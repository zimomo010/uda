package com.bu.admin.feign.bo.system;

import lombok.Data;

/**
 * @ClassName OperationEntity
 * @Description 系统功能接口权限
 * @Author ghostWu
 * @Date 2019-07-12 15:44
 * @Version 1.0
 */
@Data
public class Operation {
    private String code;
    private String name;
    private String parentCode;
    private Boolean parent;

}