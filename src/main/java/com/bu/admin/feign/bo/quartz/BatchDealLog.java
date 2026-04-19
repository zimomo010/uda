package com.bu.admin.feign.bo.quartz;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.quartz.BatchDealType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BatchDealLog extends BaseBo {

    private Long id;

    private String batchNo;

    private String fileUrl;

    private BatchDealType dealType;

}
