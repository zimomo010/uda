package com.bu.admin.business.mail.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 删除邮件模板
 *
 * @author liujiegang
 * @date 2024/5/23 21:05
 */

@Data
@ApiModel(value = "删除邮件模板")
public class WebDelMailTemplateRequest {

    /**
     * id
     */
    @NotNull
    @ApiModelProperty(value = "id", required = true)
    private Long id;
}
