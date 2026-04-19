package com.bu.admin.feign.bo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 机构业务值对象
 *
 * @author ghostWu
 * @date 15/5/9
 */
@ApiModel
public class Department {

    @ApiModelProperty(notes = "机构标识:新增不传,更新 删除时必须传-走网关时使用")
    private String id;

    @ApiModelProperty(notes = "机构名称:新增,更新 必须传")
    private String departName;

    @ApiModelProperty(notes = "父级机构标识:新增，更新必须传 删除时可以不传")
    private String parentId;

    @ApiModelProperty(hidden = true)
    private String createUser;

    @ApiModelProperty(hidden = true)
    private String updateUser;

    @ApiModelProperty(hidden = true)
    private String departCode;

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
