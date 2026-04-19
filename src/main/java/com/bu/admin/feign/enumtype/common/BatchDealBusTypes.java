package com.bu.admin.feign.enumtype.common;

/**
 * Created by GhostWu on 2015/3/16.
 * 订单状态类型
 */
public enum BatchDealBusTypes {

    NOTE_BOOKING("note booking"),
    BOND_BOOKING("bond booking"),
    FUND_BOOKING("fund booking"),
    FX_BOOKING("fx booking"),
    FINANCING_BOOKING("financing booking"),
    REDEMPTION_BOND_BOOKING("redemption bond booking"),
    REDEMPTION_FX_BOOKING("redemption fx booking"),
    CASH_FLOW("cash flow"),
    NOTIONAL_UPDATE("notional update"),
    FEE_INPUT("fee input"),
    VALUATION_ADJUST_BOOKING("valuation adjustment booking"),
    PAYMENT_BOOKING("payment booking"),
    /* cln note */
    CLN_NOTE_BOOKING("cln note booking"),
    CLN_MARKET_INFO("cln market info"),
    CDS_BOOKING("cds booking"),
    CDS_MARKET_INFO("cds market info"),
    /* gmc */
    GENERAL_MARKET_INFO("general market info"),
    GENERAL_UNDERLYING_BOOKING("general underlying booking"),
    /* data ma */
    HIS_NOTE_BOOKING("his note booking"),
    HIS_BOND_TRANSACTION("his bond transaction"),
    HIS_FEE_REPORT("his fee report"),
    HIS_FX_TRANSACTION("his fx transaction"),
    HIS_FINANCING("his financing"),
    HIS_CASH_FLOW("his cash flow"),
    HIS_FUND_BOOKING("his fund booking"),
    /* funding */
    SMCN_BOOKING("smcn booking"),
    MIGRATION_REPO_BOOKING("migration repo booking"),
    LIFECYCLE_NEW_REPO("lifecycle new repo"),
    LIFECYCLE_REPO_TERMINATION("lifecycle repo termination"),
    TRS_BOOKING("trs booking"),
    /* funding fx */
    MIGRATION_FX_BOOKING("migration fx booking"),
    LIFECYCLE_FX_BOOKING("lifecycle fx booking"),
    TRS_RESET("trs reset"),
    /* house fx */
    HOUSE_FX_BOOKING("house fx booking"),
    HOUSE_HIS_FX_BOOKING("house his fx booking"),
    HOUSE_HIS_CASH_FLOW("house his cash flow"),
    TRS_OPEN_BOOKING("trs open booking"),
    TRS_UNWIND_BOOKING("trs unwind booking"),
    /* risk */
    COUNTERPARTY_RISK_MONITOR("counterparty risk monitor"),
    OPTION_BOOKING("option booking"),
    EQUITY_BOOKING("equity booking")
    ;

    BatchDealBusTypes(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

}
