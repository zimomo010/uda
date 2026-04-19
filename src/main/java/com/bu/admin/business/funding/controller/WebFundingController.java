package com.bu.admin.business.funding.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.funding.*;
import com.bu.admin.feign.service.FundingClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/web/funding")
@WsOperation("融资管理")
public class WebFundingController {

    @Resource
    private FundingClient fundingClient;

    @ApiOperation(value = "获取融资列表", notes = "获取融资列表")
    @GetMapping(value = "/findFundingPage", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取融资列表", ignoreLogFields = {"Authorization"})
    public String findFundingPage(@ModelAttribute Funding funding, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.findFundingPage(funding,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取融资详细", notes = "获取融资详细")
    @GetMapping(value = "/getFundingDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取融资详细", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String getFundingDetail(@ModelAttribute Funding funding, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.getFundingDetail(funding,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "终止SMCN", notes = "终止SMCN")
    @PostMapping(value = "/terminateFundSmcn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "终止SMCN", ignoreLogFields = {"Authorization"}, requiredParams = {"fundingId"})
    public String terminateFundSmcn(@RequestBody FundSmcn fundSmcn, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        fundSmcn.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.terminateFundSmcn(fundSmcn,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "终止回购", notes = "终止回购")
    @PostMapping(value = "/terminateFundRepo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "终止回购", ignoreLogFields = {"Authorization"}, requiredParams = {"fundingId"})
    public String terminateFundRepo(@RequestBody FundRepo fundRepo, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        fundRepo.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.terminateFundRepo(fundRepo,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "终止TRS", notes = "终止TRS")
    @PostMapping(value = "/terminateFundTrs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "终止TRS", ignoreLogFields = {"Authorization"}, requiredParams = {"fundingId"})
    public String terminateFundTrs(@RequestBody FundTrs fundTrs, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        fundTrs.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.terminateFundTrs(fundTrs,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取融资FX列表", notes = "获取融资FX列表")
    @GetMapping(value = "/findFundFxPage", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取融资FX列表", ignoreLogFields = {"Authorization"})
    public String findFundFxPage(@ModelAttribute FundFx fundFx, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.findFundFxPage(fundFx,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取TRS重置列表", notes = "获取TRS重置列表")
    @GetMapping(value = "/findTrsResetPage", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取TRS重置列表", ignoreLogFields = {"Authorization"})
    public String findTrsResetPage(@ModelAttribute TrsReset trsReset, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.findTrsResetPage(trsReset,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "删除融资", notes = "删除融资")
    @PostMapping(value = "/deleteFunding", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除融资", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String deleteFunding(@RequestBody Funding funding, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        funding.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.deleteFunding(funding,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改SMCN", notes = "修改SMCN")
    @PostMapping(value = "/modifyFundSmcn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改SMCN", ignoreLogFields = {"Authorization"}, requiredParams = {"fundingId"})
    public String modifyFundSmcn(@RequestBody FundSmcn fundSmcn, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        fundSmcn.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.modifyFundSmcn(fundSmcn,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改Repo", notes = "修改Repo")
    @PostMapping(value = "/modifyFundRepo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改Repo", ignoreLogFields = {"Authorization"}, requiredParams = {"fundingId"})
    public String modifyFundRepo(@RequestBody FundRepo fundRepo, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        fundRepo.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.modifyFundRepo(fundRepo,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改TRS", notes = "修改TRS")
    @PostMapping(value = "/modifyFundTrs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改TRS", ignoreLogFields = {"Authorization"}, requiredParams = {"fundingId"})
    public String modifyFundTrs(@RequestBody FundTrs fundTrs, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        fundTrs.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.modifyFundTrs(fundTrs,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "获取Repo Position列表", notes = "获取Repo Position列表")
    @PostMapping(value = "/findRepoPositionList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取Repo Position列表", ignoreLogFields = {"Authorization"},requiredParams = {"transId"})
    public String findRepoPositionList(@RequestBody FundRepo fundRepo, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.findRepoPositionList(fundRepo,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取Repo列表", notes = "获取Repo列表")
    @PostMapping(value = "/findRepoList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取Repo列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String findRepoList(@RequestBody FundRepo fundRepo, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.findRepoList(fundRepo,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取抵押品Position列表", notes = "获取抵押品Position列表")
    @PostMapping(value = "/findCollateralPositionList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取抵押品Position列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String findCollateralPositionList(@RequestBody FundRepo fundRepo, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.findCollateralPositionList(fundRepo,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取Repo估值列表", notes = "获取Repo估值列表")
    @PostMapping(value = "/findRepoValuationList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取Repo估值列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId", "valueDate"})
    public String findRepoValuationList(@RequestBody FundRepo fundRepo, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.findRepoValuationList(fundRepo,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取Repo详细", notes = "获取Repo详细")
    @GetMapping(value = "/getRepoDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取Repo详细", ignoreLogFields = {"Authorization"}, requiredParams = {"fundingId"})
    public String getRepoDetail(@ModelAttribute FundRepo fundRepo, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.getRepoDetail(fundRepo,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取Repo融资列表", notes = "获取Repo融资列表")
    @PostMapping(value = "/findRepoFinancingList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取Repo融资列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String findRepoFinancingList(@RequestBody FundRepo fundRepo, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.findRepoFinancingList(fundRepo,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取融资计划数据", notes = "获取融资计划数据")
    @GetMapping(value = "/findFinancingPlan", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取融资计划数据", ignoreLogFields = {"Authorization"}, requiredParams = {"currencyType"})
    public String findFinancingPlan(@ModelAttribute FundingPlanConfig fundingPlanConfig, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.findFinancingPlan(fundingPlanConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改融资计划数据", notes = "修改融资计划数据")
    @PostMapping(value = "/updateFundingPlanConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改融资计划数据", ignoreLogFields = {"Authorization"}, requiredParams = {"currencyType"})
    public String updateFundingPlanConfig(@RequestBody FundingPlanConfig fundingPlanConfig, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        fundingPlanConfig.setUserId(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.updateFundingPlanConfig(fundingPlanConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取融资曲线设置数据", notes = "获取融资曲线设置数据")
    @GetMapping(value = "/findFundCurveConfig", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取融资曲线设置数据", ignoreLogFields = {"Authorization"})
    public String findFundCurveConfig(@ModelAttribute FundingCurveConfig fundingCurveConfig, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.findFundCurveConfig(fundingCurveConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "修改融资曲线设置数据", notes = "修改融资曲线设置数据")
    @PostMapping(value = "/updateFundingCurveConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改融资曲线设置数据", ignoreLogFields = {"Authorization"}, requiredParams = {"fundingCurveList"})
    public String updateFundingCurveConfig(@RequestBody FundingCurveConfig fundingCurveConfig, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        fundingCurveConfig.setUserId(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(fundingClient.updateFundingCurveConfig(fundingCurveConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }
}
