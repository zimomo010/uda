package com.bu.admin.feign.bo.product;

import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.product.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 机构实体
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductBasic extends QueryBase {

    private String id;
    private ProductCategory category;       //产品大类
    private ProductDealType dealType;       //产品类型
    private String productName;             //产品名称
    private String labels;
    private String isinId;                  //证券id
    private SaleType saleType;              //销售方式
    private String issuerId;                //发行商id
    private String issuerName;              //发行商名称
    private CurrencyType ccy;               //币种
    private BigDecimal denomination;            //预期规模
    private BigDecimal issuePrice;          //发行价

    private String underlyingRemark;
    private String listingMarket;           //
    private SettlementType settlementType;  //
    private BigDecimal impliedYield;        //预期收益率
    private FrequencyType payFrequency;     //付息频率
    private ProductStatus productStatus;    //产品状态
    private Boolean isHot;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date initialFixingDate;         //初始确定日
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date issueDate;                 //发行日
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finalFixingDate;           //最终确定日
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date maturityDate;              //到期日

    private String extendInfo;        //扩展信息
    private String riskCodes;         //风险说明

    private String relationFile1;     //关联文件1
    private String relationFile2;     //关联文件2
    private String relationFile3;     //关联文件3

    private String productSn;
}
