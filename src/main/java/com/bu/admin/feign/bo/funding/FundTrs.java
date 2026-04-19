package com.bu.admin.feign.bo.funding;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
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
public class FundTrs extends BaseBo implements Serializable {

    private Integer fundingId;
    private Funding funding;

    private String trsId;
    private String counterparty;
    private String underlying;
    private String isin;
    private BigDecimal notional;
    private BigDecimal ia;
    private String allotment;
    private BigDecimal financingAmount;
    private CurrencyType ccy;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate tradeDate;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate effectiveDate;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate scheduledTerminationDate;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate terminationDate;
    private BigDecimal initialPrice;
    private BigDecimal initialCleanPrice;
    private BigDecimal spread;
    private String daycountBasis;
    private String benchmark;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate floatingAmountPayerPeriodEndDates;
    private BigDecimal stopLoss;
}
