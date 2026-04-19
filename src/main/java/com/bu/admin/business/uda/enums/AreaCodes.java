package com.bu.admin.business.uda.enums;

import lombok.Getter;


@Getter
public enum AreaCodes {

    BR("BR"),
    CA("CA"),
    DE("DE"),
    FR("FR"),
    GB("GB"),
    IN("IN"),
    JP("JP"),
    KR("KR"),
    MX("MX"),
    RU("RU"),
    US("US");

    AreaCodes(String desc) {
        this.desc = desc;
    }

    private final String desc;

}
