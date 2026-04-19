package com.bu.admin.feign.service.impl;

import com.bu.admin.business.operationLog.bo.OperationLog;
import com.bu.admin.feign.bo.system.Config;
import com.bu.admin.feign.bo.user.Department;
import com.bu.admin.feign.service.UserCenterClient;
import com.bu.admin.feign.bo.system.SystemOperationList;
import com.bu.admin.feign.bo.user.User;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

/**
 * @Auther: ghostWu
 * @Date: 2020/4/8
 */
@Component
public class UserCenterClientHystrix implements UserCenterClient {
    @Override
    public String userLogin(User param, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String updateUser(User user, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String addUser(User user, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String resetPassword(User user, String serverName, String serverToken) {
        return  hystrixResponse();
    }

    @Override
    public String modificationPassword(User user, String serverName, String serverToken) {
        return  hystrixResponse();
    }

    @Override
    public String getResetPasswordVerifyCode(User user, String serverName, String serverToken) {
        return  hystrixResponse();
    }

    @Override
    public String resetUserPassword(User user, String serverName, String serverToken) {
        return  hystrixResponse();
    }

    @Override
    public String userLogout(String param, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String userSsoLogout(String param, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String delUser(String param, String serverName, String token) {
        return hystrixResponse();
    }

    @Override
    public String updateRestoreUser(String param, String serverName, String token) {
        return hystrixResponse();
    }

    @Override
    public String sendSystemOperations(SystemOperationList systemOperationList, String serverName, String token) {
        return hystrixResponse();
    }

    @Override
    public String findUserByUserId(String param, String serverName, String token) {
        return hystrixResponse();
    }

    @Override
    public String getConfigDetail(String param,String serverName,String token){
        return hystrixResponse();
    }

    @Override
    public String getConfigList(Config config, String serverName, String token){
        return hystrixResponse();
    }

    @Override
    public String addOrModifyConfig(Config config,String serverName,String token){
        return hystrixResponse();
    }

    @Override
    public String getConfigDetailById(Config config,String serverName,String token){
        return hystrixResponse();
    }

    @Override
    public String findOperationLogPage(OperationLog operationLog, String applicationName, String token) {
        return hystrixResponse();
    }

    @Override
    public String getDepartAllChildren(Department department, String serverName, String serverToken) {
        return hystrixResponse();
    }


    private String hystrixResponse() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", 500);
        jsonObject.addProperty("msg", "网络错误,请稍后再试");
        return jsonObject.toString();
    }
}
