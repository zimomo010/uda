package com.bu.admin.feign.enumtype.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 平台用户业务类型
 *
 * @author liujiegang
 * @date 2024/6/3 19:39
 */
@Getter
@AllArgsConstructor
public enum SaasUserBusType {

    GTJA("发行人"),
    NOTE_HOLDER("票据持有人"),
    END_CLIENT("实际出资人"),
    IA("投顾"),
    ;

    private String desc;
}
