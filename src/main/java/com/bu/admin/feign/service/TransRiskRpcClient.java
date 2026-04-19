package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.risk.RiskLine;
import com.bu.admin.feign.bo.risk.RiskReport;
import com.bu.admin.feign.bo.risk.TransRiskConfig;
import com.bu.admin.feign.bo.transaction.StressConfig;
import com.bu.admin.feign.bo.transaction.TransSecurityPosition;
import com.bu.admin.feign.bo.transaction.Transaction;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gj-product-center", contextId = "transRiskRpcClient")
public interface TransRiskRpcClient {

    @ApiOperation(value = "获取组合关联模板列表", notes = "获取组合关联模板列表")
    @PostMapping(value = "/server/riskReport/getTransRiskConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransRiskConfig(@RequestBody TransRiskConfig transRiskConfig, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改票据风险配置信息", notes = "维护关联组合模板")
    @PostMapping(value = "/server/riskReport/modifyTransRiskConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyTransRiskConfig(@RequestBody TransRiskConfig transRiskConfig, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取风险报告列表数据", notes = "获取风险报告列表数据")
    @PostMapping(value = "/server/riskReport/findReportDateList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findReportDateList(@RequestBody RiskReport riskReport, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "更新债券压力测试指标", notes = "更新债券压力测试指标")
    @PostMapping(value = "/server/transPosition/updateTransSecurityStressConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateTransSecurityStressConfig(@RequestBody TransSecurityPosition transSecurityPosition, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);
    @ApiOperation(value = "获取通用压力测试指标", notes = "获取通用压力测试指标")
    @PostMapping(value = "/server/transPosition/getCommonStressConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCommonStressConfig(@RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "更新通用压力测试指标", notes = "更新通用压力测试指标")
    @PostMapping(value = "/server/transPosition/updateCommonStressConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateCommonStressConfig(@RequestBody StressConfig stressConfig, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取组合债券压力数据", notes = "获取组合债券压力数据")
    @PostMapping(value = "/server/riskReport/getBondStressListByTransId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getBondStressListByTransId(@RequestBody RiskReport riskReport, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取仓位统计列表", notes = "获取仓位统计列表")
    @PostMapping(value = "/server/transPosition/getTransSecPositionList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransSecPositionList(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取仓位底层展示列表", notes = "获取仓位底层展示列表")
    @PostMapping(value = "/server/transPosition/getTransSecPositionListGroupByIsin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransSecPositionListGroupByIsin(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "更新风险线配置", notes = "更新风险线配置")
    @PostMapping(value = "/server/riskReport/updateRiskLine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateRiskLine(@RequestBody RiskLine riskLine, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增风险线配置", notes = "新增风险线配置")
    @PostMapping(value = "/server/riskReport/createRiskLine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String createRiskLine(@RequestBody RiskLine riskLine, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除风险线配置", notes = "删除风险线配置")
    @PostMapping(value = "/server/riskReport/deleteRiskLine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteRiskLine(@RequestBody RiskLine riskLine, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "查找风险线列表", notes = "查找风险线列表")
    @PostMapping(value = "/server/riskReport/findRiskLineList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findRiskLineList(@RequestBody RiskLine riskLine, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "查找风险线翻页", notes = "查找风险线翻页")
    @PostMapping(value = "/server/riskReport/findRiskLinePage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findRiskLinePage(@RequestBody RiskLine riskLine, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "查找融资监控数据", notes = "查找融资监控数据")
    @PostMapping(value = "/server/riskReport/findFundingMonitors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findFundingMonitors(@RequestBody RiskLine riskLine, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "查找LCR监控数据", notes = "查找LCR监控数据")
    @PostMapping(value = "/server/riskReport/findLCRMonitors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findLCRMonitors(@RequestBody RiskLine riskLine, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "查找对手方监控数据", notes = "查找对手方监控数据")
    @PostMapping(value = "/server/riskReport/findCptyMonitors", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findCptyMonitors(@RequestBody RiskLine riskLine, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);
}
