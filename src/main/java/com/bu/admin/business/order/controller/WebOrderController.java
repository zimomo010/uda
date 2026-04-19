package com.bu.admin.business.order.controller;


import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.order.utils.TransUtils;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.feign.bo.pnl.PnlQueryBo;
import com.bu.admin.feign.bo.product.ProductBasic;
import com.bu.admin.feign.bo.product.ProductNotional;
import com.bu.admin.feign.bo.trade.Trade;
import com.bu.admin.feign.bo.transaction.*;
import com.bu.admin.feign.enumtype.product.ProductDealType;
import com.bu.admin.feign.service.*;
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
@RequestMapping("/web/order")
@WsOperation("运营订单管理")
public class WebOrderController {

    @Resource
    private ProductCenterClient productCenterClient;
    @Resource
    private TransFinancingClient transFinancingClient;
    @Resource
    private TransEventClient transEventClient;
    @Resource
    private TradeCenterClient tradeCenterClient;
    @Resource
    private TransValuationRpcClient transValuationClient;

    @ApiOperation(value = "获取订单列表", notes = "获取订单列表")
    @PostMapping(value = "/getTransactionList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单列表", ignoreLogFields = {"Authorization"})
    public String getTransactionList(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        if (null == transaction.getDealType()) {
            TransUtils.addNegateDealType(transaction, ProductDealType.TRS, ProductDealType.VIRTUAL);
        }
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTransactionList(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取订单详细", notes = "获取订单详细")
    @GetMapping(value = "/getTransactionDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单详细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransactionDetail(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(productCenterClient.getTransactionDetail(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取Repo订单详细", notes = "获取Repo订单详细")
    @GetMapping(value = "/getRepoTransactionDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取Repo订单详细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getRepoTransactionDetail(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(productCenterClient.getRepoTransactionDetail(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }


    @ApiOperation(value = "获取订单证券仓位", notes = "获取订单证券仓位")
    @GetMapping(value = "/getTransactionSecurity", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单证券仓位", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransactionSecurity(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(productCenterClient.getTransactionSecurity(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取订单证券详细", notes = "获取订单证券详细")
    @GetMapping(value = "/getBondTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单证券详细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getBondTransaction(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(productCenterClient.getBondTransaction(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "订单证券交易扩展信息修改", notes = "订单证券交易扩展信息修改")
    @PostMapping(value = "/batchTransSecLogExtendModify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "订单证券交易扩展信息修改", ignoreLogFields = {"Authorization"})
    public String batchTransSecLogExtendModify(@RequestBody BatchTransSecurityLog batchTransSecurityLog, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        batchTransSecurityLog.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.batchTransSecLogExtendModify(batchTransSecurityLog,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "获取订单外汇仓位", notes = "获取订单外汇仓位")
    @GetMapping(value = "/getTransactionFxPosition", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单汇率仓位", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransactionFxPosition(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(productCenterClient.getTransactionFxPosition(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取订单外汇明细", notes = "获取订单外汇明细")
    @GetMapping(value = "/getFxTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单外汇明细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getFxTransaction(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setPageSize(20000);
        return Response.ok(productCenterClient.getFxTransaction(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }


    @ApiOperation(value = "获取订单现金流明细", notes = "获取订单现金流明细")
    @GetMapping(value = "/getTransactionCashFlow", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单现金流明细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransactionCashFlow(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setPageSize(20000);
        return Response.ok(productCenterClient.getTransactionCashFlow(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "现金流批量更新", notes = "现金流批量更新")
    @PostMapping(value = "/batchTransCashFlowModify", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "现金流批量更新", ignoreLogFields = {"Authorization"})
    public String batchTransCashFlowModify(@RequestBody BatchCashFlow batchCashFlow, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        batchCashFlow.setUpdateUser(userId);
        return Response.ok(productCenterClient.batchTransCashFlowModify(batchCashFlow,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "更新交易记录用户备注", notes = "更新交易记录用户备注")
    @PostMapping(value = "/updateTradeCusRemark", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "更新交易记录用户备注", ignoreLogFields = {"Authorization"}, requiredParams = {"tradeId"})
    public String updateTradeCusRemark(@RequestBody Trade trade, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        trade.setUpdateUser(userId);
        return Response.ok(tradeCenterClient.updateTradeCusRemark(trade,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "删除证券交易", notes = "删除证券交易")
    @PostMapping(value = "/delSecurityTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除证券交易", ignoreLogFields = {"Authorization"}, requiredParams = {"trlId"})
    public String delSecurityTransaction(@RequestBody TransSecurity transSecurity, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transSecurity.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.delSecurityTransaction(transSecurity,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除外汇交易", notes = "删除外汇交易")
    @PostMapping(value = "/delFxTransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除外汇交易", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "outTradeId"})
    public String delFxTransaction(@RequestBody TransRelative transRelative, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transRelative.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.delFxTransaction(transRelative,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "现金流删除", notes = "现金流删除")
    @PostMapping(value = "/delCashFlow", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "现金流删除", ignoreLogFields = {"Authorization"}, requiredParams = {"tradeId", "tradeSn"})
    public String delCashFlow(@RequestBody CashFlow cashFlow, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        cashFlow.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.delCashFlow(cashFlow,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "新建订单融资", notes = "新建订单融资")
    @PostMapping(value = "/newTransFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新建订单融资", requiredParams = {"transId"}, ignoreLogFields = {"Authorization"})
    public String newTransFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transFinancing.setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.newTransFinancing(transFinancing,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改订单融资", notes = "修改订单融资")
    @PostMapping(value = "/updateTransFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改订单融资", requiredParams = {"id"}, ignoreLogFields = {"Authorization"})
    public String updateTransFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transFinancing.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.updateTransFinancing(transFinancing,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除订单融资", notes = "删除订单融资")
    @PostMapping(value = "/delTransFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除订单融资", requiredParams = {"id", "groupId"}, ignoreLogFields = {"Authorization"})
    public String delTransFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transFinancing.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.delTransFinancing(transFinancing,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取订单融资信息", notes = "获取订单融资信息")
    @GetMapping(value = "/getTransFinancingInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单融资信息", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransFinancingInfo(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(transFinancingClient.getTransFinancingInfo(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }


    @ApiOperation(value = "计算票据面值", notes = "计算票据面值")
    @PostMapping(value = "/updateProductBasicNotional", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "计算票据面值", requiredParams = {"id"}, ignoreLogFields = {"Authorization"})
    public String updateProductBasicNotional(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.updateProductBasicNotional(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改票据面值", notes = "修改票据面值")
    @PostMapping(value = "/modifyProductBasicNotional", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改票据面值", requiredParams = {"productId", "amount", "effectiveDate"}, ignoreLogFields = {"Authorization"})
    public String modifyProductBasicNotional(@RequestBody ProductNotional productNotional, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        productNotional.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.modifyProductBasicNotional(productNotional,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改票据发行日", notes = "修改票据发行日")
    @PostMapping(value = "/modifyProductBasicIssueDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改票据发行日", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "issueDate"})
    public String modifyProductBasicIssueDate(@RequestBody ProductBasic productBasic, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        productBasic.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.modifyProductBasicIssueDate(productBasic,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "修改票据到期日", notes = "修改票据到期日")
    @PostMapping(value = "/modifyProductBasicMaturityDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改票据到期日", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "maturityDate"})
    public String modifyProductBasicMaturityDate(@RequestBody ProductBasic productBasic, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        productBasic.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.modifyProductBasicMaturityDate(productBasic,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改票据NoteId", notes = "修改票据NoteId")
    @PostMapping(value = "/modifyProductBasicNoteId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改票据NoteId", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "productSn"})
    public String modifyProductBasicNoteId(@RequestBody ProductBasic productBasic, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        productBasic.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.modifyProductBasicNoteId(productBasic,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改票据持有人", notes = "修改票据持有人")
    @PostMapping(value = "/modifyTransNoteHolder", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改票据持有人", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "channelAccNo"})
    public String modifyTransNoteHolder(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transaction.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.modifyTransNoteHolder(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改票据出资人", notes = "修改票据出资人")
    @PostMapping(value = "/modifyTransEndClient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改票据出资人", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "cusAccNo"})
    public String modifyTransEndClient(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transaction.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.modifyTransEndClient(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改跨境交易类型", notes = "修改跨境交易类型")
    @PostMapping(value = "/modifyCrossBoarderType", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改跨境交易类型", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "crossBoarderType"})
    public String modifyCrossBoarderType(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transaction.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.modifyCrossBoarderType(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改订单下单时间", notes = "修改订单下单时间")
    @PostMapping(value = "/modifyTransDeliverDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改订单下单时间", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "securityConfirmDate"})
    public String modifyTransDeliverDate(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transaction.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.securityDeliveryConfirm(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "修改销售所有权", notes = "修改销售所有权")
    @PostMapping(value = "/modifySalesOwnership", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改销售所有权", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "salesDepartCode"})
    public String modifySalesOwnership(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transaction.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.updateSalesOwnership(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "修改最终确认日", notes = "修改最终确认日")
    @PostMapping(value = "/modifyTransFundsConfirmDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改最终确认日", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "fundsConfirmDate"})
    public String modifyTransFundsConfirmDate(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transaction.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.fundsDeliveryConfirm(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "迁移完成确认", notes = "迁移完成确认")
    @PostMapping(value = "/transMigrateFinish", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "迁移完成确认", requiredParams = {"id", "transSn"}, ignoreLogFields = {"Authorization"})
    public String transMigrateFinish(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.transMigrateFinish(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除订单", notes = "删除订单")
    @PostMapping(value = "/delTransaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除订单", requiredParams = {"id"}, ignoreLogFields = {"Authorization"})
    public String delTransaction(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        transaction.setUpdateUser(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.delTransaction(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取估值列表", notes = "获取估值列表")
    @GetMapping(value = "/findTransValuationSummaryList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取估值列表", ignoreLogFields = "Authorization", requiredParams = {"id"})
    public String findTransValuationSummaryList(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transValuationClient.findTransValuationSummaryList(transaction, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "生成单日订单估值", notes = "生成单日订单估值")
    @PostMapping(value = "/generateTransValuationByDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "生成单日订单估值", requiredParams = {"id", "valueDate"}, ignoreLogFields = {"Authorization"})
    public String generateTransValuationByDate(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transValuationClient.generateTransValuationByDate(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "获取票据统计信息", notes = "获取票据统计信息")
    @GetMapping(value = "/getNoteSummary", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取票据统计信息", ignoreLogFields = "Authorization", requiredParams = {"id"})
    public String getNoteSummary(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getNoteSummary(transaction, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "报告下载")
    @GetMapping(value = "/generateFileFromTrans")
    @WsOperation(value = "报告下载", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "reportType", "reportFileType"})
    public String generateFileFromTrans(@Context HttpServletResponse response,
                                        @ModelAttribute Transaction transaction,
                                        @RequestHeader("Authorization") String token) {
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
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTransFeeList(transFee, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取订单账户币种列表", notes = "获取订单账户币种列表")
    @GetMapping(value = "/getTransAccCcyList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单账户币种列表", ignoreLogFields = "Authorization", requiredParams = {"id"})
    public String getTransAccCcyList(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTransAccCcyList(transaction, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "新建费用", notes = "新建费用")
    @PostMapping(value = "/newTransFee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新建费用", requiredParams = {"transId"}, ignoreLogFields = {"Authorization"})
    public String newTransFee(@RequestBody TransFee transFee, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transFee.setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.newTransFee(transFee,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "更新费用", notes = "更新费用")
    @PostMapping(value = "/updateTransFee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "更新费用", requiredParams = {"transId", "id"}, ignoreLogFields = {"Authorization"})
    public String updateTransFee(@RequestBody TransFee transFee, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transFee.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.updateTransFee(transFee,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除费用", notes = "删除费用")
    @PostMapping(value = "/deleteFeeById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除费用", requiredParams = {"transId", "id"}, ignoreLogFields = {"Authorization"})
    public String deleteFeeById(@RequestBody TransFee transFee, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transFee.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.deleteFeeById(transFee,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取调整列表", notes = "获取调整列表")
    @GetMapping(value = "/getTransValuationAdjustList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取调整列表", requiredParams = {"transId"}, ignoreLogFields = {"Authorization"})
    public String getTransValuationAdjustList(@ModelAttribute TransValuationAdjust adjust, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTransValuationAdjustList(adjust,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "新建调整", notes = "新建调整")
    @PostMapping(value = "/newTransValuationAdjust", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新建调整", requiredParams = {"transId"}, ignoreLogFields = {"Authorization"})
    public String newTransValuationAdjust(@RequestBody TransValuationAdjust adjust, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        adjust.setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.newTransValuationAdjust(adjust,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除调整", notes = "删除调整")
    @PostMapping(value = "/delTransValuationAdjust", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除调整", requiredParams = {"id"}, ignoreLogFields = {"Authorization"})
    public String delTransValuationAdjust(@RequestBody TransValuationAdjust adjust, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        adjust.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.delTransValuationAdjust(adjust,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取订单position", notes = "获取订单position")
    @GetMapping(value = "/getTransactionPositionSummary", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单position", requiredParams = {"id"}, ignoreLogFields = {"Authorization"})
    public String getTransactionPositionSummary(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTransactionPositionSummary(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "确认票据赎回", notes = "确认票据赎回")
    @PostMapping(value = "/confirmRedemption", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "确认票据赎回", requiredParams = {"id"}, ignoreLogFields = {"Authorization"})
    public String confirmRedemption(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transaction.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.confirmRedemption(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "更新票据支付", notes = "更新票据支付")
    @PostMapping(value = "/updateTransPayment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "更新票据支付", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String updateTransPayment(@RequestBody TransPayment transPayment, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transPayment.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.updateTransPayment(transPayment,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取支付列表", notes = "获取支付列表")
    @GetMapping(value = "/getTransPaymentList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取支付列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String getTransPaymentList(@ModelAttribute TransPayment transPayment, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTransPaymentList(transPayment,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取债券付息列表", notes = "获取债券付息列表")
    @GetMapping(value = "/getTSCouponList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取债券付息列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String getTSCouponList(@ModelAttribute TSCouponPay tsCouponPay, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTSCouponList(tsCouponPay,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取用户总仓位列表", notes = "获取用户总仓位列表")
    @GetMapping(value = "/findTransSecurityPositionList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取用户总仓位列表", ignoreLogFields = {"Authorization"}, ignoreLogOutputFields = {"dataList"})
    public String findTransSecurityPositionList(@ModelAttribute TransSecurityPosition transSecurityPosition, @RequestHeader("Authorization") String token) {
        return Response.ok(productCenterClient.findTransSecurityPositionList(transSecurityPosition,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "更新用户债券风险设置", notes = "更新用户债券风险设置")
    @PostMapping(value = "/saveTransSecurityPosition", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "更新用户债券风险设置", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String saveTransSecurityPosition(@RequestBody TransSecurityPosition transSecurityPosition, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transSecurityPosition.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.saveTransSecurityPosition(transSecurityPosition,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "新建Hedge融资", notes = "新建Hedge融资")
    @PostMapping(value = "/newTransHedgeFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新建Hedge融资", requiredParams = {"transId"}, ignoreLogFields = {"Authorization"})
    public String newTransHedgeFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transFinancing.setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.newTransHedgeFinancing(transFinancing,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "新建IRS融资", notes = "新建IRS融资")
    @PostMapping(value = "/newTransHedgeIrs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新建IRS融资", requiredParams = {"transId"}, ignoreLogFields = {"Authorization"})
    public String newTransHedgeIrs(@RequestBody TransHedgeIrs transHedgeIrs, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transHedgeIrs.setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.newTransHedgeIrs(transHedgeIrs,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取Hedge融资列表", notes = "获取Hedge融资列表")
    @GetMapping(value = "/getTransHedgeFinancingList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取Hedge融资列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String getTransHedgeFinancingList(@ModelAttribute TransFinancing transFinancing, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.getTransHedgeFinancingList(transFinancing,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取融资列表", notes = "获取融资列表")
    @GetMapping(value = "/getTransFinancingList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取融资列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String getTransFinancingList(@ModelAttribute TransFinancing transFinancing, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.getTransFinancingList(transFinancing,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取IRS详细", notes = "获取IRS详细")
    @GetMapping(value = "/getTransHedgeIrs", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取IRS详细", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String getTransHedgeIrs(@ModelAttribute TransHedgeIrs transHedgeIrs, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.getTransHedgeIrs(transHedgeIrs,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取票据最后扩资记录", notes = "获取票据最后扩资记录")
    @GetMapping(value = "/getLatestNotional", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取票据最后扩资记录", ignoreLogFields = {"Authorization"}, requiredParams = {"id","productSn"})
    public String getLatestNotional(@ModelAttribute Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(productCenterClient.getLatestNotional(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "更新票据最后扩资信息", notes = "更新票据最后扩资信息")
    @PostMapping(value = "/addLatestNotionalTradeDate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "更新票据最后扩资信息", requiredParams = {"id","transDate"}, ignoreLogFields = {"Authorization"})
    public String addLatestNotionalTradeDate(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transaction.setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.addLatestNotionalTradeDate(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除IRS", notes = "删除IRS")
    @PostMapping(value = "/deleteHedgeIrs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除IRS", requiredParams = {"transId"}, ignoreLogFields = {"Authorization"})
    public String deleteHedgeIrs(@RequestBody TransHedgeIrs transHedgeIrs, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transHedgeIrs.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.deleteHedgeIrs(transHedgeIrs,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "新建FTP融资", notes = "新建FTP融资")
    @PostMapping(value = "/newFtpFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新建FTP融资", requiredParams = {"financingType"}, ignoreLogFields = {"Authorization"})
    public String newFtpFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transFinancing.setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.newFtpFinancing(transFinancing,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取FTP融资列表", notes = "获取FTP融资列表")
    @GetMapping(value = "/getFtpFinancingPage", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取FTP融资列表", ignoreLogFields = {"Authorization"})
    public String getFtpFinancingPage(@ModelAttribute TransFinancing transFinancing, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.getFtpFinancingPage(transFinancing,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除通用融资", notes = "删除通用融资")
    @PostMapping(value = "/deleteGeneralFinancing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除通用融资", requiredParams = {"id"}, ignoreLogFields = {"Authorization"})
    public String deleteGeneralFinancing(@RequestBody TransFinancing transFinancing, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transFinancing.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transFinancingClient.deleteGeneralFinancing(transFinancing,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取现金流统计明细", notes = "获取现金流统计明细")
    @PostMapping(value = "/getCashFlowStatsDetailList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取现金流统计明细", ignoreLogFields = {"Authorization"}, requiredParams = {"valueDate"})
    public String getCashFlowStatsDetailList(@RequestBody CashFlowStats cashFlowStats, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getCashFlowStatsDetailList(cashFlowStats,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取现金流统计概要", notes = "获取现金流统计概要")
    @PostMapping(value = "/getCashFlowStatsSummaryList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取现金流统计概要", ignoreLogFields = {"Authorization"}, requiredParams = {"valueDate"})
    public String getCashFlowStatsSummaryList(@RequestBody CashFlowStats cashFlowStats, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getCashFlowStatsSummaryList(cashFlowStats,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改现金流期初余额", notes = "修改现金流期初余额")
    @PostMapping(value = "/modifyCashFlowOpeningBalance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改现金流期初余额", ignoreLogFields = {"Authorization"}, requiredParams = {"valueDate", "currency", "openingBalance"})
    public String modifyCashFlowOpeningBalance(@RequestBody CashFlowStats cashFlowStats, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        cashFlowStats.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.modifyCashFlowOpeningBalance(cashFlowStats,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取订单提前赎回价格列表", notes = "获取订单提前赎回价格列表")
    @GetMapping(value = "/getTransCallPriceList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单提前赎回价格列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String getTransCallPriceList(@ModelAttribute TransCallPrice transCallPrice, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transEventClient.getTransCallPriceList(transCallPrice,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改提前赎回信息", notes = "修改提前赎回信息")
    @PostMapping(value = "/modifyTransCallPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改提前赎回信息", requiredParams = {"id","endDate"}, ignoreLogFields = {"Authorization"})
    public String modifyTransCallPrice(@RequestBody TransCallPrice transCallPrice, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transCallPrice.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transEventClient.modifyTransCallPrice(transCallPrice,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取组合信用信息", notes = "获取组合信用信息")
    @GetMapping(value = "/getTransCredit", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取组合信用信息", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String getTransCredit(@ModelAttribute TransCredit transCredit, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(transEventClient.getTransCredit(transCredit,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "更改关联证券信息", notes = "更改关联证券信息")
    @PostMapping(value = "/updateTransRef", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "更改关联证券信息", ignoreLogFields = {"Authorization"}, requiredParams = {"transId", "referenceIsin"})
    public String updateTransRef(@RequestBody TransCredit transCredit, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transCredit.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transEventClient.updateTransRef(transCredit,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取估值信息", notes = "获取估值信息")
    @PostMapping(value = "/getTransactionValuation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取估值信息", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getTransactionValuation(@RequestBody Transaction transaction, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTransactionValuation(transaction,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "发送估值数据", notes = "发送估值数据")
    @PostMapping(value = "/sendTransactionValuation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "发送估值数据", ignoreLogFields = {"Authorization"}, requiredParams = {"priceDate", "isin", "timeStr", "navRate"})
    public String sendTransactionValuation(@RequestBody TransValuationParams transValuationParams, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transValuationParams.setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.sendTransactionValuation(transValuationParams,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改融资浮动费率", notes = "修改融资浮动费率")
    @PostMapping(value = "/updateFinancingFloatingRate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改融资浮动费率", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "floatingRate"})
    public String updateFinancingFloatingRate(@RequestBody TransFinancing transFinancing, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.updateFinancingFloatingRate(transFinancing,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "PNL报告下载")
    @PostMapping(value = "/generateFileFromPnl")
    @WsOperation(value = "PNL报告下载", ignoreLogFields = {"Authorization"}, requiredParams = {"pnlType"})
    public String generateFileFromPnl(@Context HttpServletResponse response,
                                      @RequestBody PnlQueryBo pnlQueryBo,
                                      @RequestHeader("Authorization") String token) {

        pnlQueryBo.setUpdateUser(WsContext.getContext().getUserId());
        JsonObject result = ResponseCheckUtils.checkResponse(productCenterClient.generateFileFromPnl(pnlQueryBo, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).getAsJsonObject();
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

}
