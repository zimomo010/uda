package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.transaction.TransTemplate;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "gj-product-center", contextId = "transTemplateRpcClient")
public interface TransTemplateRpcClient {

    @ApiOperation(value = "获取组合关联模板列表", notes = "获取组合关联模板列表")
    @PostMapping(value = "/server/transTemplate/getTransTemplateList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransTemplateList(@RequestBody TransTemplate transTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "维护关联组合模板", notes = "维护关联组合模板")
    @PostMapping(value = "/server/transTemplate/addOrModifyTransTemplate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String addOrModifyTransTemplate(@RequestBody TransTemplate transTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取组合模板详细", notes = "获取组合模板详细")
    @PostMapping(value = "/server/transTemplate/getTransTemplateDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransTemplateDetail(@RequestBody TransTemplate transTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "按照模版生成组合文档", notes = "按照模版生成组合文档")
    @PostMapping(value = "/server/transTemplate/generateFile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String generateFile(@RequestBody TransTemplate transTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

}
