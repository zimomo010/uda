package com.bu.admin.business.config.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.system.Config;
import com.bu.admin.system.SystemInitService;
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
@RequestMapping("/front/config")
@WsOperation("配置管理")
public class FrontConfigController {

    @Resource
    private SystemInitService systemInitService;

    @ApiOperation(value = "获取系统版本信息", notes = "获取系统版本信息")
    @GetMapping(value = "/getApplicationVersion", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取系统版本信息", ignoreLogFields = {"Authorization"}, tokenNeeded = false)
    public String getApplicationVersion(@ModelAttribute Config config, @RequestHeader("Authorization") String token) {
        return Response.ok(systemInitService.getApplicationVersion()).build();
    }


}
