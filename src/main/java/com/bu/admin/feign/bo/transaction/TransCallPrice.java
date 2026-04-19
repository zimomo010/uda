package com.bu.admin.feign.bo.transaction;


import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.product.FrequencyType;
import com.bu.admin.feign.enumtype.product.ProductEventType;
import com.bu.admin.utils.adapter.DateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransCallPrice extends BaseBo implements Serializable{

    private Integer id;
    private Integer transId;
    private String noteId;
    private String userId;
    private BigDecimal callRate;
    private BigDecimal basePrice;
    private CurrencyType currencyType;
    private String dayCount;

    @JsonAdapter(DateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    @JsonAdapter(DateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    private FrequencyType frequencyType;         //频率类型
    private Integer frequencyValue;
    private BigDecimal callPrice;
    private String batchInputNo;
    private String remark;

    private String eventName;
    private ProductEventType eventType;
    private String extendInfo;
    private LocalDate eventDate3;

}

