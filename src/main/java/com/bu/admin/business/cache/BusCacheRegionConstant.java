package com.bu.admin.business.cache;

import lombok.Getter;


@Getter
public enum BusCacheRegionConstant {
    USER_COS_FILE_URL("USER_COS_FILE_URL"),
    METHOD_REQUEST_LIMIT("METHOD_REQUEST_LIMIT");

    BusCacheRegionConstant(String desc) {
        this.desc = desc;
    }

    private final String desc;
}
