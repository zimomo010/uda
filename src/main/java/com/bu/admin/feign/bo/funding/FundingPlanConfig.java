package com.bu.admin.feign.bo.funding;

import com.bu.admin.feign.enumtype.product.CurrencyType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class FundingPlanConfig implements Serializable {

    private String userId;
    private CurrencyType currencyType;
    private BigDecimal usdRate;
    private List<FundingPlanMonth> fundingPlanMonthList = new ArrayList<>();
}
