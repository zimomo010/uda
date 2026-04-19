package com.bu.admin.feign.enumtype.product;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum BarrierStyle {

    WORST_OF("表现最差的标的"),
    COMBINED("所有标的结合"),
    BEST_OF("表现最好的标的"),;

    BarrierStyle(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
