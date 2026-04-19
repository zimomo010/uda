package com.bu.admin.api;

import com.auth0.jwt.interfaces.Claim;
import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.utils.JSONUtils;
import com.google.gson.JsonObject;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Map;

@Aspect
@Component
public class WsOperationAspect {

    private static final Logger logger = LoggerFactory.getLogger(WsOperationAspect.class);
    @Resource
    private WsOperationCache wsOperationCache;

    @Around("@annotation(com.bu.admin.api.WsOperation)")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) point.getSignature();

        WsOperation operationAnnotation = methodSignature.getMethod().getAnnotation(WsOperation.class);

        long callSN = 1985;
        String interfaceName = "unknown-interface";
        boolean hasError = false;
        String response = "{}";
        String reqInfo = "{}";
        logger.info("get trace id is {}", MDC.get("traceId"));
        //need save log
        try {
            WsOperationUtils.populateParams(methodSignature, point.getArgs()); // 搜集请求参数
            callSN = wsOperationCache.getSerialNumber();

            // 查询接口路径
            RequestMapping targetPathAnnotation = point.getTarget().getClass().getAnnotation(RequestMapping.class);
            String targetPath = targetPathAnnotation.value()[0];
            String methodPath = getMethodType(methodSignature);

            WsContext.getContext().setOperationCode(targetPath + methodPath);
            WsContext.getContext().setOperationName(operationAnnotation.value());
            interfaceName = WsContext.getContext().getOperationCode();

            reqInfo = doLog(operationAnnotation, interfaceName, callSN);

            // check user token
            checkUserToken(operationAnnotation);

            // check server token
            checkServerToken(operationAnnotation);

            // check input params empty
            WsOperationUtils.checkInputEmpty(operationAnnotation);


            response = (String) point.proceed(point.getArgs());
            return response;
        } catch (BasicException e) {
            logger.error("[{}] error: {}", interfaceName, e.getErrorCode(), e);

            hasError = true;
            response = Response.error(e.getErrorCode()).message(e.getMessage()).build();
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            hasError = true;
            response = Response.error(ErrorCodes.UNKNOWN_ERROR).message("error name: " + e.getClass().getName()
                    + (StringUtils.isNotBlank(e.getMessage()) ? (", error message : " + e.getMessage()) : "")).build();
            return response;
        } finally {
            WsContext.unset();

            doFinaLog(hasError, operationAnnotation, reqInfo, response, interfaceName, callSN);
        }
    }

    //--------------------------------------------------------------------------------------------------------------
    //--------------------------------------------private methods---------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------
    private void checkUserToken(WsOperation wsOperation) {
        if (StringUtils.isNotEmpty(WsContext.getContext().getToken())) {
            String token = WsContext.getContext().getToken();
            if (wsOperationCache.userTokenExists(token)) {
                try {
                    //do nothing
                } catch (Exception e) {
                    // ignore
                    logger.error("check token failed", e);
                }
            }
        }

        if (wsOperation.tokenNeeded()) {
            if (WsContext.getContext().getUserInfo() == null) {
                throw new ApiException(ErrorCodes.ApiEntrance.INVALID_TOKEN);
            }
        }
    }

    private void checkServerToken(WsOperation wsOperation) {
        if (wsOperation.serverTokenNeeded()) {
            String serverToken = WsContext.getContext().getToken();
            String serverName = WsContext.getContext().getServerName();
            if (StringUtils.isEmpty(serverToken) || StringUtils.isEmpty(serverName)) {
                throw new ApiException(ErrorCodes.ApiEntrance.INVALID_SERVER_TOKEN);
            }
        }
    }

    private String getMethodType(MethodSignature methodSignature) {
        String methodPath;
        if (methodSignature.getMethod().isAnnotationPresent(GetMapping.class)) {
            methodPath = methodSignature.getMethod().getAnnotation(GetMapping.class).value()[0];
        } else if (methodSignature.getMethod().isAnnotationPresent(PostMapping.class)) {
            methodPath = methodSignature.getMethod().getAnnotation(PostMapping.class).value()[0];
        } else {
            throw new WsException(ErrorCodes.ApiEntrance.REQUEST_ERROR_METHOD);
        }
        return methodPath;
    }

    private String doLog(WsOperation operationAnnotation, String interfaceName, long callSN) {
        String reqInfo = "{}";
        if (logger.isInfoEnabled()) {
            String[] ignoreLogFields = operationAnnotation.ignoreLogFields();
            if (ignoreLogFields != null && ignoreLogFields.length > 0) {
                JsonObject logInput = WsContext.getContext().getRequest().deepCopy();
                Arrays.stream(ignoreLogFields).forEach(f -> {
                    if (null != logInput.get(f)) {
                        String data = logInput.get(f).getAsString();
                        logInput.addProperty(f + "-truncated", data.length() > 50 ? data.substring(0, 50) + "......" : data);
                        logInput.remove(f);
                    }
                });
                reqInfo = JSONUtils.toJSONString(logInput, ignoreLogFields);
            } else {
                reqInfo = WsContext.getContext().getRequest().toString();
            }
            logger.info("RESTful-Request-Interface[{}]Seq[{}]request info：{}", interfaceName, callSN, reqInfo);
            WsContext.getContext().setCallSn(callSN);
        }
        return reqInfo;
    }

    private void doFinaLog(boolean hasError, WsOperation operationAnnotation, String reqInfo, String response, String interfaceName, long callSN) {
        if (logger.isInfoEnabled()) {
            long start = System.currentTimeMillis();
            if (hasError) {
                logger.error("RESTful-Response-Error-Interface[{}]Seq[{}]cost[{}]request-info：{}，response：{}", interfaceName, callSN, System.currentTimeMillis() - start, reqInfo, response);
            } else {
                String[] ignoreLogOutputFields = operationAnnotation.ignoreLogOutputFields();
                if (ignoreLogOutputFields != null && ignoreLogOutputFields.length > 0) {
                    dealIgnoreLog(interfaceName, response, callSN, start);
                } else {
                    logger.info("RESTful-Response-Interface[{}]Seq[{}]cost[{}]ms,response：{}", interfaceName, callSN, System.currentTimeMillis() - start, response);
                }
            }
        }
    }

    private void dealIgnoreLog(String interfaceName, String response, long callSN, long start) {
        logger.info("RESTful-Response-WithIgnore-Interface[{}]Seq[{}]cost[{}]ms,response：{}", interfaceName, callSN, System.currentTimeMillis() - start,
                response.length() > 50 ? response.substring(0, 50) + "......" : response);
    }
}

