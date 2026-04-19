package com.bu.admin.feign.bo.product;


import com.bu.admin.feign.enumtype.product.BarrierLevelType;
import com.bu.admin.feign.enumtype.product.BarrierStyle;
import com.bu.admin.feign.enumtype.product.FrequencyType;
import com.bu.admin.feign.enumtype.product.ObserveType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * product event po
 *
 * @author ghostWu
 */
public class ProductBarrier implements Serializable {

    private String id;
    private String productId;                   //产品id
    private BarrierLevelType barrierLevelType;
    private BigDecimal barrierLevel;
    private BarrierStyle barrierStyle;
    private ObserveType observeType;            //事件发生日 观察日
    private FrequencyType observationFrequency;
    private FrequencyType knockFrequency;
    private Date startDate;                    //结息日
    private Date endDate;                       //付款日
    private BigDecimal barrierLevelDistance;

    private Date createTime;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public BigDecimal getBarrierLevel() {
        return barrierLevel;
    }

    public void setBarrierLevel(BigDecimal barrierLevel) {
        this.barrierLevel = barrierLevel;
    }

    public BarrierStyle getBarrierStyle() {
        return barrierStyle;
    }

    public void setBarrierStyle(BarrierStyle barrierStyle) {
        this.barrierStyle = barrierStyle;
    }

    public ObserveType getObserveType() {
        return observeType;
    }

    public void setObserveType(ObserveType observeType) {
        this.observeType = observeType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getBarrierLevelDistance() {
        return barrierLevelDistance;
    }

    public void setBarrierLevelDistance(BigDecimal barrierLevelDistance) {
        this.barrierLevelDistance = barrierLevelDistance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BarrierLevelType getBarrierLevelType() {
        return barrierLevelType;
    }

    public void setBarrierLevelType(BarrierLevelType barrierLevelType) {
        this.barrierLevelType = barrierLevelType;
    }

    public FrequencyType getObservationFrequency() {
        return observationFrequency;
    }

    public void setObservationFrequency(FrequencyType observationFrequency) {
        this.observationFrequency = observationFrequency;
    }

    public FrequencyType getKnockFrequency() {
        return knockFrequency;
    }

    public void setKnockFrequency(FrequencyType knockFrequency) {
        this.knockFrequency = knockFrequency;
    }

}
