package com.bu.admin.utils.lock;

/**
 * Created by ghostWu on 15/6/2.
 */
public class LockAcquireTimeoutException extends LockException {
    public LockAcquireTimeoutException() {
    }

    public LockAcquireTimeoutException(String message) {
        super(message);
    }

    public LockAcquireTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockAcquireTimeoutException(Throwable cause) {
        super(cause);
    }

    public LockAcquireTimeoutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
