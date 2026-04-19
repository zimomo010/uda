package com.bu.admin.feign.bo.trade;


import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.utils.DateConverterUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/3/12.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Trade extends QueryBase implements Serializable {

    private String tradeId;
    private String tradeSn;
    private String tradeName;
    private BigDecimal tradeAmount;
    private Date tradeTime;
    private Date confirmTime;
    private String businessId;
    private String userId;
    private Date expireTime;
    private String remark;
    private String startDateStr;
    private String endDateStr;
    private String confirmStartDateStr;
    private String confirmEndDateStr;
    private String startAmount;
    private String endAmount;
    private String processId;
    private String accountNumber;
    private String blockTradeId;         //用户remark字段
    private List<String> accountNumbers = new ArrayList<>();
    private List<String> tradeIds = new ArrayList<>();
    private List<String> busIdList = new ArrayList<>();
    private List<String> tradeNameList = new ArrayList<>();

    public Date getStartDate() {
        if (StringUtils.isNotBlank(this.startDateStr)) {
            if (this.startDateStr.length() < 12) {
                return DateConverterUtils.formatStringToDate(this.startDateStr + " 00:00:00", DateConverterUtils.FormatterType.FORMAT1);
            } else {
                return DateConverterUtils.formatStringToDate(this.startDateStr, DateConverterUtils.FormatterType.FORMAT1);
            }
        }
        return null;
    }

    public Date getEndDate() {
        if (StringUtils.isNotBlank(this.endDateStr)) {
            if (this.endDateStr.length() < 12) {
                return DateConverterUtils.formatStringToDate(this.endDateStr + " 23:59:59", DateConverterUtils.FormatterType.FORMAT1);
            }
            else {
                return DateConverterUtils.formatStringToDate(this.endDateStr, DateConverterUtils.FormatterType.FORMAT1);
            }
        }
        return null;
    }

}
