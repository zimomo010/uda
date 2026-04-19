package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.transaction.FinancingSideType;
import com.bu.admin.feign.enumtype.transaction.FinancingType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransFinancing  extends BaseBo implements Serializable{

    private  Integer id;
    private Integer transId;
    private String noteId;

    private FinancingType financingType;
    private BigDecimal financingAmount;
    private CurrencyType financingCcy;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate financingStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate financingEndDate;

    private String groupId;                 //组归属号
    private String eventName;
    private String resetFrequency;
    private Integer financingFixDate;
    private String fundingFloatingRateIndex;
    private BigDecimal fundingFloatingRateSpread;
    private BigDecimal fundingFixedRate;
    private BigDecimal floatingRate = BigDecimal.ZERO;
    private FinancingSideType side;
    private String remark;
    private String eventData1;        //financing day counts
}

