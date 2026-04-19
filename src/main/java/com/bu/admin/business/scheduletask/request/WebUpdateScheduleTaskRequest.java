package com.bu.admin.business.scheduletask.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 修改计划任务
 *
 * @author liujiegang
 * @date 2024/5/25 13:35
 */
@Data
@ApiModel(value = "修改计划任务")
public class WebUpdateScheduleTaskRequest {

    /**
     * id
     */
    @NotNull
    @ApiModelProperty(value = "id", required = true)
    private Long id;

    /**
     * 任务名称
     */
    @NotBlank
    @ApiModelProperty(value = "任务名称", required = true)
    private String name;

    /**
     * 任务类型
     */
    @NotBlank
    @ApiModelProperty(value = "任务类型：MAIL=邮件，CLOUD=云盘", required = true)
    private String type;

    /**
     * 邮件模板ID
     */
    @ApiModelProperty(value = "邮件模板ID")
    private Long mailTemplateId;

    /**
     * 实际出资人ID
     */
    @ApiModelProperty(value = "实际出资人ID")
    private String contributionUserId;

    /**
     * 票据持有人ID
     */
    @ApiModelProperty(value = "票据持有人ID")
    private String billUserId;

    /**
     * 投顾ID
     */
    @ApiModelProperty(value = "投顾ID")
    private String investmentAdviserId;

    /**
     * 时间规则
     */
    @Valid
    @ApiModelProperty(value = "时间规则", required = true)
    private List<ScheduleTaskTimeRuleRequest> timeRuleList;

    /**
     * 任务的票据note id
     */
    @ApiModelProperty(value = "任务的票据note id")
    private List<String> billNoteIdList;

    /**
     * 发送邮箱
     */
    @Valid
    @ApiModelProperty(value = "发送邮箱")
    private List<ScheduleTaskToMailRequest> toMailList;
}
