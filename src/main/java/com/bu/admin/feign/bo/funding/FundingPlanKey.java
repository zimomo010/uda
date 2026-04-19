package com.bu.admin.feign.bo.funding;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FundingPlanKey implements Serializable {

    private String name;
    private BigDecimal value;

}
