package com.bu.admin.feign.bo.user;

import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.product.IdentifyType;
import com.bu.admin.feign.enumtype.user.PhoneAreaCodes;
import com.bu.admin.feign.enumtype.user.SaasUserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 用户详细信息实体
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SaasUser extends QueryBase implements Serializable {

    private String id;
    private String userId;
    private String userName;
    private String ssoUserName;
    private String userAlias;
    private String email;
    private Boolean valid;
    private String userPassword;
    private String userDepartCode;
    private String verifyCode;
    private String busId;
    private SaasUserType userType;
    private Boolean identification;
    private IdentifyType identifyType;
    private String identifyFile;
    private Boolean isTrader;           //是否交易员，支持交易
    private Boolean isAdmin;            //是否管理员，一个机构只有一个
    private PhoneAreaCodes userAreaCode; //用户区域
    private List<String> roleIdList;
    private String roleCode;
    private String mainUserId;
}
