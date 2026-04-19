package com.bu.admin.business.member.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 统一登录
 *
 * @author liujiegang
 * @date 2024/6/13 19:14
 */
@Data
public class UnificationLoginVO {

    /**
     * 统一登录平台
     */
    @NotBlank
    private String platform;

    /**
     * 统一登录平台的授权token
     */
    @NotBlank
    private String accessToken;

    /**
     * 统一登录平台的刷新token
     */
    private String refreshToken;
}
