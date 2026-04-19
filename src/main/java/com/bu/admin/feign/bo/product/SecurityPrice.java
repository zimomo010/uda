package com.bu.admin.feign.bo.product;

import com.bu.admin.extend.bo.QueryBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * product underlying po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SecurityPrice extends QueryBase implements Serializable {

    private Integer id;
    private String securityCode;
    private String tenor;
    private String securityType;
    private String areaCode;     //区域
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal midPrice;
    private BigDecimal dirtyPrice;
    private BigDecimal dirtyPrice1;
    private BigDecimal dirtyPrice2;
    private String priceDateStr;         //价格日
    private LocalDate fwdDate;         //价格日
    private BigDecimal daysDiff;
    private String priceDetail;     //价格详细
    private String currencyType;
    private String busId;
}
