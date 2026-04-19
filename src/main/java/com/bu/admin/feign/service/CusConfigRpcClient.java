package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.cusconfig.CusTemplate;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gj-product-center", contextId = "cusConfigRpcClient")
public interface CusConfigRpcClient {

    @ApiOperation(value = "获取客户报表配置列表", notes = "获取客户报表配置列表")
    @PostMapping(value = "/server/cusConfig/findCusTemplates", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findCusTemplates(@RequestBody CusTemplate cusTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取配置详情", notes = "获取配置详情")
    @PostMapping(value = "/server/cusConfig/getCusTemplate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getCusTemplate(@RequestBody CusTemplate cusTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增及修改配置", notes = "新增及修改配置")
    @PostMapping(value = "/server/cusConfig/addOrModifyCusTemplate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String addOrModifyCusTemplate(@RequestBody CusTemplate cusTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改配置状态", notes = "修改配置状态")
    @PostMapping(value = "/server/cusConfig/modifyCusTemplateStatus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyCusTemplateStatus(@RequestBody CusTemplate cusTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除配置信息", notes = "删除配置信息")
    @PostMapping(value = "/server/cusConfig/deleteCusTemplate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteCusTemplate(@RequestBody CusTemplate cusTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取报告原始字段", notes = "获取报告原始字段")
    @PostMapping(value = "/server/cusConfig/getReportOriginParams", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getReportOriginParams(@RequestBody CusTemplate cusTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "初始化数据", notes = "初始化数据")
    @PostMapping(value = "/server/cusConfig/initReportParams", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String initReportParams(@RequestBody CusTemplate cusTemplate, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

}
