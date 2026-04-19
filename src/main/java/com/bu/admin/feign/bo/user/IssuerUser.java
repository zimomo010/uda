package com.bu.admin.feign.bo.user;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


/**
 * saas user bo
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IssuerUser extends SaasUser implements Serializable {

    private String bankCode;
    private String bankNumber;
    private String companyName;
}
