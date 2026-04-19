package com.bu.admin.feign.enumtype.user;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum SaasUserType {

    COMMON_USER("普通用户"),
    EAM_USER("EAM用户"),
    PLAT_OP("平台运营"),
    ISSUER_USER("发行商"),
    CHANNEL_USER("渠道商"),
    END_CLIENT_USER("终端客户"),
    IA_USER("投资人");
    SaasUserType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
