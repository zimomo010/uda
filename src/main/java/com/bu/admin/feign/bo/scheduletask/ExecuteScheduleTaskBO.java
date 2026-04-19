package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;

import java.util.List;

/**
 * 执行计划任务
 *
 * @author liujiegang
 * @date 2024/6/3 10:27
 */
@Data
public class ExecuteScheduleTaskBO {

    /**
     * 任务ID
     */
    private Long id;

    /**
     * 任务的票据note id
     */
    private List<String> billNoteIdList;

    /**
     * 扩展信息
     */
    private ExecuteScheduleTaskExtBO ext;

    /**
     * 票据数据检查：0=否，1=是
     */
    private Integer accumulatedDailyCheckMarkFlag;
}
