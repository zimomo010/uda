package com.bu.admin.business.order.controller;


import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.risk.RiskLine;
import com.bu.admin.feign.bo.risk.RiskReport;
import com.bu.admin.feign.bo.risk.TransRiskConfig;
import com.bu.admin.feign.bo.transaction.StressConfig;
import com.bu.admin.feign.bo.transaction.TransSecurityPosition;
import com.bu.admin.feign.bo.transaction.Transaction;
import com.bu.admin.feign.service.TransRiskRpcClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/web/transRisk")
@WsOperation("组合文件风险管理")
public class WebTransRiskController {

    @Resource
    private TransRiskRpcClient transRiskRpcClient;

    @ApiOperation(value = "获取风险列表数据", notes = "获取风险列表数据")
    @GetMapping(value = "/findReportDateList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取风险列表数据", ignoreLogFields = "Authorization", ignoreLogOutputFields = {"dataList"})
    public String findReportDataList(@ModelAttribute RiskReport riskReport, @RequestHeader("Authorization") String token) {
        return Response.ok(transRiskRpcClient.findReportDateList(riskReport, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取票据风险配置信息", notes = "获取票据风险配置信息")
    @GetMapping(value = "/getTransRiskConfig", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取票据风险配置信息", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String getTransRiskConfig(@ModelAttribute TransRiskConfig transRiskConfig, @RequestHeader("Authorization") String token) {
        return Response.ok(transRiskRpcClient.getTransRiskConfig(transRiskConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "修改票据风险配置信息", notes = "修改票据风险配置信息")
    @PostMapping(value = "/modifyTransRiskConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改票据风险配置信息", ignoreLogFields = {"Authorization"})
    public String modifyTransRiskConfig(@RequestBody TransRiskConfig transRiskConfig, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transRiskConfig.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.modifyTransRiskConfig(transRiskConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "更新债券压力测试指标", notes = "更新债券压力测试指标")
    @PostMapping(value = "/updateTransSecurityStressConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "更新债券压力测试指标", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String updateTransSecurityStressConfig(@RequestBody TransSecurityPosition transSecurityPosition, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transSecurityPosition.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.updateTransSecurityStressConfig(transSecurityPosition,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取通用压力测试指标", notes = "获取通用压力测试指标")
    @GetMapping(value = "/getCommonStressConfig", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取通用压力测试指标", ignoreLogFields = {"Authorization"})
    public String getCommonStressConfig(@RequestHeader("Authorization") String token) {
        return Response.ok(transRiskRpcClient.getCommonStressConfig(ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "更新通用压力测试指标", notes = "更新通用压力测试指标")
    @PostMapping(value = "/updateCommonStressConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "更新通用压力测试指标", ignoreLogFields = {"Authorization"})
    public String updateCommonStressConfig(@RequestBody StressConfig stressConfig, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        stressConfig.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.updateCommonStressConfig(stressConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取组合债券压力数据", notes = "获取组合债券压力数据")
    @GetMapping(value = "/getBondStressListByTransId", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取组合债券压力数据", ignoreLogFields = {"Authorization"}, requiredParams = {"transId", "startDate"})
    public String getBondStressListByTransId(@ModelAttribute RiskReport riskReport, @RequestHeader("Authorization") String token) {
        return Response.ok(transRiskRpcClient.getBondStressListByTransId(riskReport, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取仓位统计列表", notes = "获取仓位统计列表")
    @GetMapping(value = "/getTransSecPositionList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取仓位统计列表", ignoreLogFields = {"Authorization"}, requiredParams = {"start0"})
    public String getTransSecPositionList(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(transRiskRpcClient.getTransSecPositionList(transaction, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取仓位底层展示列表", notes = "获取仓位底层展示列表")
    @GetMapping(value = "/getTransSecPositionListGroupByIsin", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取仓位底层展示列表", ignoreLogFields = {"Authorization"}, requiredParams = {"start0"})
    public String getTransSecPositionListGroupByIsin(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(transRiskRpcClient.getTransSecPositionListGroupByIsin(transaction, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "更新风险线配置", notes = "更新风险线配置")
    @PostMapping(value = "/updateRiskLine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "更新风险线配置", ignoreLogFields = {"Authorization"}, requiredParams = {"type", "name"})
    public String updateRiskLine(@RequestBody RiskLine riskLine, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        riskLine.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.updateRiskLine(riskLine,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "新增风险线配置", notes = "新增风险线配置")
    @PostMapping(value = "/createRiskLine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新增风险线配置", ignoreLogFields = {"Authorization"}, requiredParams = {"type", "name"})
    public String createRiskLine(@RequestBody RiskLine riskLine, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        riskLine.setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.createRiskLine(riskLine,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除风险线配置", notes = "删除风险线配置")
    @PostMapping(value = "/deleteRiskLine", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除风险线配置", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String deleteRiskLine(@RequestBody RiskLine riskLine, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        riskLine.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.deleteRiskLine(riskLine,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "查找风险线列表", notes = "查找风险线列表")
    @GetMapping(value = "/findRiskLineList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "查找风险线列表", ignoreLogFields = "Authorization")
    public String findRiskLineList(@ModelAttribute RiskLine riskLine, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.findRiskLineList(riskLine,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "查找风险线翻页", notes = "查找风险线翻页")
    @GetMapping(value = "/findRiskLinePage", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "查找风险线翻页", ignoreLogFields = "Authorization")
    public String findRiskLinePage(@ModelAttribute RiskLine riskLine, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.findRiskLinePage(riskLine,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "查找融资监控数据", notes = "查找融资监控数据")
    @GetMapping(value = "/findFundingMonitors", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "查找融资监控数据", ignoreLogFields = "Authorization")
    public String findFundingMonitors(@ModelAttribute RiskLine riskLine, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.findFundingMonitors(riskLine,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "查找LCR监控数据", notes = "查找LCR监控数据")
    @GetMapping(value = "/findLCRMonitors", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "查找LCR监控数据", ignoreLogFields = "Authorization")
    public String findLCRMonitors(@ModelAttribute RiskLine riskLine, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.findLCRMonitors(riskLine,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "查找对手方监控数据", notes = "查找对手方监控数据")
    @GetMapping(value = "/findCptyMonitors", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "查找对手方监控数据", ignoreLogFields = "Authorization")
    public String findCptyMonitors(@ModelAttribute RiskLine riskLine, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transRiskRpcClient.findCptyMonitors(riskLine,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }
}
