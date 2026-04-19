package com.bu.admin.feign.bo.scheduletask;

import com.bu.admin.extend.bo.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页查询计划任务
 *
 * @author liujiegang
 * @date 2024/5/25 15:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageQueryScheduleTaskBO extends BaseBo {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务类型
     */
    private List<String> typeList;

    /**
     * 任务业务类型
     */
    private List<String> busTypeList;

    /**
     * 任务状态
     */
    private List<String> statusList;

    /**
     * 执行状态
     */
    private List<String> executeStatusList;
}
