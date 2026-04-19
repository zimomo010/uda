package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.funding.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "gj-product-center", contextId = "fundingClient")
public interface FundingClient {

    @ApiOperation(value = "获取融资列表", notes = "获取融资列表")
    @GetMapping(value = "/server/funding/findFundingPage", produces = MediaType.APPLICATION_JSON_VALUE)
    String findFundingPage(@RequestBody Funding funding, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取融资详细", notes = "获取融资详细")
    @GetMapping(value = "/server/funding/getFundingDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    String getFundingDetail(@RequestBody Funding funding, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "终止回购", notes = "终止回购")
    @PostMapping(value = "/server/funding/terminateFundSmcn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String terminateFundSmcn(@RequestBody FundSmcn fundSmcn, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "终止回购", notes = "终止回购")
    @PostMapping(value = "/server/funding/terminateFundRepo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String terminateFundRepo(@RequestBody FundRepo fundRepo, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "终止回购", notes = "终止回购")
    @PostMapping(value = "/server/funding/terminateFundTrs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String terminateFundTrs(@RequestBody FundTrs fundTrs, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取融资FX列表", notes = "获取融资FX列表")
    @GetMapping(value = "/server/funding/findFundFxPage", produces = MediaType.APPLICATION_JSON_VALUE)
    String findFundFxPage(@RequestBody FundFx fundFx, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取TRS重置列表", notes = "获取TRS重置列表")
    @GetMapping(value = "/server/funding/findTrsResetPage", produces = MediaType.APPLICATION_JSON_VALUE)
    String findTrsResetPage(@RequestBody TrsReset trsReset, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除融资", notes = "删除融资")
    @PostMapping(value = "/server/funding/deleteFunding", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteFunding(@RequestBody Funding funding, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改SMCN", notes = "修改SMCN")
    @PostMapping(value = "/server/funding/modifyFundSmcn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyFundSmcn(@RequestBody FundSmcn fundSmcn, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改Repo", notes = "修改Repo")
    @PostMapping(value = "/server/funding/modifyFundRepo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyFundRepo(@RequestBody FundRepo fundRepo, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改TRS", notes = "修改TRS")
    @PostMapping(value = "/server/funding/modifyFundTrs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modifyFundTrs(@RequestBody FundTrs fundTrs, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取Repo Position列表", notes = "获取Repo Position列表")
    @GetMapping(value = "/server/funding/findRepoPositionList", produces = MediaType.APPLICATION_JSON_VALUE)
    String findRepoPositionList(@RequestBody FundRepo fundRepo, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取Repo列表", notes = "获取Repo列表")
    @GetMapping(value = "/server/funding/findRepoList", produces = MediaType.APPLICATION_JSON_VALUE)
    String findRepoList(@RequestBody FundRepo fundRepo, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取抵押品Position列表", notes = "获取抵押品Position列表")
    @GetMapping(value = "/server/funding/findCollateralPositionList", produces = MediaType.APPLICATION_JSON_VALUE)
    String findCollateralPositionList(@RequestBody FundRepo fundRepo, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取Repo估值列表", notes = "获取Repo估值列表")
    @GetMapping(value = "/server/funding/findRepoValuationList", produces = MediaType.APPLICATION_JSON_VALUE)
    String findRepoValuationList(@RequestBody FundRepo fundRepo, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取Repo详细", notes = "获取Repo详细")
    @GetMapping(value = "/server/funding/getRepoDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    String getRepoDetail(@RequestBody FundRepo fundRepo, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取Repo融资列表", notes = "获取Repo融资列表")
    @GetMapping(value = "/server/funding/findRepoFinancingList", produces = MediaType.APPLICATION_JSON_VALUE)
    String findRepoFinancingList(@RequestBody FundRepo fundRepo, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取融资计划数据", notes = "获取融资计划数据")
    @PostMapping(value = "/server/funding/findFinancingPlan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findFinancingPlan(@RequestBody FundingPlanConfig fundingPlanConfig, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改融资计划数据", notes = "修改融资计划数据")
    @PostMapping(value = "/server/funding/updateFundingPlanConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateFundingPlanConfig(@RequestBody FundingPlanConfig fundingPlanConfig, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取融资曲线设置数据", notes = "获取融资曲线设置数据")
    @PostMapping(value = "/server/funding/findFundCurveConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findFundCurveConfig(@RequestBody FundingCurveConfig fundingCurveConfig, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改融资曲线设置数据", notes = "修改融资曲线设置数据")
    @PostMapping(value = "/server/funding/updateFundingCurveConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateFundingCurveConfig(@RequestBody FundingCurveConfig fundingCurveConfig, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String serverToken);

}
