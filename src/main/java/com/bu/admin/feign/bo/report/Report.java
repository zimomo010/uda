package com.bu.admin.feign.bo.report;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.bo.pnl.PnlQueryBo;
import com.bu.admin.feign.bo.transaction.Transaction;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.product.ProductDealType;
import com.bu.admin.feign.enumtype.report.ReportType;
import com.bu.admin.feign.enumtype.report.RequestUserType;
import com.bu.admin.feign.enumtype.transaction.RelativeInternalType;
import com.bu.admin.feign.enumtype.transaction.TransBusType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.list.GrowthList;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * user task po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Report extends BaseBo implements Serializable {

    private String cusId;

    private String channelId;

    private List<Transaction> transactionList = new GrowthList<>();

    private ReportType reportType;

    private String startDate;

    private String endDate;

    private String reportUrl;

    private String reportSn;

    private CurrencyType ccy;

    private ProductDealType dealType;       //产品类型

    private RelativeInternalType internalType;

    private List<TransBusType> transBusTypes;

    private Map<String, String> params = new HashMap<>();

    private PnlQueryBo pnlQueryBo;

    private RequestUserType requestUserType = RequestUserType.EMPLOYEE;

    private Integer cusTemplateId;

    private String fileName;
}
