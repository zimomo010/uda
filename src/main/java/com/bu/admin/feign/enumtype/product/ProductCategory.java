package com.bu.admin.feign.enumtype.product;

import lombok.Getter;

/**
 * Created by GhostWu 
 * product deal category
 */
@Getter
public enum ProductCategory {

    CAPITAL_PROTECTED("保本型"),
    YIELD_ENHANCEMENT("增强型"),
    PARTICIPATION("参与型"),
    OTHER("其他"),;

    ProductCategory(String desc) {
        this.desc = desc;
    }

    private final String desc;

}
