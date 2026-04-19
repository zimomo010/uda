package com.bu.admin.api;

import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCode;

/**
 * @ClassName ApiException
 * @Description
 * @Author ghostWu
 * @Date 2019/7/10
 */
public class ApiException extends BasicException {
    public ApiException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ApiException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ApiException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public ApiException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
