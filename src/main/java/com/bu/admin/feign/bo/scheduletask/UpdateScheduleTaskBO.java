package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;

import java.util.List;

/**
 * 修改计划任务
 *
 * @author liujiegang
 * @date 2024/5/25 13:35
 */
@Data
public class UpdateScheduleTaskBO {

    /**
     * id
     */
    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类型
     */
    private String type;

    /**
     * 邮件模板ID
     */
    private Long mailTemplateId;

    /**
     * 实际出资人ID
     */
    private String contributionUserId;

    /**
     * 票据持有人ID
     */
    private String billUserId;

    /**
     * 投顾ID
     */
    private String investmentAdviserId;

    /**
     * 修改人ID
     */
    private String updateUser;

    /**
     * 时间规则
     */
    private List<ScheduleTaskTimeRuleBO> timeRuleList;

    /**
     * 任务的票据note id
     */
    private List<String> billNoteIdList;

    /**
     * 发送邮箱
     */
    private List<ScheduleTaskToMailBO> toMailList;
}
