package com.bu.admin.feign.bo.transaction;

import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.transaction.CashFlowType;
import com.bu.admin.feign.enumtype.transaction.RelativeInternalType;
import com.bu.admin.feign.enumtype.transaction.SecurityOperationType;
import com.bu.admin.feign.enumtype.transaction.SecurityTradeType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * user security po
 *
 * @author ghostWu
 */
@Data
public class TransSecurityLog implements Serializable {


    private Integer id;
    private Integer usId;
    private String secIsin;
    private String secName;
    private CurrencyType ccy;                   //币种
    private SecurityTradeType tradeType;
    private Integer transId;
    private BigDecimal numbers;
    private LocalDateTime transDate;
    private LocalDateTime finishDate;
    private LocalDateTime asOfDate;
    private LocalDateTime clientSettleDate;
    private BigDecimal price;
    private BigDecimal cleanPrice;
    private BigDecimal dirtyPrice;
    private BigDecimal settlementAmount;
    private BigDecimal accruedInterestAmt;
    private Integer  accruedInterestDays;
    private BigDecimal dirtyAmount;
    private String counterparty;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String outTradeSn;

    private RelativeInternalType internalType;

    private BigDecimal remainingNumbers;                //剩余数量
    private CashFlowType cashFlowType;
    private int version = 1;

    private String batchInputNo;            //批量导入流水号
    private String updateUser;
    private SecurityOperationType operationType;
}
