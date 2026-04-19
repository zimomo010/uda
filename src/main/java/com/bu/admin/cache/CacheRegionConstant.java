package com.bu.admin.cache;

import lombok.Getter;


@Getter
public enum CacheRegionConstant {

    USER_TOKENS("USER_TOKENS"), // 用户token
    USER_EXTEND_INFO("USER_EXTEND_INFO"),//用户扩展
    OPERATION_ROLE_REVALANCE("OPERATION_ROLE_REVALANCE"),//功能权限角色域
    SEQUENCE_NO("SEQUENCE_NO");

    CacheRegionConstant(String desc) {
        this.desc = desc;
    }

    private final String desc;
}
