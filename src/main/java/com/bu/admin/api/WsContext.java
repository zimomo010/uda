package com.bu.admin.api;

import com.auth0.jwt.interfaces.Claim;
import com.google.gson.JsonObject;

import java.util.Map;

/**
 * @ClassName WsContext
 * @Description
 * @Author ghostWu
 * @Date 2019/7/10
 */
public class WsContext {
    private static final ThreadLocal<WsContext> threadLocal = ThreadLocal.withInitial(WsContext::new);

    private String operationCode;
    private String operationName;
    private final JsonObject request = new JsonObject();
    private long callSn;
    private boolean tokenNeeded;
    private String userId;
    private String token;
    private boolean serverTokenNeeded;
    private String serverName;
    private Map<String, Claim> userInfo;

    /**
     * 获取上下文
     * @return
     */
    public static WsContext getContext() {
        return threadLocal.get();
    }


    /**
     * 清除
     */
    public static void unset() {
        threadLocal.remove();
    }

    public JsonObject getRequest() {
        return request;
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public long getCallSn() {
        return callSn;
    }

    public void setCallSn(long callSn) {
        this.callSn = callSn;
    }

    public boolean isTokenNeeded() {
        return tokenNeeded;
    }

    public void setTokenNeeded(boolean tokenNeeded) {
        this.tokenNeeded = tokenNeeded;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Claim> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, Claim> userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isServerTokenNeeded() {
        return serverTokenNeeded;
    }

    public void setServerTokenNeeded(boolean serverTokenNeeded) {
        this.serverTokenNeeded = serverTokenNeeded;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

}
