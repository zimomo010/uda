package com.bu.admin.feign.bo.product;


import com.bu.admin.extend.bo.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductNotional extends BaseBo implements Serializable {

    private Integer id;
    private String productId;               //产品id

    private BigDecimal amount;
    private LocalDate effectiveDate;
    private Boolean effective;

    private String batchInputNo;            //批量导入流水号
}
