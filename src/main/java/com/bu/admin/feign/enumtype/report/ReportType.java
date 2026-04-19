package com.bu.admin.feign.enumtype.report;


import lombok.Getter;

@Getter
public enum ReportType {

    NOTE_SUMMARY("Note_Summary_Report"),
    BOND_POSITION("Bond_Position_Report"),
    BOND_TRANSACTION("Bond_Transaction_Report"),
    FUND_POSITION("Fund_Position_Report"),
    FUND_TRANSACTION("Fund_Transaction_Report"),
    EQUITY_TRANSACTION("Equity_Transaction_Report"),
    EQUITY_POSITION("Equity_Position_Report"),
    FX_POSITION("FX_Position_Report"),
    FX_TRANSACTION("FX_Transaction_Report"),
    CASH_FLOW("Cash_Flow_Report"),
    COUPON_PAYMENT_REPORT("Coupon_Payment_Report"),
    DIVIDEND_PAYMENT("Dividend_Payment_Report"),
    FINANCING_REPORT("Financing_Report"),
    FEES_REPORT("Fees_Report"),
    HTM_VALUATION_SUMMARY_REPORT("HTM_Valuation_Summary_Report"),
    MTM_VALUATION_SUMMARY_REPORT("MTM_Valuation_Summary_Report"),
    MTM_VALUATION_SUMMARY_FLN_REPORT("MTM_Valuation_Summary_Fln_Report"),
    NOTE_DETAIL_REPORT("Note_Detail_Report"),
    HEDGE_FINANCING_REPORT("Hedge_Financing_Report"),
    /*------------CLN---------------*/
    CLN_OPTION_REPORT("Cln_Option_Report"),
    CLN_INTEREST_REPORT("Cln_Interest_Report"),
    CLN_NOTE_SUMMARY_REPORT("Cln_Note_Summary_Report"),
    CLN_HEDGE_REPORT("Cln_Hedge_Report"),
    CDS_HEDGE_REPORT("Cds_Hedge_Report"),
    CLN_VALUATION_SUMMARY_REPORT("Cln_Valuation_Summary_Report"),
    /*------------Funding TRS---------------*/
    FUNDING_TRS_GENERAL("Funding_Trs_General"),
    FUNDING_TRS_UNDERLYING("Funding_Trs_Underlying"),
    FUNDING_TRS_FINANCING("Funding_Trs_Financing"),
    FUNDING_TRS_BOND_POSITION("Funding_Trs_Bond_Position"),
    FUNDING_TRS_RISK("Funding_Trs_Risk"),
    FUNDING_TRS_CASH_FLOW("Funding_Trs_Cash_Flow"),

