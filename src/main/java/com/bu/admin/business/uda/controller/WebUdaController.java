package com.bu.admin.business.uda.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.uda.bo.SaasUser;
import com.bu.admin.business.uda.bo.UdaVideo;
import com.bu.admin.business.uda.service.UdaService;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.utils.JSONUtils;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Api
@RestController
@RequestMapping("/web/uda")
@WsOperation("youtube data analysis")
public class WebUdaController {

    @Resource
    private UdaService udaService;

    @ApiOperation(value = "syncUdaData", notes = "Sync uda data")
    @GetMapping(value = "/syncUdaData", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "syncUdaData", ignoreLogFields = "Authorization", tokenNeeded = false)
    public String syncUdaData(@RequestHeader("Authorization") String token) {
        udaService.syncUdaData();
        return Response.ok().build();
    }

    @ApiOperation(value = "videoTagCount", notes = "Sync uda data")
    @GetMapping(value = "/videoTagCount", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "videoTagCount", ignoreLogFields = "Authorization", tokenNeeded = false)
    public String videoTagCount(@RequestHeader("Authorization") String token) {
        udaService.videoTagCount();
        return Response.ok().build();
    }

    @ApiOperation(value = "login", notes = "login")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "login", requiredParams = {"userName", "password"}, ignoreLogFields = {"Authorization"}, tokenNeeded = false)
    public String login(@RequestBody SaasUser saasUser, @RequestHeader("Authorization") String token) {
        if (saasUser.getUserName().equalsIgnoreCase("uda")
                && saasUser.getPassword().equalsIgnoreCase("uda123456")) {
            return Response.ok().build();
        } else {
            return Response.error(ErrorCodes.UNKNOWN_ERROR).build();
        }
    }

    @ApiOperation(value = "getCountData", notes = "get count data")
    @GetMapping(value = "/getCountData", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "getCountData", ignoreLogFields = "Authorization", tokenNeeded = false)
    public String getCountData() {
        JsonObject result = new JsonObject();
        result.addProperty("dataList", JSONUtils.toJSONString(udaService.getCountData()));
        return Response.ok(result).build();
    }

    @ApiOperation(value = "getCategoryCountList", notes = "get category count data")
    @GetMapping(value = "/getCategoryCountList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "getCategoryCountList", requiredParams = {"areaCode"}, ignoreLogFields = "Authorization", tokenNeeded = false)
    public String getCategoryCountList(@ModelAttribute UdaVideo udaVideo) {
        JsonObject result = new JsonObject();
        result.addProperty("dataList", JSONUtils.toJSONString(udaService.getCategoryCountList(udaVideo.getAreaCode())));
        return Response.ok(result).build();
    }

    @ApiOperation(value = "getTopVideoList", notes = "get top list data")
    @GetMapping(value = "/getTopVideoList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "getTopVideoList", requiredParams = {"areaCode", "sortParam"}, ignoreLogFields = "Authorization", tokenNeeded = false)
    public String getTopVideoList(@ModelAttribute UdaVideo udaVideo) {
        JsonObject result = new JsonObject();
        result.addProperty("dataList", JSONUtils.toJSONString(udaService.getTopVideoList(udaVideo.getAreaCode(), udaVideo.getSortParam())));
        return Response.ok(result).build();
    }

    @ApiOperation(value = "getVideoTagTop", notes = "get video tag top list data")
    @GetMapping(value = "/getVideoTagTop", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "getVideoTagTop", requiredParams = {"areaCode", "categoryId"}, ignoreLogFields = "Authorization", tokenNeeded = false)
    public String getVideoTagTop(@ModelAttribute UdaVideo udaVideo) {
        return Response.ok(udaService.getVideoTagTop(udaVideo.getAreaCode(), udaVideo.getCategoryId())).build();
    }


    @ApiOperation(value = "userVideoCal", notes = "user video cal")
    @PostMapping(value = "/userVideoCal", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "userVideoCal", requiredParams = {"title", "categoryId"}, ignoreLogFields = {"Authorization"}, tokenNeeded = false)
    public String userVideoCal(@RequestBody UdaVideo udaVideo, @RequestHeader("Authorization") String token) {
        return Response.ok(udaService.userVideoCal(udaVideo)).build();
    }
}
