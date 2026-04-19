package com.bu.admin.business.mail.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 查询邮件模板
 *
 * @author liujiegang
 * @date 2024/5/27 10:05
 */
@Data
@ApiModel(value = "查询邮件模板")
public class WebQueryMailTemplateRequest {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private List<Long> idList;

    /**
     * 模板名称
     */
    @ApiModelProperty(value = "模板名称")
    private String name;

    /**
     * 归属机构code
     */
    @ApiModelProperty(value = "归属机构code")
    private String departCode;
}
