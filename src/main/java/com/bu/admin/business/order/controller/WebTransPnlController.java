package com.bu.admin.business.order.controller;


import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.pnl.PnlQueryBo;
import com.bu.admin.feign.service.TransPnlRpcClient;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/web/transPnl")
@WsOperation("PNL管理")
public class WebTransPnlController {

    @Resource
    private TransPnlRpcClient transPnlRpcClient;


    @ApiOperation(value = "获取PNL列表数据", notes = "获取PNL列表数据")
    @PostMapping(value = "/findPnlList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取PNL列表数据", ignoreLogFields = "Authorization", requiredParams = {"pnlType","startDateStr","endDateStr"})
    public String findPnlList(@RequestBody PnlQueryBo pnlQueryBo, @RequestHeader("Authorization") String token) {
        return Response.ok(transPnlRpcClient.findPnlList(pnlQueryBo, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }
    @ApiOperation(value = "获取PNL列表数据-销售", notes = "获取PNL列表数据-销售")
    @PostMapping(value = "/findPnlListForSales", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取PNL列表数据-销售", ignoreLogFields = "Authorization", requiredParams = {"pnlType","startDateStr","endDateStr"})
    public String findPnlListForSales(@RequestBody PnlQueryBo pnlQueryBo, @RequestHeader("Authorization") String token) {
        return Response.ok(transPnlRpcClient.findPnlListForSales(pnlQueryBo, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

}
