package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.batchdata.BatchData;
import com.bu.admin.feign.bo.transaction.EquTradeConfig;
import com.bu.admin.feign.bo.transaction.Transaction;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "gj-product-center", contextId = "transConfigClient")
public interface TransConfigClient {

    @ApiOperation(value = "获取Equ费率配置信息", notes = "获取Equ费率配置信息")
    @PostMapping(value = "/server/transConfig/getEquTradingConfigList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getEquTradingConfigList(@RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "更新Equ费率配置信息", notes = "更新Equ费率配置信息")
    @PostMapping(value = "/server/transConfig/modifyEquTradingFeeConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyEquTradingFeeConfig(@RequestBody BatchData batchData, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

}
