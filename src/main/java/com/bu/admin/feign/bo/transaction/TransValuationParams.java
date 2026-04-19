package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransValuationParams extends BaseBo implements Serializable {

    private CurrencyType currencyType;
    private BigDecimal notePrice;
    private Integer transId;               //订单ID
    private LocalDate priceDate;           //估值日
    private String isin;
    private String timeStr;
    private BigDecimal  navRate;
    private String noteId;
}
