package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.transaction.TransFinancing;
import com.bu.admin.feign.bo.transaction.TransHedgeIrs;
import com.bu.admin.feign.bo.transaction.Transaction;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gj-product-center", contextId = "transFinancingClient")
public interface TransFinancingClient {

    @ApiOperation(value = "新建Hedge融资", notes = "新建Hedge融资")
    @PostMapping(value = "/server/transFinancing/newTransHedgeFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newTransHedgeFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新建IRS融资", notes = "新建IRS融资")
    @PostMapping(value = "/server/transFinancing/newTransHedgeIrs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newTransHedgeIrs(@RequestBody TransHedgeIrs transHedgeIrs, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新建FTP融资", notes = "新建FTP融资")
    @PostMapping(value = "/server/transFinancing/newFtpFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newFtpFinancing(@RequestBody TransFinancing transFinancing,@RequestHeader("MiServerName") String applicationName,@RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取Hedge融资列表", notes = "获取Hedge融资列表")
    @GetMapping(value = "/server/transFinancing/getTransHedgeFinancingList", produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransHedgeFinancingList(@RequestBody TransFinancing transFinancing, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取融资列表", notes = "获取融资列表")
    @GetMapping(value = "/server/transFinancing/getTransFinancingList", produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransFinancingList(@RequestBody TransFinancing transFinancing, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取IRS详细", notes = "获取IRS详细")
    @GetMapping(value = "/server/transFinancing/getTransHedgeIrs", produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransHedgeIrs(@RequestBody TransHedgeIrs transHedgeIr, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取FTP融资列表", notes = "获取FTP融资列表")
    @GetMapping(value = "/server/transFinancing/getFtpFinancingPage", produces = MediaType.APPLICATION_JSON_VALUE)
    String getFtpFinancingPage(@RequestBody TransFinancing transFinancing, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新建订单融资", notes = "新建订单融资")
    @PostMapping(value = "/server/transFinancing/newTransFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String newTransFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改订单融资", notes = "修改订单融资")
    @PostMapping(value = "/server/transFinancing/updateTransFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateTransFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除订单融资", notes = "删除订单融资")
    @PostMapping(value = "/server/transFinancing/delTransFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delTransFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取订单融资信息", notes = "获取订单融资信息")
    @PostMapping(value = "/server/transFinancing/getTransFinancingInfo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getTransFinancingInfo(@RequestBody Transaction transaction, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除IRS", notes = "删除IRS")
    @PostMapping(value = "/server/transFinancing/deleteHedgeIrs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteHedgeIrs(@RequestBody TransHedgeIrs transHedgeIrs,@RequestHeader("MiServerName") String applicationName,@RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除通用融资", notes = "删除通用融资")
    @PostMapping(value = "/server/transFinancing/deleteGeneralFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteGeneralFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

}
