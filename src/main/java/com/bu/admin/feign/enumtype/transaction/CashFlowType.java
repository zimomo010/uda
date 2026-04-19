package com.bu.admin.feign.enumtype.transaction;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum CashFlowType {

    DEPOSIT("Deposit"),
    WITHDRAWAL("Withdrawal"),
    B("B"),
    S("S"),
    MATURITY("Maturity"),
    CALL("Call"),

    FX("FX"),
    FX_PNL("FX PNL"),
    CORPORATE_ACTION("Corporate Action"),
    CORPORATE_ACTION_VALUATION("Corporate Action-Valuation"),
    INITIAL_CASH("Initial Cash"),
    ISSUE_PROCEEDS("Issue Proceeds"),
    FINANCING_INCREASE("Financing Increase"),
    FINANCING_DECREASE("Financing Decrease"),
    FEE_PAYMENT("Fee Payment"),
    UNKNOWN("unknown")
    ;


    CashFlowType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
