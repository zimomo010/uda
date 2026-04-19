package com.bu.admin.system;

import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCode;


public class InitException extends BasicException {
    public InitException(ErrorCode errorCode) {
        super(errorCode);
    }

    public InitException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public InitException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public InitException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
