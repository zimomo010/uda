package com.bu.admin.feign.bo.cusconfig;

import lombok.Data;

import java.io.Serializable;

@Data
public class TemplateCusParam implements Serializable {

    private String originParamName;
    private String paramType;
    private String cusParamName;
    private String paramFormat;

}
