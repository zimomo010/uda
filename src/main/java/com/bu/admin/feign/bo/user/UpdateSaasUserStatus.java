package com.bu.admin.feign.bo.user;

import lombok.Data;

/**
 * 修改Saas User 状态
 *
 * @author liujiegang
 * @date 2024/6/5 19:17
 */
@Data
public class UpdateSaasUserStatus {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 状态：true、false
     */
    private Boolean status;

    /**
     * 操作人ID
     */
    private String updateUser;
}
