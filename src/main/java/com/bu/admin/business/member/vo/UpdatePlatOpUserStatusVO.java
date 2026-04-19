package com.bu.admin.business.member.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改Saas User 状态
 *
 * @author liujiegang
 * @date 2024/6/5 19:17
 */
@Data
public class UpdatePlatOpUserStatusVO {

    /**
     * 用户ID
     */
    @NotBlank
    private String id;

    /**
     * 状态：true、false
     */
    @NotNull
    private Boolean status;
}
