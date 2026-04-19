package com.bu.admin.cache.exception;

import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCode;


public class CacheException extends BasicException {
    public CacheException(ErrorCode errorCode) {
        super(errorCode);
    }

    public CacheException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public CacheException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public CacheException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
