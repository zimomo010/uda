package com.bu.admin.feign.bo.transaction;


import com.bu.admin.utils.adapter.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransSecurityLogExtend{
    private Integer trlId;

    private Boolean oneTimeRealizedPnl;

    private BigDecimal fxHedgingYieldUsd;

    private BigDecimal fxHedgingYieldCnh;

    private BigDecimal yieldRate;

    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate fXHedgingForwardDate;

    private BigDecimal fxHedgingForwardNotional;

    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate nxtCallDate;
}
