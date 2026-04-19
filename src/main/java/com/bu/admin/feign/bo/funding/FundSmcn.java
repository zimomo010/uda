package com.bu.admin.feign.bo.funding;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.product.SettlementType;
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
public class FundSmcn extends BaseBo implements Serializable {


    private Integer fundingId;
    private Funding funding;

    private String noteId;
    private String isin;
    private String issuerId;
    private String noteHolderId;
    private String counterparty;
    private SettlementType settlementType;
    private CurrencyType ccy;
    private BigDecimal notional;

    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate issueDate;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate maturityDate;

    private BigDecimal netProceedsAmount;

    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate tradeDate;

    private String putCallOptions;
    private String daycountBasis;
}
