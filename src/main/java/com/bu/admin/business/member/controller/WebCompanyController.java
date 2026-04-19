package com.bu.admin.business.member.controller;

/**
 * @ClassName WebBannerController
 * @Description banner管理
 * @Author yangting
 * @Date 2019-07-16 14:30
 * @Version 1.0
 */

import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.feign.bo.user.Company;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("/web/company")
@WsOperation("后台用户管理")
public class WebCompanyController {

    @Resource
    private ProductCenterClient orderCenterClient;

    @ApiOperation(value = "新建机构公司", notes = "新建机构公司")
    @PostMapping(value = "/companyCreate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新建机构公司", requiredParams = {"companyName","companyType"}, ignoreLogFields = {"Authorization"})
    public String issuerCreate(@RequestBody Company company, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        company.setCreateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.companyCreate(company,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取公司列表", notes = "获取公司列表")
    @GetMapping(value = "/getCompanyInfoList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取公司列表", ignoreLogFields = "Authorization")
    public String getCompanyInfoList(@ModelAttribute Company company, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.getCompanyInfoList(company, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取公司列表-数据控制", notes = "获取公司列表-数据控制")
    @GetMapping(value = "/getCompanyInfoListForUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取公司列表-数据控制", ignoreLogFields = "Authorization")
    public String getCompanyInfoListForUser(@ModelAttribute Company company, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        company.setOpUserId(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.getCompanyInfoList(company, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "通过ID获取公司详细", notes = "通过ID获取公司详细")
    @GetMapping(value = "/getCompanyInfoById", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "通过ID获取公司详细", ignoreLogFields = "Authorization", requiredParams = {"id"})
    public String getCompanyInfoById(@ModelAttribute Company company, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.getCompanyInfoById(company, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "用户主体修改", notes = "用户主体修改")
    @PostMapping(value = "/companyInfoModify", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "用户主体修改", requiredParams = {"id", "companyName","companyCode","companyType"}, ignoreLogFields = {"Authorization"})
    public String companyInfoModify(@RequestBody Company company, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        company.setCreateUser(userId);
        ResponseCheckUtils.checkResponse(orderCenterClient.companyInfoModify(company, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }

    @ApiOperation(value = "获取最新的公司编号", notes = "获取最新的公司编号")
    @GetMapping(value = "/getNewCompanyCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取最新的公司编号", ignoreLogFields = {"Authorization"})
    public String getNewCompanyCode(@RequestHeader("Authorization") String token) {
        return orderCenterClient.getNewCompanyCode(ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }


}
