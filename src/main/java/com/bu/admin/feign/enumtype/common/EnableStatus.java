package com.bu.admin.feign.enumtype.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 启用状态
 *
 */
@Getter
@AllArgsConstructor
public enum EnableStatus {

    ENABLE("启用"),
    DISABLE("禁用"),
    ;

    private String desc;
}
