package com.bu.admin.business.uda.bo;

import lombok.Data;

import java.io.Serializable;


@Data
public class SaasUser implements Serializable {


    private String userName;
    private String password;

}
