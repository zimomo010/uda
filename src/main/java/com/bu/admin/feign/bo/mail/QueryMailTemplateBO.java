package com.bu.admin.feign.bo.mail;

import lombok.Data;

import java.util.List;

/**
 * 查询邮件模板
 *
 * @author liujiegang
 * @date 2024/5/27 10:05
 */
@Data
public class QueryMailTemplateBO {

    /**
     * id
     */
    private List<Long> idList;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 归属机构code
     */
    private String departCode;
}
