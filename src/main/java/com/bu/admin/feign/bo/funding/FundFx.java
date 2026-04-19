package com.bu.admin.feign.bo.funding;

import com.bu.admin.extend.bo.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class FundFx extends BaseBo implements Serializable {
    private Integer id;
}
