package com.bu.admin.utils.lock;

/**
 * @ClassName AquiredLockWorker
 * @Description 获取锁资源后的处理逻辑
 * @Author ghostWu
 * @Date 2018/10/22
 */
@FunctionalInterface
public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire();
}
