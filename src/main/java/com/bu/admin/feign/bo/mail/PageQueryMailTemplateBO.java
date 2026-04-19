package com.bu.admin.feign.bo.mail;

import com.bu.admin.extend.bo.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询邮件模板
 *
 * @author liujiegang
 * @date 2024/5/23 21:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageQueryMailTemplateBO extends BaseBo {

    /**
     * 名称
     */
    private String name;
}
