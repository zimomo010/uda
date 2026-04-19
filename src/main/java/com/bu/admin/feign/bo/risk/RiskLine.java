package com.bu.admin.feign.bo.risk;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.risk.RiskLineType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class RiskLine extends BaseBo {
    private Integer id;
    private String name;
    private RiskLineType type;
    private BigDecimal warningLine;
    private BigDecimal riskLineValue;
    private BigDecimal cdsDiscount;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;
}
