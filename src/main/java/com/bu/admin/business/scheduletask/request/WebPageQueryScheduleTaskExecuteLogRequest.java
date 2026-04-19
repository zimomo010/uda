package com.bu.admin.business.scheduletask.request;

import com.bu.admin.extend.bo.BaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 查询计划任务的执行日志
 *
 * @author liujiegang
 * @date 2024/5/30 20:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WebPageQueryScheduleTaskExecuteLogRequest extends BaseBo {

    /**
     * 任务ID
     */
    private Long taskId;

    /**
     * 票据 note id
     */
    private String billNoteId;

    /**
     * 执行状态
     */
    private List<String> statusList;

    /**
     * 执行时间-开始
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date executeStartTime;

    /**
     * 执行时间-结束
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date executeEndTime;
}
