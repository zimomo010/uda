package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;

import java.util.List;

/**
 * 重新执行日志记录
 *
 * @author liujiegang
 * @date 2024/6/18 22:09
 */
@Data
public class ReExecuteScheduleTaskLogBO {

    /**
     * 日志ID
     */
    private List<Long> logIdList;

    /**
     * 操作人
     */
    private String updateUser;
}
