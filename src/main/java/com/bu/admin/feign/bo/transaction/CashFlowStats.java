package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.product.FrequencyType;

import com.bu.admin.utils.adapter.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class CashFlowStats extends BaseBo implements Serializable{


    private Integer id;

    private BigDecimal openingBalance;
    private BigDecimal fixedOpeningBalance;
    private BigDecimal deltaAmount;
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate valueDate;
    private String inFlowSummary;
    private String outFlowSummary;

    private FrequencyType frequencyType;
}

