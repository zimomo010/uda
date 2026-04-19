package com.bu.admin.feign.enumtype.risk;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum TransRiskReportType {

    FULL_FX_RISK("Full_FX_Risk"),
    FX_NOTIONAL_BASED_STOP_LOSS("FX_Notional-based_Stop-loss"),
    PN_PORT_BASED_STOP_LOSS("PN_Port-based_Stop-loss"),
    LN_PORT_BASED_STOP_LOSS("LN_Port-based_Stop-loss"),
    LOAN_TO_VALUE("Loan-To-Value"),
    ;

    TransRiskReportType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
