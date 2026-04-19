package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;

/**
 * 修改计划任务的状态
 *
 * @author liujiegang
 * @date 2024/5/27 19:16
 */
@Data
public class UpdateScheduleTaskStatusBO {

    /**
     * id
     */
    private Long id;

    /**
     * 任务状态
     */
    private String status;

    /**
     * 修改人ID
     */
    private String updateUser;
}
