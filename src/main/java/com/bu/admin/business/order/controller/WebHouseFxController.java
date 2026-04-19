package com.bu.admin.business.order.controller;


import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.transaction.Transaction;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/web/houseFx")
@WsOperation("内部换汇管理")
public class WebHouseFxController {
    private static final String HOUSE_FX = "HOUSE_FX";

    @Resource
    private ProductCenterClient productCenterClient;



    @ApiOperation(value = "获取自有外汇仓位", notes = "获取自有外汇仓位")
    @GetMapping(value = "/getTransactionFxPosition", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取自有外汇仓位", ignoreLogFields = {"Authorization"})
    public String getTransactionFxPosition(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setProductSn(HOUSE_FX);
        return Response.ok(productCenterClient.getTransactionFxPosition(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取自有外汇明细", notes = "获取自有外汇明细")
    @GetMapping(value = "/getFxTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取自有外汇明细", ignoreLogFields = {"Authorization"})
    public String getFxTransaction(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setProductSn(HOUSE_FX);
        return Response.ok(productCenterClient.getFxTransactionFroPage(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }


    @ApiOperation(value = "获取自有外汇现金流明细", notes = "获取自有外汇现金流明细")
    @GetMapping(value = "/getTransactionCashFlow", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取自有外汇现金流明细", ignoreLogFields = {"Authorization"})
    public String getTransactionCashFlow(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setProductSn(HOUSE_FX);
        return Response.ok(productCenterClient.getTransactionCashFlowFroPage(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }


}
