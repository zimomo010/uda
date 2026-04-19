package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.transaction.CashFlowType;
import com.bu.admin.feign.enumtype.transaction.RelativeInternalType;
import com.bu.admin.feign.enumtype.transaction.TransType;
import com.bu.admin.utils.adapter.DateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.list.GrowthList;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * product event po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TransRelative extends BaseBo {

    private Integer id;

    private String relativeName;

    private String noteId;

    private Integer transId;                //产品id

    private CashFlowType cashFlowType;

    private String relativeSecType;

    private CurrencyType matchedCcy;  //operate ccy

    private CurrencyType currencyType;  //source ccy

    private CurrencyType currencyType1; //target ccy

    private String tradeType;           //NCT

    private BigDecimal tradeAmount;

    private BigDecimal targetCcyAmount;

    private BigDecimal tradeAmount1;

    @JsonAdapter(DateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date tradeDate;

    @JsonAdapter(DateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date valueDate;

    private BigDecimal allInRate;

    private BigDecimal spotRate;

    private String dealCode;

    private String counterparty;

    private String cusCounterparty;

    private BigDecimal clientSwapPoint = new BigDecimal(0);

    private String outTradeId;

    private BigDecimal fxRate;

    private BigDecimal referenceRate = new BigDecimal(0);

    private BigDecimal overHedgedAmt;       //额外对冲金额

    private BigDecimal unwindPnl = new BigDecimal("0");
    private BigDecimal unwindPnl1 = new BigDecimal("0");

    private RelativeInternalType internalType;
    private TransType transType = TransType.COMMON;

    private CurrencyType clientCcy;
    private BigDecimal clientNear;
    private BigDecimal clientFwd;
    private BigDecimal clientNotionalCcy1;
    private BigDecimal clientNotionalCcy2;

    List<CurrencyType> currencyTypeList = new GrowthList<>();

    private List<Integer> transIds = new GrowthList<>();

    private Date tradeEndDate;
    private Date valueEndDate;

    private Date calValueDate;
    private Date fxPositionDate;
    private String bookBelong;

    private BigDecimal fxPnlCcy1Amt = new BigDecimal(0);
    private BigDecimal fxPnlCcy2Amt = new BigDecimal(0);

    private BigDecimal swapPoint;
    private BigDecimal fxImpliedYieldCcy1 = new BigDecimal(0);
    private BigDecimal fxImpliedYieldCcy2 = new BigDecimal(0);
    private String tradeBusId;
    private String batchInputNo;            //批量导入流水号
}
