package com.bu.admin.feign.bo.scheduletask;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 计划任务执行日志的 HTM Valuation Summary 数据
 *
 * @author liujiegang
 * @date 2024/8/3
 */
@Data
public class ScheduleTaskLogHTMValuationSummaryBO {

    /**
     * 票据note id
     */
    private String billNoteId;

    /**
     * transaction
     */
    private Transaction transaction;

    /**
     * product basic
     */
    private ProductBasic productBasic;

    /**
     * transaction valuation summary
     */
    private TransactionValuationSummary transactionValuationSummary;

    /**
     * 专户
     */
    private SpecialAccount specialAccount;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Transaction {

        /**
         * Note ID
         */
        private String valuationNoteId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProductBasic {

        /**
         * 证券id
         */
        private String isinId;

        /**
         * 预期规模
         * BigDecimal
         */
        private String denomination;

        /**
         * 币种
         */
        private String ccy;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TransactionValuationSummary {

        /**
         * id
         */
        private Integer id;

        /**
         * NAV
         * BigDecimal
         */
        private String valuationNav;

        /**
         * Price
         * BigDecimal
         */
        private String valuationPrice;

        /**
         * Value Date
         */
        private String valuationDate;

        /**
         * Pull to Par adjustment
         * BigDecimal
         */
        private String pollToParAdjCcy1;
        private String pollToParAdjCcy2;

        /**
         * Accrued Interest
         * BigDecimal
         */
        private String bondAccrualDiff1;
        private String bondAccrualDiff2;

        /**
         * FX PNL
         * BigDecimal
         */
        private String fxPnlDiff1;
        private String fxPnlDiff2;

        /**
         * cashflow post-issuance in Issue Currency
         * BigDecimal
         */
        private String postCashAssetCcyDiff1;
        private String postCashAssetCcyDiff2;

        /**
         * Fx rate
         * BigDecimal
         */
        private String fxRate;

        /**
         * Accumulated Accrual
         * BigDecimal
         */
        private String accumulatedAccrualCcy1;
        private String accumulatedAccrualCcy2;

        /**
         * Accumulated Accrual Daily Diffenece
         * BigDecimal
         */
        private String accumulatedAccrualDiff1;
        private String accumulatedAccrualDiff2;

        /**
         * Accumulated Accrual Daily Diffenece Check
         */
        private String accumulatedDailyCheckMark;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpecialAccount {

        /**
         * 全称
         */
        private String longName;
    }
}
