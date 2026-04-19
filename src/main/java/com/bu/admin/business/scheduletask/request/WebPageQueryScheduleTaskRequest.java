package com.bu.admin.business.scheduletask.request;

import com.bu.admin.extend.bo.BaseBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "分页查询计划任务")
@EqualsAndHashCode(callSuper = true)
public class WebPageQueryScheduleTaskRequest extends BaseBo {

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String name;

    /**
     * 任务类型
     */
    @ApiModelProperty(value = "任务类型：MAIL=邮件，CLOUD=云盘")
    private List<String> typeList;

    /**
     * 任务业务类型
     */
    @ApiModelProperty(value = "任务业务类型")
    private List<String> busTypeList;

    /**
     * 任务状态
     */
    @ApiModelProperty(value = "任务状态：ENABLE=启用，DISABLE=禁用")
    private List<String> statusList;

    /**
     * 执行状态
     */
    @ApiModelProperty(value = "执行状态：SUCCESS=成功，FAIL=失败，PART_FAIL=部分失败")
    private List<String> executeStatusList;
}
