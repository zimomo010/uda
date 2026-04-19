package com.bu.admin.feign.bo.transaction;


import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.transaction.CashFlowType;
import com.bu.admin.feign.enumtype.transaction.TransType;
import com.bu.admin.utils.adapter.DateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class CashFlow extends BaseBo {
    private String tradeId;

    private String noteId;

    private CashFlowType cashFlowType;

    private String cashFlowName;

    private String secName;

    private String secIsin;

    @JsonAdapter(DateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date tradeDate;

    @JsonAdapter(DateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date valueDate;

    private CurrencyType currency;

    private BigDecimal amount;

    private BigDecimal fxRate;

    private BigDecimal financingRate;

    private BigDecimal fundingRateSpread;

    private String fundingRateIndex;

    private String tradeSn;

    private Integer transId;

    private TransType transType = TransType.COMMON;

    private Boolean fxRealizedPNL;
}
