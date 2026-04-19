package com.bu.admin.feign.bo.product;


import com.bu.admin.feign.enumtype.common.DataType;
import com.bu.admin.feign.enumtype.product.ProductDealType;

import java.io.Serializable;
import java.util.Date;

/**
 * product specific po
 *
 * @author ghostWu
 */
public class ProductSpecificTemplate implements Serializable {

    private String id;
    private ProductDealType dealType;            //产品类型
    private String dataName;                     //名称
    private DataType dataType;                   //类型
    private Date updateTime;
    private Boolean showInBasic;                 //首页展示
    private int sortNum;

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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public Boolean getShowInBasic() {
        return showInBasic;
    }

    public void setShowInBasic(Boolean showInBasic) {
        this.showInBasic = showInBasic;
    }
}
