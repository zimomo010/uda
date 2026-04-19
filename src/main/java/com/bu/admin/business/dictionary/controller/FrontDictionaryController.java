package com.bu.admin.business.dictionary.controller;


import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.system.Dictionary;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/front/dictionary")
@WsOperation("字典数据管理")
public class FrontDictionaryController {


    @Resource
    private ProductCenterClient orderCenterClient;

    @ApiOperation(value = "获取指定字典表值", notes = "获取指定字典表值")
    @GetMapping(value = "/getDictionariesByTypeCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取指定字典表值", ignoreLogFields = {"Authorization"}, requiredParams = {"typeCode"},tokenNeeded = false)
    public String getDictionariesByTypeCode(@ModelAttribute Dictionary dictionary) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.getDictionariesByTypeCode(dictionary,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

}
