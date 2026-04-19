package com.bu.admin.feign.bo.funding;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.bo.product.SecurityBasic;
import com.bu.admin.feign.enumtype.funding.FundingStatus;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.product.PriceType;
import com.bu.admin.utils.adapter.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class FundRepo extends BaseBo implements Serializable {

    private Integer fundingId;
    private Funding funding;
    private Integer transId;

    private String ticket;
    private String traderId;
    private PriceType priceType;
    private String securityDescription;
    private BigDecimal price;
    private BigDecimal amount;
    private BigDecimal fullNetAmount;
    private CurrencyType ccy;
    private BigDecimal settlementTotalInSettlementCurrency;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate settleDate;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate terminationTradeDate;
    private String counterparty;
    private String isin;
    private String firmAccountLongName;
    private String type;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate repoTerminationDate;
    private String shortNote1;

    // new
    private BigDecimal settlementAmountInViewCurrency;
    private BigDecimal accruedInterest;
    private Integer accruedNumberOfDays;
    private BigDecimal fxRate;
    private CurrencyType viewInCurrency;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate tradeDate;
    private String traderLogin;
    private String cusipNumber;
    private String uniqueIdentifier;

    private BigDecimal repoRate;
    private BigDecimal haircutRatio;
    private String floatingFinancingBenchmark;
    private BigDecimal floatingFinancingSpread;

    // migration
    private BigDecimal terminalMoney;
    private BigDecimal settlementAmount;

    private String batchInputNo;
    private SecurityBasic securityBasic;
    private FundingStatus busType;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate valueDate;
}
