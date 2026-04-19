package com.bu.admin.feign.bo.trade;


import com.bu.admin.feign.enumtype.common.AccountTypeEnum;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.common.FinanceAccountType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/3/10.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FundsAccount extends Account implements Serializable {
    public FundsAccount(){
        this.setAccountType(AccountTypeEnum.FINANCE_ACC);
    }

    public FundsAccount(String userId, String userName, String belongUserName, FinanceAccountType financeAccountType, CurrencyType ccy){
        this.setUserId(userId);
        this.setUserName(userName);
        this.setBelongUserName(belongUserName);
        this.financeAccountType = financeAccountType;
        super.setCcy(ccy);
        this.setAccountType(AccountTypeEnum.FINANCE_ACC);
    }
    private BigDecimal accountBalance;
    private BigDecimal frozenAmount;
    private BigDecimal usableAmount;
    private String secondStatus;
    private String payPassword;
    private BigDecimal gatherAmount;
    private String belongUserName;
    private String orderByName;
    private FinanceAccountType financeAccountType;


}
