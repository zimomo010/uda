package com.bu.admin.business.member.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * 添加用户的公司机构数据权限
 *
 * @author liujiegang
 * @date 2024/8/7
 */
@Data
public class AddSaasUserCompanyPermissionVO {

    /**
     * 用户ID
     */
    @NotBlank
    private String id;

    /**
     * 数据权限范围
     */
    @NotBlank
    private String dataPermissionRange;

    /**
     * 公司机构
     */
    @Valid
    private List<Company> companies;

    @Data
    public static class Company {

        /**
         * 公司机构ID
         */
        @NotBlank
        private String companyId;

        /**
         * 公司类型
         */
        @NotBlank
        private String companyType;
    }
}
