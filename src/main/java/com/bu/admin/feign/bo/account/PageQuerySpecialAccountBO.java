package com.bu.admin.feign.bo.account;

import com.bu.admin.extend.bo.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询
 *
 * @author liujiegang
 * @date 2024/6/9 16:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageQuerySpecialAccountBO extends BaseBo {

    /**
     * 关键字（简称、全称）
     */
    private String keyword;

    /**
     * 归属机构ID
     */
    private String companyId;
}
