package com.bu.admin.business.order.controller;


import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.transaction.TransSecurityLog;
import com.bu.admin.feign.service.TransEventClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/web/transSecurity")
@WsOperation("证券底层交易管理")
public class WebTransSecurityController {

    @Resource
    private TransEventClient transEventClient;


    @ApiOperation(value = "证券到期完成操作", notes = "证券到期或行权")
    @PostMapping(value = "/transLogOperation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "证券到期完成操作", ignoreLogFields = {"Authorization"},requiredParams = {"id", "operationType"})
    public String transLogOperation(@RequestBody TransSecurityLog transSecurityLog, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transSecurityLog.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transEventClient.transLogOperation(transSecurityLog,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


}
