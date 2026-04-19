package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.transaction.Transaction;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gj-product-center", contextId = "transValuationRpcClient")
public interface TransValuationRpcClient {

    @ApiOperation(value = "获取估值列表", notes = "获取估值列表")
    @PostMapping(value = "/server/transValuation/findTransValuationSummaryList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findTransValuationSummaryList(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "生成单日订单估值", notes = "生成单日订单估值")
    @PostMapping(value = "/server/transValuation/generateTransValuationByDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String generateTransValuationByDate(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

}