    /*------------Risk---------------*/
    RISK_SUMMARY("Risk_Summary_Report"),
    /*------------Risk Type---------------*/
    NOTE_MATURITY_REPORT("Note_Maturity_Report"),
    BOND_MATURITY_REPORT("Bond_Maturity_Report"),
    FX_MATURITY_REPORT("FX_Maturity_Report"),
    FINANCING_MATURITY_REPORT("Financing_Maturity_Report"),
    FEE_MATURITY_REPORT("Fee_Maturity_Report"),
    PAYMENT_SCHEDULE_REPORT("Payment_Schedule_Report"),
    CLN_CALL_DATE_REPORT("Cln_Call_Date_Report"),
    CLN_REFERENCE_BOND_MATURITY_REPORT("Cln_Reference_Bond_Maturity_Report"),
    CDS_MATURITY_REPORT("Cds_Maturity_Report"),
    /*------------Funding Type---------------*/
    TOTAL_FUNDING_MATURITY_MANAGEMENT("Total_Funding_Maturity_Management"),
    REPO_MATURITY_MANAGEMENT("Repo_Maturity_Management"),
    TRS_MATURITY_MANAGEMENT("TRS_Maturity_Management"),
    SMCN_MATURITY_MANAGEMENT("SMCN_Maturity_Management"),
    TENOR_DISTRIBUTION("Tenor_Distribution"),
    /*------------PNL Type---------------*/
    PNL_SUMMARY("Pnl_Summary"),
    FINANCING_PNL("Financing_Pnl"),
    FEE_PNL("Fee_Pnl"),
    BOND_PNL("Bond_Pnl"),
    FX_PNL("Fx_Pnl"),
    /*------------Valuation Type---------------*/
    COST_METHOD_GENERAL_VALUATION("Cost_Method_General_Valuation"),
    HTM_VALUATION("HTM_Valuation"),
    HTM_VALUATION_FX_MTM("HTM_Valuation_FX_MTM"),
    HTM_VALUATION_ALL_FX_MTM("HTM_Valuation_ALL_FX_MTM"),
    HTM_VALUATION_CAPITAL_PNL_REALIZED("HTM_Valuation_Capital_PNL_Realized"),
    HTM_VALUATION_FX_FWD("HTM_Valuation_FX_Fwd"),
    HTM_VALUATION_NONBOND_MTM("HTM_Valuation_NonBond_MTM"),
    MTM_VALUATION("MTM_Valuation"),
    MTM_VALUATION_FX_HTM("MTM_Valuation_FX_HTM"),
    MTM_VALUATION_FLN("MTM_Valuation_Fln"),
    MTM_VALUATION_CLN("MTM_Valuation_Cln"),
    MTM_VALUATION_OLN("MTM_Valuation_Oln"),
    MTM_VALUATION_ELN("MTM_Valuation_Eln"),
    /*------------Calculation Type---------------*/
    ISSUE_CALCULATION("Issue_Calculation"),
    ISSUE_CALCULATION_FLN("Issue_Calculation_Fln"),
    ISSUE_CALCULATION_OLN("Issue_Calculation_Oln"),
    ISSUE_CALCULATION_ELN("Issue_Calculation_Eln"),

    REDEMPTION_CALCULATION("Redemption_Calculation"),
    REDEMPTION_CALCULATION_FLN("Redemption_Calculation_Fln"),
    REDEMPTION_CALCULATION_OLN("Redemption_Calculation_Oln"),
    GENERAL_CALCULATION("General_Calculation"),
    GENERAL_CALCULATION_FLN("General_Calculation_Fln"),
    GENERAL_CALCULATION_OLN("General_Calculation_Oln"),
    HTM_PARTIAL_REDEMPTION_CALCULATION("HTM_Partial_Redemption_Calculation"),
    MTM_PARTIAL_REDEMPTION_CALCULATION("MTM_Partial_Redemption_Calculation"),
    MTM_PARTIAL_REDEMPTION_CALCULATION_OLN("MTM_Partial_Redemption_Calculation_Oln"),
    /*------------Stress Type---------------*/
    TRANS_BOND_STRESS_REPORT("Bond_Stress_Report"),
    /*------------Management report----------*/
    NOTE_DETAIL_REPORT_GENERAL("Note_Detail_Report_General"),
    NOTE_DETAIL_REPORT_SIMPLIFIED("Note_Detail_Report_Simplified"),
    NOTE_DETAIL_REPORT_HTM_VALUATION_SUMMARY("Note_Detail_Report_HTM_Valuation_Summary"),
    /*------------Note Upsize report----------*/
    NOTE_UPSIZE("Note_Upsize"),
    /*------------Repo report----------*/
    REPO_POSITION("Repo_Position"),
    REPO_TRANSACTION("Repo_Transaction"),
    REPO_COLLATERAL ("Repo_Collateral"),
    REPO_FINANCING ("Repo_Financing"),
    REPO_CASH_FLOW ("Repo_Cash_Flow"),
    REPO_MTM_VALUATION ("Repo_MTM_Valuation"),
    REPO_SUMMARY ("Repo_Summary"),
    /* amc */
    GENERAL_MARKET_INFO("general market info"),
    GENERAL_UNDERLYING_BOOKING("general underlying booking"),
    OPTION_POSITION_REPORT("Option_Position_Report"),
    OPTION_TRANSACTION_REPORT("Option_Transaction_Report"),
    OPTION_FUND_POSITION_REPORT("Option_Fund_Position_Report"),
    OPTION_FUND_TRANSACTION_REPORT("Option_Fund_Transaction_Report"),
    ;

    ReportType(String desc) {
        this.desc = desc;
        this.category = "Normal";
    }


    private final String desc;

    private final String category;

}
