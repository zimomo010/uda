package com.bu.admin.feign.service;

import com.bu.admin.business.operationLog.bo.OperationLog;
import com.bu.admin.feign.bo.system.Config;
import com.bu.admin.feign.bo.system.SystemOperationList;
import com.bu.admin.feign.bo.user.Department;
import com.bu.admin.feign.bo.user.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("gj-user-center")
public interface UserCenterClient {

    @ApiOperation(value = "后台登陆", notes = "员工登陆")
    @PostMapping(value = "/server/user/userLogin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "param", value = "接收两个参数,username,pwd 传json字符串", dataType = "JSONObject")
    String userLogin(@RequestBody User param, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @PostMapping(value = "/server/user/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateUser(@RequestBody User user, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping(value = "/server/user/addUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String addUser(@RequestBody User user, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "后台重置密码", notes = "后台重置密码")
    @PostMapping(value = "/server/user/resetPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String resetPassword(@RequestBody User user, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PostMapping(value = "/server/user/modificationPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String modificationPassword(@RequestBody User user, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "获取重置密码验证码", notes = "获取重置密码验证码")
    @PostMapping(value = "/server/user/getResetPasswordVerifyCode", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getResetPasswordVerifyCode(@RequestBody User user, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "用户重置密码", notes = "用户重置密码")
    @PostMapping(value = "/server/user/resetUserPassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String resetUserPassword(@RequestBody User user, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "用户退出", notes = "用户退出")
    @PostMapping(value = "/server/user/userLogout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String userLogout(@RequestBody String param, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "单点登录登出", notes = "单点登录登出")
    @PostMapping(value = "/server/user/userSsoLogout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String userSsoLogout(@RequestBody String param, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @PostMapping(value = "/server/user/delUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delUser(@RequestBody String param, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String token);

    @ApiOperation(value = "恢复用户", notes = "恢复用户")
    @PostMapping(value = "/server/user/updateRestoreUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateRestoreUser(@RequestBody String param, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String token);

    @ApiOperation(value = "批量保存权限", notes = "批量保存权限")
    @PostMapping(value = "/server/operation/sendSystemOperations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String sendSystemOperations(@RequestBody SystemOperationList systemOperationList, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String token);

    @ApiOperation(value = "查询用户", notes = "查询用户")
    @PostMapping(value = "/server/user/getUserDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findUserByUserId(@RequestBody String param, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String token);

    @ApiOperation(value = "获取配置详细", notes = "获取配置详细")
    @PostMapping(value = "/server/config/getConfigDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getConfigDetail(@RequestBody String config, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String token);

    @ApiOperation(value = "获取配置列表", notes = "获取配置列表")
    @PostMapping(value = "/server/config/getConfigList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getConfigList(@RequestBody Config config, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String token);

    @ApiOperation(value = "新增或修改配置", notes = "新增或修改配置")
    @PostMapping(value = "/server/config/addOrModifyConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String addOrModifyConfig(@RequestBody Config config, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String token);

    @ApiOperation(value = "获取配置详细", notes = "获取配置详细")
    @PostMapping(value = "/server/config/getConfigDetailById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getConfigDetailById(@RequestBody Config config, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String token);

    @ApiOperation(value = "获取日志列表", notes = "获取日志列表")
    @PostMapping(value = "/server/operationLog/findOperationLogPage", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String findOperationLogPage(@RequestBody OperationLog operationLog, @RequestHeader("MiServerName") String applicationName, @RequestHeader("Authorization") String token);

    @ApiOperation(value = "获取子机构", notes = "获取子机构")
    @PostMapping(value = "/server/department/getDepartAllChildren", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String getDepartAllChildren(@RequestBody Department department, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

}
