package com.bu.admin.business.scheduletask.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 导出计划任务的执行日志
 *
 * @author liujiegang
 * @date 2024/5/30 20:30
 */
@Data
public class WebExportScheduleTaskExecuteLogRequest {

    /**
     * 任务ID
     */
    @NotNull
    private Long taskId;

    /**
     * 任务的业务类型
     */
    @NotBlank
    private String busType;

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
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date executeStartTime;

    /**
     * 执行时间-结束
     */
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date executeEndTime;
}
