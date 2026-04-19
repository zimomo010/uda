package com.bu.admin.feign.enumtype.transaction;

public enum TransPaymentType {
    INTEREST_PAYMENT("Interest Payment"),
    OTHERS("Others"),
    ISSUER_FEE_PAYMENT("Issuer Fee Payment"),
    IA_FEE_PAYMENT("IA Fee Payment"),
    CALL_DATE("Call Date"),
    FEE_RESET("Fee Reset");


    TransPaymentType(String desc) {
        this.desc = desc;
    }
    private String desc;
    public String getDesc() {
        return desc;
    }
}
