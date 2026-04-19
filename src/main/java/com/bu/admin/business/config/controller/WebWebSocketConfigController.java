package com.bu.admin.business.config.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.system.Config;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName WebConfigController
 * @Description 配置管理
 * @Author ghostWu
 * @Date 2019-07-09 10:09
 * @Version 1.0
 */
@Api
@RestController
@RequestMapping("/web/webSocket/config")
@WsOperation("Reuters配置管理")
public class WebWebSocketConfigController {

    @Resource
    private ProductCenterClient productCenterClient;

    @ApiOperation(value = "openWebSocket", notes = "开websocket链接")
    @GetMapping(value = "/openWebSocket", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "重新链接路透", ignoreLogFields = {"Authorization"})
    public String getConfigList(@ModelAttribute Config config, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.openWebSocket(
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "closeWebSocket", notes = "关webSocket链接")
    @GetMapping(value = "/closeWebSocket", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "关闭链接路透", ignoreLogFields = {"Authorization"})
    public String getConfigDetail(@ModelAttribute Config config,@RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.closeWebSocket(
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }
}
