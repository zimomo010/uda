package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;


/**
 * 计划任务的执行日志 Response
 *
 * @author liujiegang
 * @date 2024/5/30 20:19
 */
@Data
public class ScheduleTaskExecuteLogResponseBO {

    /**
     * id
     */
    private Long id;

    /**
     * 任务ID
     */
    private Long scheduleTaskId;

    /**
     * 票据note id
     */
    private String billNoteId;

    /**
     * 执行状态
     */
    private String status;

    /**
     * 执行时间
     */
    private String executeTime;

    /**
     * 执行失败原因
     */
    private String failReason;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * valuation summary json格式数据
     */
    private String valuationSummaryData;
}
