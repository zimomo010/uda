package com.bu.admin.business.member.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户详细信息实体
 *
 * @author ghostWu
 */
public class Member implements Serializable {

    public Member() {
    }

    public Member(String userId) {
        this.userId = userId;
    }

    private String userId;
    private String userName;
    private String userPassword;
    private String mobileNumber;
    private String userAlias;
    private String email;
    private Boolean valid;
    private String departCode;
    private String userDepartCode;
    private String rootDepartCode;
    private Date createTime;
    private Date updateTime;
    private String createUser;
    private String busId;
    private String verifyCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getRootDepartCode() {
        return rootDepartCode;
    }

    public void setRootDepartCode(String rootDepartCode) {
        this.rootDepartCode = rootDepartCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserDepartCode() {
        return userDepartCode;
    }

    public void setUserDepartCode(String userDepartCode) {
        this.userDepartCode = userDepartCode;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
