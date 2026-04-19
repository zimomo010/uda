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
import com.bu.admin.feign.bo.user.*;
import com.bu.admin.feign.enumtype.user.SaasUserType;
import com.bu.admin.feign.enumtype.user.VerifyStatus;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.utils.codec.RSACoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Api
@RestController
@RequestMapping("/web/member")
@WsOperation("后台用户管理")
public class WebMemberController {

    private static final String PRIVATE_KEY = """
            MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANv7J5IA5K9IDROl
            FT0yl0ECCU7u+DI7VamXXzIwP7n7umv8WphLvXuwFQ84G6Ey9Vx4ZM/ZefUdz0BO
            0FobbfDMeXeRVCQs9WVWjC+xCFbHfbVGSkC/cSYC697eEsg3J7LE2qWBGy789mQ2
            LEtqFp83/cSQKRPTFae7SMf1eVDFAgMBAAECgYB/WWKLD6FUJ+4KFWO6TfNqWvdw
            jr94tQjeaRdfQrdhv+0oXqtDx3UmicVnTiAQriOFKN2Txl7oHB3DKN1NjNuTnMhD
            luCc8FeStpIUE+D39bzB90Ly8NMr0fg0q2N2aCcqjyJI86k9fpy31KbVdg3WZEpv
            LacYG88n5+b1vwICcQJBAO31h487+K0/E7QLP5dj/M3WAj4p9j3u1rZuCCIN59qI
            URZ+MPmPezhWJ+K198UxwSG/eon5qCq12AHMJGCa4Z8CQQDsqLIEmmXYQ0tALH7P
            W8bH06QbLMXFSyo09MQPY+VicRo5HGhKNa1mQQaGlxr1i3lD0uZLq0rtH2pNdCBD
            EFsbAkEA68x4DjmRTKvKrIjQ9QXBx36gLlWKiwiXf6M9L/GzpNub7rwRb9r8cXD+
            iStkYOXSycxic0MPn3Men3lZOtl/ZQJAHBKOu1kNR7AD1yLwD+zVEZMbXlXbR+H4
            qbt3HlQ9O4YrFCc4f8UKQpzoB0MZYZMf2VnFE6jIBIS7eWCTCyF/jQJAa9vARkE5
            OuUhBR3BvP3wCue0cTsxsMMPzM9JlsTkOoP4WBW36RGAipPNZRK3l+t1Ro71PhFB
            mn2v3KZA6CfWTA==
            """;
    @Resource
    private ProductCenterClient orderCenterClient;


