package com.bu.admin.api;

import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCodes;


public class ParamEmptyVerifyException extends BasicException {

    public ParamEmptyVerifyException(String emptyParam) {
        super(ErrorCodes.ApiEntrance.EMPTY_PARAM, emptyParam + "不能为空");
    }
}
