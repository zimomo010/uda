package com.bu.admin.feign.bo.transaction;

import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.transaction.SecurityTradeType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class EquTradeConfig implements Serializable {

    private SecurityTradeType tradeType;
    private CurrencyType currencyType;
    private BigDecimal stampDuty = BigDecimal.ZERO;
    private BigDecimal transactionLevy = BigDecimal.ZERO;
    private BigDecimal ftt = BigDecimal.ZERO;
    private BigDecimal clearingFee = BigDecimal.ZERO;
    private BigDecimal accruedInterest = BigDecimal.ZERO;
    private BigDecimal tradingFee = BigDecimal.ZERO;
    private BigDecimal financialTransactionTax = BigDecimal.ZERO;
    private BigDecimal dvpSettlement = BigDecimal.ZERO;
}