    @ApiOperation(value = "新建平台运营用户", notes = "新建平台运营用户")
    @PostMapping(value = "/newPlatOpUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "用户注册", requiredParams = {"userName","userPassword"}, ignoreLogFields = {"Authorization"})
    public String newPlatOpUser(@RequestBody SaasUser saasUser, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        saasUser.setUserName(RSACoder.decrypt(saasUser.getUserName(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        saasUser.setUserPassword(RSACoder.decrypt(saasUser.getUserPassword(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        saasUser.setUserType(SaasUserType.PLAT_OP);
        saasUser.setCreateUser(userId);
        ResponseCheckUtils.checkResponse(orderCenterClient.newPlatOpUser(saasUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }

    @ApiOperation(value = "新建发行商用户", notes = "新建发行商用户")
    @PostMapping(value = "/newIssuerUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新建发行商用户", requiredParams = {"userName", "userAlias", "userPassword","companyName"}, ignoreLogFields = {"Authorization"})
    public String newIssuerUser(@RequestBody IssuerUser issuerUser, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        issuerUser.setUserName(RSACoder.decrypt(issuerUser.getUserName(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        issuerUser.setUserPassword(RSACoder.decrypt(issuerUser.getUserPassword(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        issuerUser.setUserType(SaasUserType.ISSUER_USER);

        issuerUser.setCreateUser(userId);
        ResponseCheckUtils.checkResponse(orderCenterClient.newIssuerUser(issuerUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }

    @ApiOperation(value = "获取客户列表", notes = "获取客户列表")
    @GetMapping(value = "/getCustomers", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取客户列表", ignoreLogFields = {"Authorization"})
    public String getCustomers(@ModelAttribute Customer customer, @RequestHeader("Authorization") String token) {
        customer.setVerifyStatusExclude(VerifyStatus.WAIT_SUBMIT);
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.getCustomers(customer, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取客户信息详细", notes = "获取客户信息详细")
    @GetMapping(value = "/getCustomerDetail", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取客户信息详细", requiredParams = {"cusId"}, ignoreLogFields = {"Authorization"})
    public String getCustomerDetail(@ModelAttribute Customer customer,@RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.getCustomerDetail(customer, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "客户信息审核", notes = "客户信息审核")
    @PostMapping(value = "/customerIdentifyDeal", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "客户信息审核", requiredParams = {"cusId", "valid"}, ignoreLogFields = {"Authorization"})
    public String customerIdentifyDeal(@RequestBody Customer customer, @RequestHeader("Authorization") String token) {
        ResponseCheckUtils.checkResponse(orderCenterClient.dealEAMCustomer(customer, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }

    @ApiOperation(value = "获取用户任务列表", notes = "获取用户任务列表")
    @GetMapping(value = "/getUserTaskList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取用户任务列表", ignoreLogFields = "Authorization")
    public String getUserTaskList(@ModelAttribute UserTask userTask, @RequestHeader("Authorization") String token) {
        userTask.setUserId(null);
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.getUserTaskList(userTask, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取客户统计列表", notes = "获取客户统计列表")
    @GetMapping(value = "/getCustomerStatisticList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取客户统计列表", ignoreLogFields = "Authorization")
    public String getCustomerStatisticList(@ModelAttribute Company company, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.findTransactionStatisticList(company, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "新建终端客户", notes = "新建终端客户")
    @PostMapping(value = "/newEndClientUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新建终端客户", requiredParams = {"userName", "userDepartCode"}, ignoreLogFields = {"Authorization"})
    public String newEndClientUser(@RequestBody SaasUser saasUser, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();

        saasUser.setUserAlias(saasUser.getUserName());
        saasUser.setUserType(SaasUserType.END_CLIENT_USER);
        saasUser.setCreateUser(userId);
        saasUser.setDepartCode(saasUser.getUserDepartCode());

        return orderCenterClient.newClientUser(saasUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping(value = "/modifySaasUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改用户信息", requiredParams = {"id", "userName", "userDepartCode"}, ignoreLogFields = {"Authorization"})
    public String modifySaasUser(@RequestBody SaasUser saasUser, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();

        saasUser.setUserId(saasUser.getId());
        saasUser.setUpdateUser(userId);
        saasUser.setDepartCode(saasUser.getUserDepartCode());

        return orderCenterClient.saasUserModifyInfo(saasUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    @ApiOperation(value = "关联客户", notes = "关联客户")
    @PostMapping(value = "/associateMainUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "关联客户", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "mainUserId"})
    public String associateMainUser(@RequestBody SaasUser saasUser, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();

        saasUser.setUserId(saasUser.getId());
        saasUser.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.associateMainUser(saasUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "解除关联客户", notes = "解除关联客户")
    @PostMapping(value = "/disassociateMainUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "解除关联客户", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String disassociateMainUser(@RequestBody SaasUser saasUser,  @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();

        saasUser.setUserId(saasUser.getId());
        saasUser.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.disassociateMainUser(saasUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "后台重置密码", notes = "后台重置密码")
    @PostMapping(value = "/resetClientPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "后台重置密码", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String resetClientPassword(@RequestBody User user, @RequestHeader("Authorization") String token) {
        ResponseCheckUtils.checkResponse(orderCenterClient.resetClientPassword(user, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }

    @ApiOperation(value = "分页查询客户", notes = "分页查询客户")
    @GetMapping(value = "/findEndClientUserPage", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "分页查询客户", ignoreLogFields = {"Authorization"})
    public String findEndClientUserPage(@ModelAttribute SaasUser saasUser, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.findEndClientUserPage(saasUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "模糊查询客户", notes = "模糊查询客户")
    @GetMapping(value = "/fuzzyQueryEndClientList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "模糊查询客户", ignoreLogFields = {"Authorization"})
    public String fuzzyQueryEndClientList(@ModelAttribute SaasUser saasUser, @RequestHeader("Authorization") String token) {
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.fuzzyQueryEndClientList(saasUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }
}
