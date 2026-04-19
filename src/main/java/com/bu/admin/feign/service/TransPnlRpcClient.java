package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.pnl.PnlQueryBo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gj-product-center", contextId = "transPnlRpcClient")
public interface TransPnlRpcClient {

    @ApiOperation(value = "获取组合关联模板列表", notes = "获取组合关联模板列表")
    @PostMapping(value = "/server/transPnl/findPnlList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findPnlList(@RequestBody PnlQueryBo pnlQueryBo, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);
    @ApiOperation(value = "获取组合关联模板列表", notes = "获取组合关联模板列表")
    @PostMapping(value = "/server/transPnl/findPnlListForSales", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findPnlListForSales(@RequestBody PnlQueryBo pnlQueryBo, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

}
