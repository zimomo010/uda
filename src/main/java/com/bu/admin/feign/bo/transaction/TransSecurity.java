package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.transaction.CashFlowType;
import com.bu.admin.feign.enumtype.transaction.RelativeInternalType;
import com.bu.admin.feign.enumtype.transaction.TransType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * user security po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TransSecurity extends BaseBo implements Serializable {


    private Integer trlId;
    private Integer usId;
    private Integer transId;
    private String cusId;
    private String productId;
    private String productSn;
    private String secName;
    private String secIsin;
    private CurrencyType ccy;               //币种
    private String secAccountNum;
    private String counterparty;

    private BigDecimal issuerPrice;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private BigDecimal clientPrice;
    private BigDecimal numbers;
    private Date transDate;
    private Date finishDate;
    private Date asOfDate;
    private Date clientSettleDate;
    private BigDecimal originAmount;
    private BigDecimal accruedInterestAmt;
    private BigDecimal settlementAmount;
    private int statusLogIdx = 0;
    private Integer accruedInterestDays;
    private String tradeType;
    private String remark;

    private BigDecimal cpn;                     //票息
    private LocalDate prevCpnDate;              //上次付息日
    private String dayCountDesc;                //记日期类型
    private String secNameCh;                   //债券中文名
    private String bondClassificationName;      //债券分类名称
    private String industryGroup;               //行业分组
    private LocalDate maturity;                      //到期日
    private String chengTouMark;
    private LocalDate nxtCallDate;
    private BigDecimal nxtCallPx;
    private LocalDate calledDate;
    private String called;
    private String callable;

    private BigDecimal notional;
    private String outTradeSn;
    private BigDecimal cleanPrice;
    private BigDecimal dirtyPrice;
    private BigDecimal marketCleanPriceMid = new BigDecimal(0);
    private BigDecimal marketDirtyPriceMid = new BigDecimal(0);

    private BigDecimal marketTotalCleanAmt = new BigDecimal(0);
    private BigDecimal marketTotalDirtyAmt = new BigDecimal(0);

    private String tradeSn;
    private RelativeInternalType internalType;
    private String bookBelong;
    private BigDecimal remainingNumbers;                                //剩余数量
    private TransType transType = TransType.COMMON;
    private LocalDateTime valueDate;

    private CashFlowType cashFlowType;
    private Boolean tBillMark = false;
    private Boolean skipMark = false;
    private Integer dayCount;
    private String tradeBusId;                 //交易号/批处理管理交易号
    private String batchInputNo;            //批量导入流水号
    private String extendInfo;              //扩展信息
}
