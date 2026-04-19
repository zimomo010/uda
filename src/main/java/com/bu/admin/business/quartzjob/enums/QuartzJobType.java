package com.bu.admin.business.quartzjob.enums;

public enum QuartzJobType {
    SCAN_SCHEDULE_TASK_TO_EXECUTE_QUARTZ("scanScheduleTaskToExecuteQuartz"),
    SYNC_INPUT_DATA_QUARTZ("syncInputDataQuartz"),
    SYNC_OUT_DATA_QUARTZ("syncOutDataQuartz"),
    TRANSACTION_MATURITY_QUARTZ("transactionMaturityQuartz"),
    TRANS_SECURITY_MATURITY_QUARTZ("transSecurityMaturityQuartz"),
    TRANS_VALUATION_QUARTZ("transValuationQuartz"),
    DAILY_REPORT_QUARTZ("dailyReportQuartz"),
    PRODUCT_NOTIONAL_QUARTZ("productNotionalQuartz"),
    HIS_DATA_QUARTZ("hisDataQuartz"),
    RISK_DATA_QUARTZ("riskDataQuartz"),
    BLOOM_BERG_QUARTZ("bloomBergQuartz"),
    FINANCING_QUARTZ("financingQuartz"),
    FUNDING_QUARTZ("fundingQuartz"),
    VIRTUAL_PRODUCT_QUARTZ("virtualProductQuartz"),
    CASH_FLOW_BALANCE_QUARTZ("cashFlowBalanceQuartz"),
    ;

    QuartzJobType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
