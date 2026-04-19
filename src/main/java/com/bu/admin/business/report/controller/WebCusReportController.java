package com.bu.admin.business.report.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.cusconfig.CusTemplate;
import com.bu.admin.feign.service.CusConfigRpcClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/web/cusReport")
@WsOperation("自定义报告")
public class WebCusReportController {

    @Value("${tcloud.localFile.store.path.template}")
    private String localFileTemplatePath;
    @Resource
    private CusConfigRpcClient cusConfigRpcClient;

    @ApiOperation(value = "获取客户报表配置列表", notes = "获取客户报表配置列表")
    @GetMapping(value = "/findCusTemplates", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取客户报表配置列表", ignoreLogFields = "Authorization")
    public String findCusTemplates(@ModelAttribute CusTemplate cusTemplate,
                                         @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        cusTemplate.setOpUserId(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(cusConfigRpcClient.findCusTemplates(cusTemplate,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取配置详情", notes = "获取配置详情")
    @GetMapping(value = "/getCusTemplate", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取配置详情",requiredParams = {"id"}, ignoreLogFields = "Authorization")
    public String getCusTemplate(@ModelAttribute CusTemplate cusTemplate,
                                   @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(cusConfigRpcClient.getCusTemplate(cusTemplate,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "新增及修改配置", notes = "新增及修改配置")
    @PostMapping(value = "/addOrModifyCusTemplate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新增及修改配置",requiredParams = {"reportType", "templateName", "downloadName", "paramsContent"}, ignoreLogFields = "Authorization")
    public String addOrModifyCusTemplate(@RequestBody CusTemplate cusTemplate, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        cusTemplate.setOpUserId(userId);
        return Response.ok(cusConfigRpcClient.addOrModifyCusTemplate(cusTemplate, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "修改配置状态", notes = "修改配置状态")
    @PostMapping(value = "/modifyCusTemplateStatus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改配置状态",requiredParams = {"id", "enableStatus"}, ignoreLogFields = "Authorization")
    public String modifyCusTemplateStatus(@RequestBody CusTemplate cusTemplate, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        cusTemplate.setOpUserId(userId);
        return Response.ok(cusConfigRpcClient.modifyCusTemplateStatus(cusTemplate, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "删除配置信息", notes = "删除配置信息")
    @PostMapping(value = "/deleteCusTemplate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除配置信息",requiredParams = {"id"}, ignoreLogFields = {"Authorization"})
    public String deleteCusTemplate(@RequestBody CusTemplate cusTemplate,
                                 @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        cusTemplate.setOpUserId(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(cusConfigRpcClient.deleteCusTemplate(cusTemplate,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取报告原始字段", notes = "获取报告原始字段")
    @GetMapping(value = "/getReportOriginParams", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取报告原始字段",requiredParams = {"reportType"}, ignoreLogFields = "Authorization")
    public String getReportOriginParams(@ModelAttribute CusTemplate cusTemplate,
                                         @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(cusConfigRpcClient.getReportOriginParams(cusTemplate,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "初始化数据", notes = "初始化数据")
    @GetMapping(value = "/initReportParams", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "初始化数据", ignoreLogFields = "Authorization")
    public String initReportParams(@ModelAttribute CusTemplate cusTemplate,
                                        @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(cusConfigRpcClient.initReportParams(cusTemplate,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


}
