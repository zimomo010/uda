package com.bu.admin.feign.bo.mail;

import lombok.Data;

/**
 * 修改邮件模板
 *
 * @author liujiegang
 * @date 2024/5/23 14:15
 */
@Data
public class UpdateMailTemplateBO {

    /**
     * id
     */
    private Long id;

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
     * 修改人ID
     */
    private String updateUser;
}
