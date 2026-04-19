package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;

/**
 * 计划任务的发送邮箱
 *
 * @author liujiegang
 * @date 2024/5/25 10:27
 */
@Data
public class ScheduleTaskToMailBO {

    /**
     * 发送邮箱
     */
    private String toMail;
}
