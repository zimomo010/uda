package com.bu.admin.business.member.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.member.vo.AddSaasUserCompanyPermissionVO;
import com.bu.admin.business.member.vo.AddSaasUserSpecialAccountPermissionVO;
import com.bu.admin.business.member.vo.UpdatePlatOpUserStatusVO;
import com.bu.admin.feign.bo.user.*;
import com.bu.admin.feign.enumtype.user.SaasUserType;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.service.UserCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 后台管理-平台运营用户
 *
 * @author liujiegang
 * @date 2024/6/5 20:48
 */
@Api
@RestController
@RequestMapping("/web/platopuser")
@WsOperation("后台管理平台运营用户")
public class WebPlatOpUserController {

    @Resource
    private ProductCenterClient productCenterClient;
    @Resource
    private UserCenterClient userCenterClient;

    /**
     * 新增
     *
     * @param saasUser
     * @param token
     * @return
     */
    @ApiOperation(value = "新增")
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新增", requiredParams = {"userName", "userDepartCode", "roleIdList"}, ignoreLogFields = {"Authorization"})
    public String add(@RequestBody SaasUser saasUser, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();

        saasUser.setUserAlias(saasUser.getUserName());
        saasUser.setUserType(SaasUserType.PLAT_OP);
        saasUser.setCreateUser(userId);
        saasUser.setDepartCode(saasUser.getUserDepartCode());

        return productCenterClient.newPlatOpUser(saasUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 修改
     *
     * @param saasUser
     * @param token
     * @return
     */
    @ApiOperation(value = "修改")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改", requiredParams = {"id", "userName", "userDepartCode", "roleIdList"}, ignoreLogFields = {"Authorization"})
    public String update(@RequestBody SaasUser saasUser, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();

        saasUser.setUserId(saasUser.getId());
        saasUser.setUpdateUser(userId);
        saasUser.setDepartCode(saasUser.getUserDepartCode());

        return productCenterClient.saasUserModifyInfo(saasUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 分页查询
     *
     * @param saasUser
     * @param token
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping(value = "/page", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "分页查询", ignoreLogFields = {"Authorization"})
    public String page(@RequestBody SaasUser saasUser, @RequestHeader("Authorization") String token) {
        return productCenterClient.findPlatOpUsers(saasUser, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 修改状态
     *
     * @param updateVO
     * @param token
     * @return
     */
    @ApiOperation(value = "修改状态", notes = "修改状态")
    @PostMapping(value = "/status/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改状态", ignoreLogFields = {"Authorization"})
    public String updateStatus(@RequestBody @Validated UpdatePlatOpUserStatusVO updateVO, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();

        UpdateSaasUserStatus updateSaasUserStatus = BeanCopyUtils.convertBusinessValue(updateVO, UpdateSaasUserStatus.class);
        updateSaasUserStatus.setUserId(updateVO.getId());
        updateSaasUserStatus.setUpdateUser(userId);

        return productCenterClient.updateSaasUserStatus(updateSaasUserStatus, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    @ApiOperation(value = "后台重置密码", notes = "后台重置密码")
    @PostMapping(value = "/resetPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "后台重置密码", requiredParams = {"id"}, ignoreLogFields = {"Authorization"})
    public String resetPassword(@RequestBody User user, @RequestHeader("Authorization") String token) {
        ResponseCheckUtils.checkResponse(userCenterClient.resetPassword(user, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }

    /**
     * 保存用户的公司机构数据权限
     *
     * @param addVO
     * @param token
     * @return
     */
    @ApiOperation(value = "保存用户的公司机构数据权限", notes = "保存用户的公司机构数据权限")
    @PostMapping(value = "/addCompanyPermission", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "保存用户的公司机构数据权限", ignoreLogFields = {"Authorization"})
    public String addCompanyPermission(@RequestBody @Validated AddSaasUserCompanyPermissionVO addVO, @RequestHeader("Authorization") String token) {
        AddSaasUserCompanyPermission addSaasUserCompanyPermission = BeanCopyUtils.convertBusinessValue(addVO, AddSaasUserCompanyPermission.class);
        addSaasUserCompanyPermission.setUserId(addVO.getId());
        addSaasUserCompanyPermission.setCreateUser(WsContext.getContext().getUserId());

        return productCenterClient.addCompanyPermission(addSaasUserCompanyPermission, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 保存用户的专户数据权限
     *
     * @param addVO
     * @param token
     * @return
     */
    @ApiOperation(value = "保存用户的专户数据权限", notes = "保存用户的专户数据权限")
    @PostMapping(value = "/addSpecialAccountPermission", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "保存用户的专户数据权限", ignoreLogFields = {"Authorization"})
    public String addSpecialAccountPermission(@RequestBody @Validated AddSaasUserSpecialAccountPermissionVO addVO, @RequestHeader("Authorization") String token) {
        AddSaasUserSpecialAccountPermission addSaasUserSpecialAccountPermission = BeanCopyUtils.convertBusinessValue(addVO, AddSaasUserSpecialAccountPermission.class);
        addSaasUserSpecialAccountPermission.setUserId(addVO.getId());
        addSaasUserSpecialAccountPermission.setCreateUser(WsContext.getContext().getUserId());

        return productCenterClient.addSpecialAccountPermission(addSaasUserSpecialAccountPermission, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 查询用户的公司机构数据权限
     *
     * @param id
     * @param token
     * @return
     */
    @ApiOperation(value = "查询用户的公司机构数据权限", notes = "查询用户的公司机构数据权限")
    @GetMapping(value = "/getCompanyPermission", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "查询用户的公司机构数据权限", ignoreLogFields = {"Authorization"})
    public String getCompanyPermission(@RequestParam String id, @RequestHeader("Authorization") String token) {
        return productCenterClient.getCompanyPermission(id, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 查询用户的专户数据权限
     *
     * @param id
     * @param token
     * @return
     */
    @ApiOperation(value = "查询用户的专户数据权限", notes = "查询用户的专户数据权限")
    @GetMapping(value = "/getSpecialAccountPermission", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "查询用户的专户数据权限", ignoreLogFields = {"Authorization"})
    public String getSpecialAccountPermission(@RequestParam String id, @RequestHeader("Authorization") String token) {
        return productCenterClient.getSpecialAccountPermission(id, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    @ApiOperation(value = "获取子机构", notes = "获取子机构")
    @GetMapping(value = "/getDepartAllChildren", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取子机构", ignoreLogFields = {"Authorization"})
    public String getDepartAllChildren(@RequestParam String parentId, @RequestHeader("Authorization") String token) {
        Department department = new Department();
        if (parentId.equals("GE")) {
            parentId = parentId + "0";
        }
        department.setParentId(parentId);
        return Response.ok(ResponseCheckUtils.checkResponse(userCenterClient.getDepartAllChildren(department, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }
}
