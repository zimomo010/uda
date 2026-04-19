package com.bu.admin.feign.bo.mail;

import lombok.Data;

/**
 * 删除邮件模板
 *
 * @author liujiegang
 * @date 2024/5/23 21:05
 */

@Data
public class DelMailTemplateBO {

    /**
     * id
     */
    private Long id;

    /**
     * 修改人ID
     */
    private String updateUser;
}
