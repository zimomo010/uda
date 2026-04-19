package com.bu.admin.business.account.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 新增
 *
 * @author liujiegang
 * @date 2024/6/9 15:42
 */
@Data
public class WebAddSpecialAccountRequest {

    /**
     * 专户编号
     */
    @NotBlank
    private String code;

    /**
     * 专户名称
     */
    @NotBlank
    private String name;

    /**
     * 专户全称
     */
    private String longName;

    /**
     * 专户编号（客户指定）
     */
    private String appointCode;

    /**
     * 专户全称（客户指定）
     */
    private String appointLongName;

    /**
     * 归属机构ID
     */
    @NotBlank
    private String companyId;
}
