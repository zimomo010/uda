package com.bu.admin.business.fileupload.enums;

import lombok.Getter;

@Getter
public enum NoticeBusType {

    USER_REG("用户注册"),
    PASSWORD_RESET("重置密码"),
    ;

    private final String desc;

    NoticeBusType(String desc) {
        this.desc = desc;
    }
}
