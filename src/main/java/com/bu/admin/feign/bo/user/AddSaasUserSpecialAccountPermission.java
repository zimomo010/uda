package com.bu.admin.feign.bo.user;

import lombok.Data;

import java.util.List;

/**
 * 添加用户专户数据权限
 *
 * @author liujiegang
 * @date 2024/8/7
 */
@Data
public class AddSaasUserSpecialAccountPermission {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 创建人ID
     */
    private String createUser;

    /**
     * 数据权限范围
     */
    private String dataPermissionRange;

    /**
     * 专户
     */
    private List<SpecialAccount> specialAccounts;

    @Data
    public static class SpecialAccount {

        /**
         * 专户编号
         */
        private String specialAccountCode;

        /**
         * 公司ID
         */
        private String companyId;

        /**
         * 公司类型
         */
        private String companyType;
    }
}
