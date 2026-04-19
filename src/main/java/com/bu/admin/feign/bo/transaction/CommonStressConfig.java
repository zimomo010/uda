package com.bu.admin.feign.bo.transaction;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CommonStressConfig implements Serializable {

    private String stressName;
    private BigDecimal stressValue;

}
