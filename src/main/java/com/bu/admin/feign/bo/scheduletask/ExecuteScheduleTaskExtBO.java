package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;

import java.time.LocalDate;

/**
 * 执行计划任务 扩展信息
 *
 * @author liujiegang
 * @date 2024/8/21
 */
@Data
public class ExecuteScheduleTaskExtBO {

    /**
     * 客户下单日
     */
    private LocalDate tradeDate;

    private String ac;

    private String ssi;
}
