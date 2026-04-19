package com.bu.admin.feign.enumtype.product;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum ProductStatus {

    CREATE("新创建"),
    SELLING("销售中"),
    COMPLETED("已完成"),
    OFF_SHELF("已下架");

    ProductStatus(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
