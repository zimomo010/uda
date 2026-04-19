package com.bu.admin.feign.enumtype.transaction;

public enum FinancingType {
    ORIGIN("融资"),
    HEDGE("Hedge融资"),
    HOUSE_REPO("house repo"),
    CLIENT_REPO("client repo"),
    STRUCTURED_NOTE("structured note"),
    QFII("QFII"),
    EQD("EQD"),
    D1("D1"),
    PROP("prop"),
    OTHERS("others"),
    TRS("TRS"),
    SMCN("SMCN"),
    ;

    FinancingType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
