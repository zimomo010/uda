package com.bu.admin.feign.bo.content;


import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Created by mac on 14/10/28.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BankCDData extends QueryBase {

    private String id;
    private String dataDate;
    private String moody;
    private CurrencyType ccy;
    private String sAndP;
    private String threeMTH;
    private String sixMTH;
    private String nineMTH;
    private String twelveMTH;
    private String minSize;
    private String bankName;


}
