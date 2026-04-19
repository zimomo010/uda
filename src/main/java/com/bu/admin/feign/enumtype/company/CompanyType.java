package com.bu.admin.feign.enumtype.company;

/**
 * Created by ghostWu on 2017/5/9.
 */
public enum CompanyType {

    EAM("EAM"),
    PLATFORM("平台公司"),
    CHANNEL("渠道公司"),
    IAA("IAA公司"),
    CLIENT("终端公司"),
    ISSUER("发行公司");

    CompanyType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
