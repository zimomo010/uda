package com.bu.admin.feign.bo.risk;

import com.bu.admin.extend.bo.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * transaction risk config po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TransRiskConfig extends BaseBo implements Serializable {

    public TransRiskConfig(){

    }

    public TransRiskConfig(Integer transId){
        this.transId = transId;
    }

    private Integer id;

    private Integer transId;               //订单ID

    private BigDecimal portfolioStopLoss = new BigDecimal(0);
    private BigDecimal fxNotionalStopLoss = new BigDecimal(0);
    private BigDecimal partialStopLoss = new BigDecimal(0);
    private BigDecimal initialLtv = new BigDecimal(0);
    private BigDecimal concentrationTrigger = new BigDecimal(0);

    private String remark;
    private String configDetail;

}
