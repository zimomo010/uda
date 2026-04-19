package com.bu.admin.feign.enumtype.user;

/**
 * Created by  on 2015/3/16.
 * saas type
 */
public enum UserEventType {

    READ("产品"),
    LIKE("点赞"),
    CANCEL_LIKE("取消点赞"),
    FAVORITE("收藏"),
    CANCEL_FAVORITE("取消收藏"),
    OTHER("其他"),;

    UserEventType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
