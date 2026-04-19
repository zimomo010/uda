package com.bu.admin.feign.bo.user;


import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.product.IdentifyType;
import com.bu.admin.feign.enumtype.user.VerifyStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户详细信息实体
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends QueryBase implements Serializable {


    private String eamId;
    private String cusId;
    private String cusName;
    private String mobileNumber;
    private String email;
    private IdentifyType identifyType;
    private String identifyNumber;
    private String remark;
    private String bankCode;
    private String bankNumber;

    private VerifyStatus verifyStatus;          //审核状态
    private VerifyStatus verifyStatusExclude;   //排除状态
    private Boolean valid;

}
