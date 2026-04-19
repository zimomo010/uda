package com.bu.admin.feign.bo.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * user task po
 *
 * @author ghostWu
 */
@Data
public class TransactionProduct implements Serializable {


    private String id;
    private String transId;
    private String productId;
    private String isinId;
    private String issuerId;
    private String issuerName;

    private BigDecimal issuerPrice;
    private BigDecimal marketPrice;
    private Integer numbers;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date transDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishDate;

    private Integer cusTemplateId;


}
