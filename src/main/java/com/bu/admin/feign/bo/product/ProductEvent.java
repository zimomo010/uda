package com.bu.admin.feign.bo.product;


import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.product.ProductEventType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * product event po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductEvent extends QueryBase implements Serializable {

    private String id;
    private String productId;               //产品id
    private String eventName;
    private ProductEventType eventType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date eventDate;                 //事件发生日 观察日
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date exDate;                    //结息日
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date paymentDate;               //付款日
    private BigDecimal couponAmount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date earlyRedemptionDate;              //提前赎回日
    private BigDecimal earlyRedemptionAmount;      //提前赎回金额
    private BigDecimal earlyRedemptionTrigger;     //提前赎回比例
    private String remark;


}
