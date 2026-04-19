package com.bu.admin.feign.bo.product;


import com.bu.admin.feign.enumtype.product.PriceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductPrice implements Serializable {

    private String id;
    private String productId;
    private String isinId;                      //产品名称
    private PriceType priceType;                //证券id
    private String marketPrice;                 //实际价格
    private String priceUserId;                  //价格用户id
    private String priceUserName;               //价格用户名称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validityEndDate;               //有效止期
    private Boolean status;                     //是否有效
    private String createUser;
    private Date createTime;

}
