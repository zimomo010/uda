package com.bu.admin.feign.enumtype.common;

import lombok.Getter;

/**
 * Created by Dr_Yuan on 2015/3/16.
 * 订单状态类型
 */
@Getter
public enum FinanceAccountType {
    FUND_ACC("资金账户"),
    SECURITY_ACC("证券账户")
    ;
    private final String desc;

    FinanceAccountType(String desc) {
        this.desc = desc;
    }

}