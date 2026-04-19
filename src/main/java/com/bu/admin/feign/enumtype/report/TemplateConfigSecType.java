package com.bu.admin.feign.enumtype.report;

/**
 * Created by GhostWu on 2015/3/16.
 * 订单状态类型
 */
public enum TemplateConfigSecType {

    PS("Pricing Supplement"),
    NPA("NPA"),
    NATP("Nth Amendment to PS"),
    NUC("Note Upsize Confirmation"),

    HTM_VALUATION("HTM_Valuation"),
    HTM_VALUATION_FX_MTM("HTM_Valuation_FX_MTM"),
    HTM_VALUATION_CAPITAL_PNL_REALIZED("HTM_Valuation_Capital_PNL_Realized"),
    HTM_VALUATION_FX_FWD("HTM_Valuation_FX_Fwd"),
    HTM_VALUATION_ALL_FX_MTM("HTM_Valuation_ALL_FX_MTM"),

    MTM_VALUATION("HTM_Valuation"),
    MTM_VALUATION_FX_HTM("HTM_Valuation_FX_HTM"),
    /* market valuation type */
    MTM("MTM"),
    HTM("HTM"),
    ;

    TemplateConfigSecType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

}
