package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;

import java.util.List;

/**
 * 新增计划任务
 * @author liujiegang
 * @date 2024/5/24 16:55
 */
@Data
public class AddScheduleTaskBO {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务的业务类型
     */
    private String busType;

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
     * 创建人ID
     */
    private String createUser;

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

    /**
     * 归属机构code
     */
    private String departCode;

    /**
     * 归属顶级机构code
     */
    private String rootDepartCode;
}
