package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.transaction.ValuationAdjustType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransValuationAdjust extends BaseBo implements Serializable {

    private Integer id;
    private Integer transId;
    private LocalDate effectiveDate;
    private LocalDate adjustStartDate;
    private LocalDate adjustEndDate;
    private Integer plannedAdjustDays;
    private BigDecimal adjustAmount;
    private CurrencyType currency;
    private ValuationAdjustType adjustType;
}
