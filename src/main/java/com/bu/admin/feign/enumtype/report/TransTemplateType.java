package com.bu.admin.feign.enumtype.report;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum TransTemplateType {

    VALUATION("Valuation"),
    VALUATION_MTM("Valuation_Mtm"),
    WORD_REPORT("Word_Report"),
    MARKET_VALUATION_TYPE("Market_Valuation_Type"),
    ;

    TransTemplateType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
