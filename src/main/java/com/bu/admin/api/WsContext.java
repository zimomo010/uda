package com.bu.admin.api;

import com.auth0.jwt.interfaces.Claim;
import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.Map;


@Getter
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


    public static WsContext getContext() {
        return threadLocal.get();
    }


    public static void unset() {
        threadLocal.remove();
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public void setCallSn(long callSn) {
        this.callSn = callSn;
    }

    public void setTokenNeeded(boolean tokenNeeded) {
        this.tokenNeeded = tokenNeeded;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserInfo(Map<String, Claim> userInfo) {
        this.userInfo = userInfo;
    }

    public void setServerTokenNeeded(boolean serverTokenNeeded) {
        this.serverTokenNeeded = serverTokenNeeded;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

}
