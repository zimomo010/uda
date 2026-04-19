package com.bu.admin.feign.bo.scheduletask;

import lombok.Data;

import java.util.List;

/**
 * 删除计划任务
 *
 * @author liujiegang
 * @date 2024/6/20 19:00
 */
@Data
public class DelScheduleTaskBO {

    /**
     * id
     */
    private List<Long> idList;

    /**
     * 修改人ID
     */
    private String updateUser;
}
