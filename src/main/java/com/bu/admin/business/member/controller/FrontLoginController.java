package com.bu.admin.business.member.controller;

import cn.hutool.jwt.JWTUtil;
import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.fileupload.enums.NoticeBusType;
import com.bu.admin.business.fileupload.service.SmsService;
import com.bu.admin.business.member.vo.UnificationLoginVO;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.feign.bo.user.SaasUser;
import com.bu.admin.feign.bo.user.UnificationLogin;
import com.bu.admin.feign.bo.user.User;
import com.bu.admin.feign.enumtype.user.SaasUserType;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.service.UserCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.utils.BeanCopyUtils;
import com.bu.admin.utils.JSONUtils;
import com.bu.admin.utils.MethodLimitCheckUtil;
import com.bu.admin.utils.codec.RSACoder;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;


@Api
@Slf4j
@RestController
@RequestMapping("/front/logon")
@WsOperation("会员接口")
public class FrontLoginController{

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
    private UserCenterClient userCenterClient;
    @Resource
    private ProductCenterClient orderCenterClient;


    @ApiOperation(value = "获取登录图片验证码", notes = "获取登录图片验证码")
    @GetMapping(value = "/userLogin/getLoginVerifyCode/{busId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取登录图片验证码", ignoreLogFields = {"str"}, ignoreLogOutputFields = "base64Str", requiredParams = {"busId"}, tokenNeeded = false)
    public String getLoginVerifyCode(@PathVariable String busId) {
        SaasUser saasUser = new SaasUser();
        saasUser.setBusId(busId);
        JsonObject result = ResponseCheckUtils.checkResponse(orderCenterClient.getImageVerifyCode(saasUser,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).getAsJsonObject();
        return Response.ok(result).build();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping(value = "/userLogin/userRegister", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "用户注册", requiredParams = {"verifyCode", "userName", "userAlias", "userPassword", "userAreaCode"}, ignoreLogFields = {"Authorization"}, tokenNeeded = false)
    public String userRegister(@RequestBody SaasUser saasUser) {
        saasUser.setUserName(RSACoder.decrypt(saasUser.getUserName(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        String pwd = RSACoder.decrypt(saasUser.getUserPassword(), PRIVATE_KEY, StandardCharsets.UTF_8.toString());
        saasUser.setUserPassword(pwd);
        saasUser.setUserType(SaasUserType.COMMON_USER);
        saasUser.setDepartCode("GE");
        ResponseCheckUtils.checkResponse(orderCenterClient.saasUserReg(saasUser,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }

    @ApiOperation(value = "会员登录", notes = "会员登录")
    @PostMapping(value = "/userLogin/saasUserLogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "会员登录", ignoreLogFields = "Authorization", requiredParams = {"busId", "userName", "verifyCode", "userPassword"}, tokenNeeded = false)
    public String saasUserLogin(@RequestBody SaasUser saasUser) {
        saasUser.setUserName(RSACoder.decrypt(saasUser.getUserName(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        saasUser.setUserPassword(RSACoder.decrypt(saasUser.getUserPassword(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        JsonObject result = ResponseCheckUtils.checkResponse(orderCenterClient.saasUserLogin(saasUser,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).getAsJsonObject();
        return Response.ok(result).build();
    }

    @ApiOperation(value = "统一登陆", notes = "统一登陆")
    @PostMapping(value = "/userLogin/unificationLogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "统一登陆", ignoreLogFields = "Authorization", tokenNeeded = false)
    public String unificationLogin(@RequestBody @Validated UnificationLoginVO loginVO) {
        UnificationLogin login = BeanCopyUtils.convertBusinessValue(loginVO, UnificationLogin.class);

        JsonObject result = ResponseCheckUtils.checkResponse(orderCenterClient.unificationLogin(login,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).getAsJsonObject();

        return Response.ok(result).build();
    }

    @ApiOperation(value = "统一登出", notes = "统一登出")
    @PostMapping(value = "/userLogin/unificationLogout", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "统一登陆", ignoreLogFields = "Authorization", tokenNeeded = false)
    public String unificationLogout(HttpServletRequest request) {
        request.getParameterMap().forEach((key, value) -> {
            log.info("unificationLogout from:{}={}", key, value);
            String sessionId;
            if (key.equals("logout_token")) {
                String logoutToken =  value[0];
                sessionId = String.valueOf(JWTUtil.parseToken(logoutToken).getPayload("sid"));
                log.info("sessionId is :{}", sessionId);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("sessionId", sessionId);
                userCenterClient.userSsoLogout(JSONUtils.toJson(jsonObject), ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
            }
        });
        return Response.ok().build();
    }

    @ApiOperation(value = "用户退出", notes = "用户退出")
    @PostMapping(value = "/userLogout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "用户退出", ignoreLogFields = {"Authorization"}, permissionNeeded = false)
    public String userLogout(@RequestHeader("Authorization") String token) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userToken", token);
        ResponseCheckUtils.checkResponse(userCenterClient.userLogout(JSONUtils.toJson(jsonObject), ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }

    @ApiOperation(value = "重置用户密码", notes = "更改密码")
    @PostMapping(value = "/resetPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "重置用户密码", requiredParams = {"userName", "verifyCode", "userPassword"}, tokenNeeded = false)
    public String resetUserPassword(@RequestBody User user) {
        user.setUserName(RSACoder.decrypt(user.getUserName(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        user.setUserPassword(RSACoder.decrypt(user.getUserPassword(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        ResponseCheckUtils.checkResponse(userCenterClient.resetUserPassword(user, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }

    @ApiOperation(value = "用户更改密码", notes = "用户更改密码")
    @PostMapping(value = "/modificationPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "用户更改密码", requiredParams = {"oldPassword", "userPassword"}, ignoreLogFields = {"Authorization"})
    public String modificationPassword(@RequestBody User user, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        user.setId(userId);
        user.setOldPassword(RSACoder.decrypt(user.getOldPassword(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        user.setUserPassword(RSACoder.decrypt(user.getUserPassword(), PRIVATE_KEY, StandardCharsets.UTF_8.toString()));
        ResponseCheckUtils.checkResponse(userCenterClient.modificationPassword(user, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }


}
