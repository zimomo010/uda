package com.bu.admin.feign.bo.account;

import lombok.Data;

/**
 * 新增
 *
 * @author liujiegang
 * @date 2024/6/9 15:42
 */
@Data
public class UpdateSpecialAccountBO {

    /**
     * ID
     */
    private Long id;

    /**
     * 专户编号
     */
    private String code;

    /**
     * 专户名称
     */
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
     * 操作人ID
     */
    private String updateUser;
}
