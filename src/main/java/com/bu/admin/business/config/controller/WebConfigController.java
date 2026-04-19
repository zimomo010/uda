package com.bu.admin.business.config.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.system.Config;
import com.bu.admin.feign.service.UserCenterClient;
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
@RequestMapping("/web/config")
@WsOperation("配置管理")
public class WebConfigController {

    @Resource
    private UserCenterClient userCenterClient;

    @ApiOperation(value = "获取配置信息", notes = "获取配置信息")
    @GetMapping(value = "/getConfigList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取配置信息", ignoreLogFields = {"Authorization"})
    public String getConfigList(@ModelAttribute Config config, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(userCenterClient.getConfigList(config,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取配置详细", notes = "获取配置详细")
    @GetMapping(value = "/getConfigDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取配置详细", requiredParams = {"configId"}, ignoreLogFields = {"Authorization"})
    public String getConfigDetail(@ModelAttribute Config config, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(userCenterClient.getConfigDetailById(config,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "新增或修改配置", notes = "新增或修改配置")
    @PostMapping(value = "/addOrModifyConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新增或修改配置", requiredParams = {"configName", "configContent"}, ignoreLogFields = {"Authorization"})
    public String addOrModifyConfig(@RequestBody Config config, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        WsContext.getContext().getUserInfo().get("departCode").asString();
        config.setUserId(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(userCenterClient.addOrModifyConfig(config,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }



}
