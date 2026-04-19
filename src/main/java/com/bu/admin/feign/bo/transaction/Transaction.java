package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.common.SecurityType;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.product.ProductDealType;
import com.bu.admin.feign.enumtype.product.SaleType;
import com.bu.admin.feign.enumtype.report.ReportType;
import com.bu.admin.feign.enumtype.transaction.*;
import com.bu.admin.utils.adapter.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * user task po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Transaction extends QueryBase implements Serializable {

    public Transaction() {
    }

    public Transaction(String transId) {
        this.id = transId;
    }

    private String id;
    private String transSn;
    private String productSn;

    private String cusId;                   //客户id
    private String cusName;                 //客户名
    private String cusNoteManageId;         //iaaId
    private String iaaName;                 //管理人名称
    private String channelId;               //渠道id
    private String channelName;             //渠道名

    private String salesUserId;             //国君销售用户ID
    private String salesDepartCode;         //国君销售机构
    private String agentId;                 //预留

    private CurrencyType ccy;               //币种
    private TransType transType;
    private SaleType saleType;
    private TransBusType busType;
    private CrossBoarderType crossBoarderType;

    private CurrencyType securityCCy;
    private BigDecimal marketPrice;
    private BigDecimal transAmount;
    private BigDecimal financingAmount;
    private CurrencyType financingCcy;       //融资币种
    private Date financingDate;
    private String financingRateCode;        //融资利率方式
    private BigDecimal financingRate;
    private BigDecimal financingNoteCcyAmt;
    private BigDecimal financingSecCcyAmt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date transDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date finishDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date fundsConfirmDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date securityConfirmDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date maturityDate;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate valueDate;

    private TransStatus lastStatus;
    private TransStatus transStatus;
    private String remark;
    private int statusLogIdx = 0;
    private Boolean leveraged;      //是否杠杆
    private BigDecimal noteFee;
    private TransOperation lastOperation;
    private TransValuationType valuationType;
    private ProductDealType dealType;       //产品类型
    private SecurityType securityType;
    private List<String> dealTypeNegateList;

    private String issuerId;
    private String piiId;

    private String lastId;
    private String conditionParams;
    private String opUserId;

    private ReportType reportType;
    private String reportFileType;
    private String reportFileUrl;

    private String start0;
    private String start;
    private Boolean withHedge = false;
    private RelativeInternalType internalType = null;

    private List<TransBusType> transBusTypes;
    private String channelAccNo;
    private String cusAccNo;
    private BigDecimal redemptionAmount;
    private String productRemark;
    private String isinId;
    private String relativeSecType;
    private Integer cusTemplateId;
}
