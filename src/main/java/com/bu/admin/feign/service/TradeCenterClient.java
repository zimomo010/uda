package com.bu.admin.feign.service;


import com.bu.admin.feign.bo.trade.FundsAccount;
import com.bu.admin.feign.bo.trade.Trade;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient("gj-trade-center")
public interface TradeCenterClient {

    @ApiOperation(value = "获取客户账户列表", notes = "获取客户账户列表")
    @PostMapping(value = "/server/account/getFinAccountList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getFinAccountList(@RequestBody FundsAccount fundsAccount, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取客户账户详细", notes = "获取客户账户详细")
    @PostMapping(value = "/server/account/getUserFinanceAccInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getUserFinanceAccInfo(@RequestBody FundsAccount fundsAccount, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取EAM客户账户列表", notes = "获取EAM客户账户列表")
    @PostMapping(value = "/server/account/findAccountLog", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findAccountLog(@RequestBody FundsAccount fundsAccount, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "更新交易记录用户备注", notes = "更新交易记录用户备注")
    @PostMapping(value = "/server/trade/updateTradeCusRemark", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateTradeCusRemark(@RequestBody Trade trade, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

}
