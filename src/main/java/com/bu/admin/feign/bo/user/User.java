package com.bu.admin.feign.bo.user;

import com.bu.admin.feign.enumtype.user.PhoneAreaCodes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户业务值对象
 *
 * @author ghostWu
 * @date 15/5/4
 */
@Data
@ApiModel(value = "用户对象")
public class User {

    //User
    @ApiModelProperty(name = "用户标识",notes = "用户标识 新增时不传，修改时必须传")
    private String id;

    @ApiModelProperty(name = "登录名",notes = "登录名 新增,修改,修改用户名 时必须传",example = "luziqian")
    private String userName;

    @ApiModelProperty(hidden = true)
    private Boolean valid;

    private String verifyCode;

    @ApiModelProperty(hidden = true)
    private String departName;

    @ApiModelProperty(hidden = true)
    private String userType;

    //UserDetail
    @ApiModelProperty(name = "用户名",notes = "用户名 新增，修改时必须传",example = "陆子谦")
    private String userAlias;

    @ApiModelProperty(name = "邮箱",notes = "邮箱 新增，修改时必须传",example = "934521324.@qq.com")
    private String email;

    @ApiModelProperty(value = "用户角色主键,多个以逗号分割的字符串")
    private String roleIds;

    @ApiModelProperty(value = "原密码:用户更改密码接口")
    private String oldPassword;

    @ApiModelProperty(value = "新密码:用户更改密码接口")
    private String userPassword;

    private PhoneAreaCodes userAreaCode; //用户区域

}
