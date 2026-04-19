package com.bu.admin.business.scheduletask.request;

import io.swagger.annotations.ApiModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 计划任务的发送邮箱
 *
 * @author liujiegang
 * @date 2024/5/25 10:27
 */
@Data
@ApiModel(value = "计划任务的发送邮箱")
public class ScheduleTaskToMailRequest {

    /**
     * 发送邮箱
     */
    @NotBlank
    @Email
    private String toMail;
}
