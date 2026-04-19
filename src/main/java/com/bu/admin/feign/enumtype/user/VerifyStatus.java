package com.bu.admin.feign.enumtype.user;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum VerifyStatus {

    WAIT_SUBMIT("待提交"),
    VERIFYING("审核中"),
    VERIFY_PASS("审核通过");

    VerifyStatus(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
