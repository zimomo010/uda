package com.bu.admin.feign.enumtype.transaction;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum BondCategory {

    CHENG_TOU("Chengtou"),
    REAL_ESTATE("RealEstate"),
    OTHERS("Others")
    ;

    BondCategory(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
