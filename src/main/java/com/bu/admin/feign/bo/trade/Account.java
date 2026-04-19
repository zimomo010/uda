package com.bu.admin.feign.bo.trade;


import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.common.AccountTypeEnum;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.utils.DateConverterUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 链接Value
 * @author huangrui
 * @time 2015-3-9
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Account extends QueryBase implements Serializable {

    private String accountId;
    private String userId;
    private String accountStatus;
    private BigDecimal creditAmount;
    private AccountTypeEnum accountType;
    private String accountNumber;
    private String userName;
    private String startDateStr;
    private String endDateStr;
    private CurrencyType ccy;               //币种


    public Date getStartDate() {
        if (StringUtils.isNotBlank(this.startDateStr)) {
            return DateConverterUtils.formatStringToDate(this.startDateStr + " 00:00:00", DateConverterUtils.FormatterType.FORMAT1);
        }
        return null;
    }

    public Date getEndDate() {
        if (StringUtils.isNotBlank(this.endDateStr)) {
            return DateConverterUtils.formatStringToDate(this.endDateStr + " 23:59:59", DateConverterUtils.FormatterType.FORMAT1);
        }
        return null;
    }
}
