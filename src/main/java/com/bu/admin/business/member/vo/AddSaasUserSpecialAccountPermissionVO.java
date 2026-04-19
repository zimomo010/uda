package com.bu.admin.business.member.vo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

/**
 * 添加用户专户数据权限
 *
 * @author liujiegang
 * @date 2024/8/7
 */
@Data
public class AddSaasUserSpecialAccountPermissionVO {

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
     * 专户
     */
    @Valid
    private List<SpecialAccount> specialAccounts;

    @Data
    public static class SpecialAccount {

        /**
         * 专户编号
         */
        @NotBlank
        private String specialAccountCode;

        /**
         * 公司ID
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
