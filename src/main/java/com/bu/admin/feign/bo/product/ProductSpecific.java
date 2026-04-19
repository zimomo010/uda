package com.bu.admin.feign.bo.product;


import com.bu.admin.feign.enumtype.product.ProductDealType;

import java.io.Serializable;
import java.util.Date;

/**
 * product specific po
 *
 * @author ghostWu
 */
public class ProductSpecific implements Serializable {

    private String id;
    private ProductDealType dealType;       //产品类型
    private String description;             //产品描述
    private String basicData;               //首页内容
    private String specificData;            //定制内容

    private String updateUser;
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductDealType getDealType() {
        return dealType;
    }

    public void setDealType(ProductDealType dealType) {
        this.dealType = dealType;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecificData() {
        return specificData;
    }

    public void setSpecificData(String specificData) {
        this.specificData = specificData;
    }

    public String getBasicData() {
        return basicData;
    }

    public void setBasicData(String basicData) {
        this.basicData = basicData;
    }
}
