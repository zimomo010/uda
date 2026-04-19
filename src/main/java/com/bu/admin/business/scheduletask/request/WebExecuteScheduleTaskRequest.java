package com.bu.admin.business.scheduletask.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 执行计划任务
 *
 * @author liujiegang
 * @date 2024/6/3 10:27
 */
@Data
@ApiModel(value = "执行计划任务")
public class WebExecuteScheduleTaskRequest {

    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    @NotNull
    private Long id;

    /**
     * 任务的票据note id
     */
    private List<String> billNoteIdList;

    /**
     * 扩展信息
     */
    private ExecuteScheduleTaskExtRequest ext;

    /**
     * 票据数据检查：0=否，1=是，默认=0
     */
    private Integer accumulatedDailyCheckMarkFlag;
}
