package com.bu.admin.system;

import com.bu.admin.utils.AopTargetUtils;
import com.bu.admin.extend.exception.ErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Component
public class SystemInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(SystemInitializer.class);

    private final Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final List<Caller> errorCallers = new ArrayList<>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            new Thread(() -> {
                try {
                    long start = System.currentTimeMillis();
                    logger.info("system init ...");

                    ApplicationContext ctx = event.getApplicationContext();
                    List<Caller> list = ctx.getBeansWithAnnotation(Component.class).values().stream().map(obj -> {
                        Caller caller = new Caller();
                        try {
                            caller.obj = AopTargetUtils.getTarget(obj); // 获取被代理对象，因此初始化方法暂不支持事务环境
                            //
                            caller.initMethod = Arrays.stream(caller.obj.getClass().getDeclaredMethods())
                                    .filter(m -> m.getAnnotation(Init.class) != null).findFirst().orElse(null);
                        } catch (Exception e) {
                            // ignore
                        }
                        return caller;
                    }).filter(c -> c.initMethod != null).toList();
                    CountDownLatch latch = new CountDownLatch(list.size());

                    logger.info("find [{}] methods need init...", list.size());
                    init(list, latch);

                    latch.await();

                    if (!errorCallers.isEmpty()) {
                        throw new InitException(ErrorCodes.DataDeal.SYSTEM_INIT_ERROR, "system init error，error method is：" + errorCallers);
                    }

                    logger.info("system init finish, cost {} ms", System.currentTimeMillis() - start);
                } catch (Exception e) {
                    logger.error("system init error", e);
                    System.exit(-1);
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    private void init(List<Caller> callers, CountDownLatch latch) {
        callers.forEach(caller -> executor.execute(() -> {
            try {
                logger.info("execute [{}] init method...", caller);
                ReflectionUtils.makeAccessible(caller.initMethod);
                caller.initMethod.invoke(caller.obj);
                logger.info("execute [{}] init method finish", caller);
            } catch (Exception e) {
                errorCallers.add(caller);
                logger.error("execute [{}] init failed,error message is [{}]", caller, e.getMessage());
            } finally {
                latch.countDown();
            }
        }));
    }


    private static class Caller {
        private Object obj; //
        private Method initMethod; //

        @Override
        public String toString() {
            return obj.getClass().getName() + "." + initMethod.getName() + "()";
        }
    }
}
