package com.bu.admin.business.mail.request;

import com.bu.admin.extend.bo.BaseBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询邮件模板
 *
 * @author liujiegang
 * @date 2024/5/23 21:26
 */
@Data
@ApiModel(value = "分页查询邮件模板")
@EqualsAndHashCode(callSuper = true)
public class WebPageQueryMailTemplateRequest extends BaseBo {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
}
