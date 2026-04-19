package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.product.FrequencyType;
import com.bu.admin.feign.enumtype.transaction.FinancingSideType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransHedgeIrs  extends BaseBo implements Serializable {

    private  Integer id;
    private Integer transId;
    private String noteId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate tradeDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate endDate;

    private FinancingSideType fixedLegSide;
    private BigDecimal fixedRate;
    private FrequencyType fixedFrequency;
    private String fixedLegDayCount;


    private FinancingSideType floatingLegSide;
    private String floatingLegIndex;
    private BigDecimal floatingLegSpread;
    private BigDecimal floatingRate;
    private FrequencyType floatingFrequency;
    private String floatingLegDayCount;

    private BigDecimal notional;
    private CurrencyType ccy;
    private String counterparty;
}

