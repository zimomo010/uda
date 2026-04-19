package com.bu.admin.api;

import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCode;

/**
 * @ClassName WsException
 * @Description
 * @Author ghostWu
 * @Date 2019/7/10
 */
public class WsException extends BasicException {
    public WsException(ErrorCode errorCode) {
        super(errorCode);
    }

    public WsException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public WsException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public WsException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
