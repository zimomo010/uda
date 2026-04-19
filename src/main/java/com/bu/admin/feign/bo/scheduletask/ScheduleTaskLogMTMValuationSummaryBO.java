package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;

/**
 * 计划任务执行日志的 MTM Valuation Summary Report 数据
 *
 * @author liujiegang
 * @date 2024/8/8
 */
@Data
public class ScheduleTaskLogMTMValuationSummaryBO {

    private String clientShortName;
    private String clientLongName;
    private String noteHolderShortName;
    private String noteHolderLongName;
    private String noteId;
    private String noteIsin;
    private String notional;
    private String issueCcy;

    private String nav;
    private String navRate;

    private String currencyType;

    private String totalFeeAmt;
    private String finCostAmount;
    private String couponReceived;
    private String accountBalance;
    private String financingAmt;
    private String outStandingFxNotional;
    private String outStandingBondNotional;

    private String totalFeeAmtWeeklyDiff;
    private String finCostAmtWeeklyDiff;
    private String couponReceivedWeeklyDiff;
    private String accountBalanceWeeklyDiff;
    private String financingAmtWeeklyDiff;
    private String osFxNotionalWeeklyDiff;
    private String osBondNotionalWeeklyDiff;
    private String totalWeeklyDiff;
    private Boolean totalWeeklyDiffMark;
}
