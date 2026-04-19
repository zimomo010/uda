package com.bu.admin.business.operationLog.bo;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.utils.adapter.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class SyncSendMessageLogBO extends BaseBo implements Serializable {

    private Integer id;
    private String sendSn;
    private String noteId;
    private String isin;
    private BigDecimal naValue;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate valueDate;           // 估值日
    private String valueTime;
    private Long ackId; // 发给第三方唯一key值
    private String status;

    private String startDate;
}
