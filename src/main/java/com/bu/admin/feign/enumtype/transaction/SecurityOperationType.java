package com.bu.admin.feign.enumtype.transaction;

public enum SecurityOperationType {
    EXERCISE("Exercise"),
    MATURE("Mature"),

    ;


    SecurityOperationType(String desc) {
        this.desc = desc;
    }

    private final String desc;

    public String getDesc() {
        return desc;
    }
}
