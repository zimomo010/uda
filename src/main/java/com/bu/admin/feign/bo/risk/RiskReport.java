package com.bu.admin.feign.bo.risk;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.bo.transaction.Transaction;
import com.bu.admin.feign.enumtype.report.OutputFileType;
import com.bu.admin.feign.enumtype.risk.TransRiskReportType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.list.GrowthList;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RiskReport extends BaseBo {

    private String cusId;

    private Integer transId;

    private String channelId;

    private List<Transaction> transactionList = new GrowthList<>();

    private TransRiskReportType reportType;

    private String startDate;

    private String endDate;

    private String reportUrl;

    private String fileName;

    private String reportSn;

    private OutputFileType fileType = OutputFileType.ZIP;

    private Boolean generateBusFile = false;

}
