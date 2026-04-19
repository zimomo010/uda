package com.bu.admin.business.scheduletask.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 重新执行日志记录
 *
 * @author liujiegang
 * @date 2024/6/18 22:09
 */
@Data
public class WebReExecuteScheduleTaskLogRequest {

    /**
     * 日志ID
     */
    @NotEmpty
    private List<Long> logIdList;
}
