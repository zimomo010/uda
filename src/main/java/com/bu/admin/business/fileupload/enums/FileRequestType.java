package com.bu.admin.business.fileupload.enums;

import lombok.Getter;

@Getter
public enum FileRequestType {

    PUBLIC("共有读"),
    PRIVATE("私有读"),
    ;

    private String desc;

    FileRequestType(String desc) {
        this.desc = desc;
    }
}
