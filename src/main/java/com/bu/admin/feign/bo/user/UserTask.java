package com.bu.admin.feign.bo.user;


import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.common.TaskStatus;
import com.tencentcloudapi.bm.v20180423.models.TaskType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * user task po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserTask extends QueryBase{


    private String id;
    private String userId;
    private TaskType taskType;
    private String busId;
    private String taskUrl;
    private Date taskDate;
    private TaskStatus taskStatus;
    private String remark;



}
