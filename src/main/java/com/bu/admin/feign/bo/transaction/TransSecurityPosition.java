package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.transaction.BondCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * issuer security po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TransSecurityPosition extends BaseBo implements Serializable {

    private Integer id;
    private String secName;
    private String secIsin;
    private CurrencyType ccy;                   //币种
    private BigDecimal numbers;
    private String issuer;
    private String industryGroup;
    private String classificationName;
    private String chineseName;
    private BondCategory category;
    private BigDecimal issuedAmt;
    private BigDecimal outstandingAmt;
    private String extendInfo;
    private String customInfo;

    private TransIssuerSecurityCustom transIssuerSecurityCustom;
    private BondStressConfig bondStressConfig;
}
