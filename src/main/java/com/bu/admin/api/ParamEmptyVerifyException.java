package com.bu.admin.api;

import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCodes;

/**
 * 字段为空异常
 * Created by ghostWu on 15/9/11.
 */
public class ParamEmptyVerifyException extends BasicException {

    public ParamEmptyVerifyException(String emptyParam) {
        super(ErrorCodes.ApiEntrance.EMPTY_PARAM, emptyParam + "不能为空");
    }
}
