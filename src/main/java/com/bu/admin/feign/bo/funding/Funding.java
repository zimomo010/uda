package com.bu.admin.feign.bo.funding;


import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.funding.FundingStatus;
import com.bu.admin.feign.enumtype.funding.FundingType;
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
public class Funding extends BaseBo implements Serializable {

    private Integer id;
    private FundingType type;

    private String fundingSn;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate startDate;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate maturityDate;

    private FundingStatus busType;
    private String ccy;
    private String viewInCurrency;
    private BigDecimal settlementAmount;
    private String counterparty;
}
