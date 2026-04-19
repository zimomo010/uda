package com.bu.admin.feign.bo.system;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 系统功能接口集合
 * @Author ghostWu
 * @Date 2019-07-12 15:44
 * @Version 1.0
 */
public class SystemOperationList {

    @ApiModelProperty(value = "系统编码")
    private String code;

    @ApiModelProperty(value = "权限列表")
    private List<Operation> operationList = new ArrayList<>();


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }
}
