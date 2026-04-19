package com.bu.admin.feign.bo.mail;

import lombok.Data;

/**
 * 新增邮件模板
 *
 * @author liujiegang
 * @date 2024/5/23 14:15
 */
@Data
public class AddMailTemplateBO {

    /**
     * 模板名称
     */
    private String name;

    /**
     * 邮件标题
     */
    private String mailTitle;

    /**
     * 邮件模板内容
     */
    private String mailContent;

    /**
     * 创建人ID
     */
    private String createUser;

    /**
     * 归属机构code
     */
    private String departCode;

    /**
     * 归属顶级机构code
     */
    private String rootDepartCode;
}
