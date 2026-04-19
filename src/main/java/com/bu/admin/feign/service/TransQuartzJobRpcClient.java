package com.bu.admin.feign.service;

import com.bu.admin.business.quartzjob.bo.QuartZBo;
import com.bu.admin.feign.bo.quartz.BatchDealLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gj-product-center", contextId = "transQuartzJobClient")
public interface TransQuartzJobRpcClient {

    @ApiOperation(value = "执行quartz任务", notes = "执行quartz任务")
    @PostMapping(value = "/server/quartzController/executeQuartz", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String executeQuartzJob(@RequestBody QuartZBo quartZBo, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取批处理日志列表", notes = "获取批处理日志列表")
    @PostMapping(value = "/server/batchDealLog/findBatchDealLogList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findBatchDealLogList(@RequestBody BatchDealLog batchDealLog, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取彭博数据集合", notes = "获取彭博数据集合")
    @PostMapping(value = "/server/batchDealLog/getBloomBergListFromXml", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getBloomBergListFromXml(@RequestBody BatchDealLog batchDealLog, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

}
