package com.bu.admin.feign.bo.user;

import lombok.Data;

/**
 * 统一登录
 *
 * @author liujiegang
 * @date 2024/6/13 19:14
 */
@Data
public class UnificationLogin {

    /**
     * 统一登录平台
     */
    private String platform;

    /**
     * 统一登录平台的授权token
     */
    private String accessToken;

    /**
     * 统一登录平台的刷新token
     */
    private String refreshToken;
}
