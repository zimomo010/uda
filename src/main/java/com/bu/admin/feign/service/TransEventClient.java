package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.transaction.TransCallPrice;
import com.bu.admin.feign.bo.transaction.TransCredit;
import com.bu.admin.feign.bo.transaction.TransSecurityLog;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gj-product-center", contextId = "transEventClient")
public interface TransEventClient {

    @ApiOperation(value = "获取订单提前赎回价格列表", notes = "获取订单提前赎回价格列表")
    @PostMapping(value = "/server/transRelative/getTransCallPriceList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransCallPriceList(@RequestBody TransCallPrice transCallPrice, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改提前赎回信息", notes = "修改提前赎回信息")
    @PostMapping(value = "/server/transRelative/modifyTransCallPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyTransCallPrice(@RequestBody TransCallPrice transCallPrice, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取组合信用信息", notes = "获取组合信用信息")
    @PostMapping(value = "/server/transSecurity/getTransCredit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransCredit(@RequestBody TransCredit transCredit, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "更改关联证券信息", notes = "更改关联证券信息")
    @PostMapping(value = "/server/transSecurity/updateTransRef", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateTransRef(@RequestBody TransCredit transCredit, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "证券到期完成操作", notes = "证券到期或行权")
    @PostMapping(value = "/server/transSecurity/transLogOperation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String transLogOperation(@RequestBody TransSecurityLog transSecurityLog, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

}
