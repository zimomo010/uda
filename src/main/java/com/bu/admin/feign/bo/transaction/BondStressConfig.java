package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
public class BondStressConfig extends BaseBo implements Serializable {

    private BigDecimal stressLowerBound;
    private BigDecimal stressUpperBound;
    private BigDecimal rangeStressedPrice;
    private BigDecimal idiosyncraticFactor;

}
