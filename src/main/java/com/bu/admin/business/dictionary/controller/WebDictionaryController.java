package com.bu.admin.business.dictionary.controller;


import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.system.Dictionary;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/web/dictionary")
@WsOperation("字典数据管理")
public class WebDictionaryController {


    @Resource
    private ProductCenterClient orderCenterClient;

    @ApiOperation(value = "获取指定字典表值", notes = "获取指定字典表值")
    @GetMapping(value = "/getDictionariesByTypeCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取指定字典表值", ignoreLogFields = {"Authorization"}, requiredParams = {"typeCode"})
    public String getDictionariesByTypeCode(@ModelAttribute Dictionary dictionary,
                                            @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.getDictionariesByTypeCode(dictionary,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取字典类型集合", notes = "获取字典类型集合")
    @GetMapping(value = "/getTypeCodes", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取字典类型集合", ignoreLogFields = {"Authorization"}, requiredParams = {"typeCode"})
    public String getTypeCodes(@ModelAttribute Dictionary dictionary,
                               @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.getTypeCodes(dictionary,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "查询字典列表", notes = "查询字典列表")
    @GetMapping(value = "/findDictionaries", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "查询字典列表", ignoreLogFields = {"Authorization"})
    public String findDictionaries(@ModelAttribute Dictionary dictionary,
                                   @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.findDictionaries(dictionary,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取字典详细", notes = "获取字典详细")
    @GetMapping(value = "/getDictionaryById", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取字典详细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getDictionaryById(@ModelAttribute Dictionary dictionary,
                                     @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.getDictionaryById(dictionary,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "新增字典", notes = "新增字典")
    @PostMapping(value = "/newDictionary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新增字典", ignoreLogFields = {"Authorization"}, requiredParams = {"dictionaryCode", "dictionaryName", "typeCode"})
    public String newDictionary(@RequestBody Dictionary dictionary,
                                @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        dictionary.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.newDictionary(dictionary,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改字典", notes = "修改字典")
    @PostMapping(value = "/modifyDictionary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改字典", ignoreLogFields = {"Authorization"}, requiredParams = {"id","dictionaryCode", "dictionaryName", "typeCode"})
    public String modifyDictionary(@RequestBody Dictionary dictionary,
                                   @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        dictionary.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.modifyDictionary(dictionary,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除字典", notes = "删除字典")
    @PostMapping(value = "/delDictionary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除字典", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String delDictionary(@RequestBody Dictionary dictionary,
                                @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        dictionary.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.delDictionary(dictionary,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "恢复字典", notes = "恢复字典")
    @PostMapping(value = "/restoreDictionary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "恢复字典", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String restoreDictionary(@RequestBody Dictionary dictionary,
                                @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        dictionary.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.restoreDictionary(dictionary,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

}
