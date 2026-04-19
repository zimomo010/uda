package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;

/**
 * 计划任务的时间规则
 *
 * @author liujiegang
 * @date 2024/5/25 10:24
 */
@Data
public class ScheduleTaskTimeRuleBO {

    /**
     * 时间规则
     */
    private String timeRule;

    /**
     * 日期数字：每天=0 | 每周=[1-7] | 工作日=0 | 每月=[具体日期的数字]
     */
    private Integer dateNum;

    /**
     * 时间：格式：00:00
     */
    private String time;

    /**
     * 遇到节假日顺延：0=否，1=是
     */
    private Integer holidayPostponeFlag;
}
