package com.bu.admin.business.scheduletask.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 删除计划任务
 *
 * @author liujiegang
 * @date 2024/6/20 19:00
 */
@Data
public class WebDelScheduleTaskRequest {

    /**
     * id
     */
    @NotEmpty
    private List<Long> idList;
}
