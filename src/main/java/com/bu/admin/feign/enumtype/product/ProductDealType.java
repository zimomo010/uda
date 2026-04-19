package com.bu.admin.feign.enumtype.product;

/**
 * Created by GhostWu
 * product deal type
 */
public enum ProductDealType {

    CLN("Credit Linked Notes"),
    FLN("Fixed Income Linked Note"),
    BLN("Bond Linked Note"),
    ELN("Equity Linked Note"),
    FXLN("FX linked Note"),
    TRS("Total Return Swap"),
    CLIENT_REPO("Client Repo"),
    AMC("Actively Managed Certificate"),
    OLN("Option Linked Note"),
    VIRTUAL("Virtual")
    ;

    ProductDealType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

}
