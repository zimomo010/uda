package com.bu.admin.feign.bo.risk;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.risk.TransRiskType;
import com.bu.admin.utils.adapter.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * transaction valuation po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TransRisk extends BaseBo implements Serializable {

    private Integer id;

    private Integer transId;               //订单ID

    private CurrencyType issueCcy;

    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate priceDate;           //估值日

    private TransRiskType transRiskType;

    private BigDecimal portfolioStopLoss = new BigDecimal(0);
    private BigDecimal fxNotionalStopLoss = new BigDecimal(0);

    private String riskExtend1;
    private String riskExtend2;
    private String riskExtend3;

    private String remark;

}
