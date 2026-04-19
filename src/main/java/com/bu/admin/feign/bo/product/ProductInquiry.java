package com.bu.admin.feign.bo.product;


import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.product.ProductCategory;
import com.bu.admin.feign.enumtype.product.ProductDealType;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * product underlying po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductInquiry extends QueryBase{

    private String id;
    private String userId;                      //user id
    private String productId;                   //product id
    private ProductCategory category;           //产品大类
    private ProductDealType dealType;           //产品类型
    private CurrencyType ccy;
    private String issuers;
    private String productInfo;
    private String underlyingList;
    private Integer tenorDays;
    private Boolean finishMark;
    private String remark;                      //备注

    private String issuerId;
}
