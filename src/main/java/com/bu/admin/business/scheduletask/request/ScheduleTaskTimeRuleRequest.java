package com.bu.admin.business.scheduletask.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 计划任务的时间规则
 *
 * @author liujiegang
 * @date 2024/5/25 10:24
 */
@Data
@ApiModel(value = "计划任务的时间规则")
public class ScheduleTaskTimeRuleRequest {

    /**
     * 时间规则
     */
    @NotBlank
    @ApiModelProperty(value = "时间规则：DAY=每日，WORK_DAY=工作日，WEEK=每周，MONTH=每月", required = true)
    private String timeRule;

    /**
     * 日期数字：每天=0 | 每周=[1-7] | 工作日=0 | 每月=[具体日期的数字]
     */
    @NotNull
    @ApiModelProperty(value = "日期数字：每天=0 | 每周=[1-7] | 工作日=0 | 每月=[具体日期的数字]", required = true)
    private Integer dateNum;

    /**
     * 时间：格式：00:00
     */
    @NotBlank
    @ApiModelProperty(value = "时间：格式：00:00", required = true)
    private String time;

    /**
     * 遇到节假日顺延：0=否，1=是
     */
    private Integer holidayPostponeFlag;
}
