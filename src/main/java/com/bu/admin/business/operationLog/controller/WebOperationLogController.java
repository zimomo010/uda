package com.bu.admin.business.operationLog.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.operationLog.bo.OperationLog;
import com.bu.admin.business.operationLog.bo.SyncSendMessageLogBO;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.service.UserCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/web/operationLog")
@WsOperation("日志管理")
public class WebOperationLogController {

    @Resource
    private UserCenterClient userCenterClient;
    @Resource
    private ProductCenterClient productCenterClient;


    @ApiOperation(value = "获取操作日志列表", notes = "获取操作日志列表")
    @GetMapping(value = "/findOperationLogPage", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取操作日志列表", ignoreLogFields = "Authorization")
    public String findOperationLogPage(@ModelAttribute OperationLog operationLog, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(userCenterClient.findOperationLogPage(operationLog,
                        ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取同步消息日志列表", notes = "获取同步消息日志列表")
    @GetMapping(value = "/findSyncMessageLogPage", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取同步消息日志列表", ignoreLogFields = "Authorization")
    public String findSyncMessageLogPage(@ModelAttribute SyncSendMessageLogBO operationLog, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.findSyncMessageLogPage(operationLog,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }
}
