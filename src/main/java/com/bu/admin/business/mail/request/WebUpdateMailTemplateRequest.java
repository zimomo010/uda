package com.bu.admin.business.mail.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改邮件模板
 *
 * @author liujiegang
 * @date 2024/5/23 14:15
 */
@Data
@ApiModel(value = "修改邮件模板")
public class WebUpdateMailTemplateRequest {

    /**
     * id
     */
    @NotNull
    @ApiModelProperty(value = "id", required = true)
    private Long id;

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
}
