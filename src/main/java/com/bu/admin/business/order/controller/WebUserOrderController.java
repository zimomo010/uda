package com.bu.admin.business.order.controller;


import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.feign.bo.transaction.TransFee;
import com.bu.admin.feign.bo.transaction.Transaction;
import com.bu.admin.feign.enumtype.transaction.RelativeInternalType;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.service.TransFinancingClient;
import com.bu.admin.feign.service.TransValuationRpcClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Api
@RestController
@RequestMapping("/web/userOrder")
@WsOperation("用户订单管理")
public class WebUserOrderController {

    @Resource
    private ProductCenterClient productCenterClient;
    @Resource
    private TransFinancingClient transFinancingClient;
    @Resource
    private TransValuationRpcClient transValuationClient;

    @ApiOperation(value = "获取订单列表", notes = "获取订单列表")
    @PostMapping(value = "/getTransactionList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单列表", ignoreLogFields = {"Authorization"})
    public String getTransactionList(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setOpUserId(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTransactionList(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "获取订单详细", notes = "获取订单详细")
    @GetMapping(value = "/getTransactionDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单详细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransactionDetail(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setOpUserId(WsContext.getContext().getUserId());
        return Response.ok(productCenterClient.getTransactionDetail(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }


    @ApiOperation(value = "获取订单证券仓位", notes = "获取订单证券仓位")
    @GetMapping(value = "/getTransactionSecurity", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单证券仓位", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransactionSecurity(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setOpUserId(WsContext.getContext().getUserId());
        return Response.ok(productCenterClient.getTransactionSecurity(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取订单证券详细", notes = "获取订单证券详细")
    @GetMapping(value = "/getBondTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单证券详细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getBondTransaction(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setOpUserId(WsContext.getContext().getUserId());
        transaction.setInternalType(RelativeInternalType.ORIGIN);
        return Response.ok(productCenterClient.getBondTransaction(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }


    @ApiOperation(value = "获取订单外汇仓位", notes = "获取订单外汇仓位")
    @GetMapping(value = "/getTransactionFxPosition", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单汇率仓位", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransactionFxPosition(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setOpUserId(WsContext.getContext().getUserId());
        return Response.ok(productCenterClient.getTransactionFxPosition(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取订单外汇明细", notes = "获取订单外汇明细")
    @GetMapping(value = "/getFxTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单外汇明细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getFxTransaction(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setInternalType(RelativeInternalType.ORIGIN);
        transaction.setOpUserId(WsContext.getContext().getUserId());
        return Response.ok(productCenterClient.getFxTransaction(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取订单现金流明细", notes = "获取订单现金流明细")
    @GetMapping(value = "/getTransactionCashFlow", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单现金流明细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransactionCashFlow(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setOpUserId(WsContext.getContext().getUserId());
        transaction.setInternalType(RelativeInternalType.ORIGIN);
        return Response.ok(productCenterClient.getTransactionCashFlow(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取订单融资信息", notes = "获取订单融资信息")
    @GetMapping(value = "/getTransFinancingInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单融资信息", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransFinancingInfo(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setOpUserId(WsContext.getContext().getUserId());
        return Response.ok(transFinancingClient.getTransFinancingInfo(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }



    @ApiOperation(value = "获取估值列表", notes = "获取估值列表")
    @GetMapping(value = "/findTransValuationSummaryList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取估值列表", ignoreLogFields = "Authorization", requiredParams = {"id"})
    public String findTransValuationSummaryList(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setOpUserId(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(transValuationClient.findTransValuationSummaryList(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "报告下载")
    @GetMapping(value = "/generateFileFromTrans")
    @WsOperation(value = "报告下载", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "reportType", "reportFileType"})
    public String generateFileFromTrans(@Context HttpServletResponse response,
                                        @ModelAttribute Transaction transaction,
                                        @RequestHeader("Authorization") String token) {
        transaction.setOpUserId(WsContext.getContext().getUserId());
        transaction.setUpdateUser(WsContext.getContext().getUserId());
        JsonObject result = ResponseCheckUtils.checkResponse(productCenterClient.generateFileFromTrans(transaction, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).getAsJsonObject();
        File file = new File(result.get("fileUrl").getAsString());

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ResponseCheckUtils.getHeaderContentDisposition(result.get("busCode").getAsString()));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        int bytesRead;
        byte[] buffer = new byte[1024];
        try (InputStream inputStream = new FileInputStream(file)) {
            response.setHeader("Content-Length", String.valueOf(inputStream.available()));
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
            return Response.ok().build();
        } catch (Exception e) {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "");
            return Response.error(ErrorCodes.UNKNOWN_ERROR).build();
        }
    }

    @ApiOperation(value = "获取费用列表", notes = "获取费用列表")
    @GetMapping(value = "/getTransFeeList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取费用列表", ignoreLogFields = "Authorization", requiredParams = {"transId"})
    public String getTransFeeList(@ModelAttribute TransFee transFee, @RequestHeader("Authorization") String token) {
        transFee.setOpUserId(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTransFeeList(transFee,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

}
