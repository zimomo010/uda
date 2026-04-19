package com.bu.admin.feign.bo.product;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * user security po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSecurity extends BaseBo implements Serializable {


    private String usId;
    private String transId;
    private String cusId;
    private String productId;
    private String secName;
    private String secIsin;
    private CurrencyType ccy;               //币种
    private String issuerName;
    private String counterparty;

    private BigDecimal issuerPrice;
    private BigDecimal buyPrice;
    private Integer numbers;
    private Date transDate;
    private Date finishDate;
    private BigDecimal originAmount;
    private BigDecimal accruedInterestAmt;
    private BigDecimal securityAmount;
    private int statusLogIdx = 0;
    private Integer accruedInterestDays;
    private String tradeType;
    private String remark;
}
