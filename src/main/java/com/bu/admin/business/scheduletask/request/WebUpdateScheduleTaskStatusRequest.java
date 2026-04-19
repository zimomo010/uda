package com.bu.admin.business.scheduletask.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 修改计划任务的状态
 *
 * @author liujiegang
 * @date 2024/5/27 19:16
 */
@Data
@ApiModel(value = "修改计划任务的状态")
public class WebUpdateScheduleTaskStatusRequest {

    /**
     * id
     */
    @NotNull
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 任务状态
     */
    @NotBlank
    @ApiModelProperty(value = "任务状态：ENABLE=启用，DISABLE=禁用", required = true)
    private String status;
}
