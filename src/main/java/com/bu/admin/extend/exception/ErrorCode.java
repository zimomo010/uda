package com.bu.admin.extend.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public final class ErrorCode implements Serializable {

    public ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final int code;
    private final String message;

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
