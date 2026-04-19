package com.bu.admin.business.mail.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 新增邮件模板
 *
 * @author liujiegang
 * @date 2024/5/26 17:03
 */
@Data
@ApiModel(value = "创建邮件模板")
public class WebAddMailTemplateRequest {

    /**
     * 模板名称
     */
    @NotBlank
    @ApiModelProperty(value = "模板名称", required = true)
    private String name;

    /**
     * 邮件标题
     */
    @NotBlank
    @ApiModelProperty(value = "邮件标题", required = true)
    private String mailTitle;

    /**
     * 邮件模板内容
     */
    @NotBlank
    @ApiModelProperty(value = "邮件模板内容", required = true)
    private String mailContent;

    /**
     * 归属机构code
     */
    @ApiModelProperty(value = "归属机构code")
    private String departCode;

    /**
     * 归属顶级机构code
     */
    @ApiModelProperty(value = "归属顶级机构code")
    private String rootDepartCode;
}
