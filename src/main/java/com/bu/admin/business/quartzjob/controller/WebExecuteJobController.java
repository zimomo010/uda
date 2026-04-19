package com.bu.admin.business.quartzjob.controller;


import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.quartzjob.bo.QuartZBo;
import com.bu.admin.feign.bo.quartz.BatchDealLog;
import com.bu.admin.feign.service.TransQuartzJobRpcClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/web/executeJob")
@WsOperation("执行job管理")
public class WebExecuteJobController {


    @Resource
    private TransQuartzJobRpcClient transQuartzJobRpcClient;


    @ApiOperation(value = "执行任务", notes = "执行任务")
    @PostMapping(value = "/executeQuartzJob", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "执行任务", ignoreLogFields = {"Authorization"})
    public String executeQuartzJob(@RequestBody QuartZBo quartZBo,
                                @RequestHeader("Authorization") String token) {

        return Response.ok(ResponseCheckUtils.checkResponse(transQuartzJobRpcClient.executeQuartzJob(quartZBo,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取批处理日志列表", notes = "获取批处理日志列表")
    @GetMapping(value = "/findBatchDealLogList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取批处理日志列表", ignoreLogFields = {"Authorization"})
    public String findBatchDealLogList(@ModelAttribute BatchDealLog batchDealLog, @RequestHeader("Authorization") String token) {
        return Response.ok(transQuartzJobRpcClient.findBatchDealLogList(batchDealLog,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取彭博数据集合", notes = "获取彭博数据集合")
    @GetMapping(value = "/getBloomBergListFromXml", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取彭博数据集合", ignoreLogFields = {"Authorization"})
    public String getBloomBergListFromXml(@ModelAttribute BatchDealLog batchDealLog, @RequestHeader("Authorization") String token) {
        return Response.ok(transQuartzJobRpcClient.getBloomBergListFromXml(batchDealLog,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }
}
