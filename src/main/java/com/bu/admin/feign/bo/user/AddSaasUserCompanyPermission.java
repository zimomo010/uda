package com.bu.admin.feign.bo.user;

import lombok.Data;

import java.util.List;

/**
 * 添加用户的公司机构数据权限
 *
 * @author liujiegang
 * @date 2024/8/7
 */
@Data
public class AddSaasUserCompanyPermission {

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
     * 公司机构
     */
    private List<Company> companies;

    @Data
    public static class Company {

        /**
         * 公司机构ID
         */
        private String companyId;

        /**
         * 公司类型
         */
        private String companyType;
    }
}
