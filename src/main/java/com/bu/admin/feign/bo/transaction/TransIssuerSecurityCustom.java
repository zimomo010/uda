package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransIssuerSecurityCustom extends BaseBo implements Serializable {

    private String secIsin;
    private String keepWellIndicator;
    private String sblcIndicator;
    private String desNotes;
    private String province;
    private String countryOfRisk;
    private String baselIiiDesignation;
    private String paymentRank;
    private String defaulted;

}
