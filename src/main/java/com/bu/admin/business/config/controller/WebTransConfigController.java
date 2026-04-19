package com.bu.admin.business.config.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.batchdata.BatchData;
import com.bu.admin.feign.bo.system.Config;
import com.bu.admin.feign.service.TransConfigClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@Api
@RestController
@RequestMapping("/web/tranConfig")
@WsOperation("交易配置管理")
public class WebTransConfigController {

    @Resource
    private TransConfigClient transConfigClient;

    @ApiOperation(value = "获取Equ费率配置信息", notes = "获取Equ费率配置信息")
    @GetMapping(value = "/getEquTradingFeeConfigList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取Equ费率配置信息", ignoreLogFields = {"Authorization"})
    public String getEquTradingFeeConfigList(@ModelAttribute Config config, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transConfigClient.getEquTradingConfigList(ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "更新Equ费率配置信息", notes = "更新Equ费率配置信息")
    @PostMapping(value = "/modifyEquTradingFeeConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "更新Equ费率配置信息", ignoreLogFields = {"Authorization"})
    public String modifyEquTradingFeeConfig(@RequestBody BatchData batchData, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transConfigClient.modifyEquTradingFeeConfig(batchData,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }



}
