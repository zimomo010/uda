package com.bu.admin.extend.exception;


import lombok.Getter;
import org.apache.commons.lang3.StringUtils;


@Getter
public class BasicException extends RuntimeException {

    private final ErrorCode errorCode;

    public BasicException(ErrorCode errorCode) {
        this(errorCode, null, null);
    }

    public BasicException(ErrorCode errorCode, String message) {
        this(errorCode, message, null);
    }

    public BasicException(ErrorCode errorCode, Throwable cause) {
        this(errorCode, null, cause);
    }

    public BasicException(ErrorCode errorCode, String message, Throwable cause) {
        super(StringUtils.isNotEmpty(message) ? message : errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

}
