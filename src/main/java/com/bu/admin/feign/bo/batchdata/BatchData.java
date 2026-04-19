package com.bu.admin.feign.bo.batchdata;

import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.bo.transaction.EquTradeConfig;
import com.bu.admin.feign.enumtype.common.BatchDealBusTypes;
import com.bu.admin.feign.enumtype.common.BatchDealStatus;
import com.bu.admin.feign.enumtype.common.FileType;
import com.bu.admin.feign.enumtype.common.StoreType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BatchData extends QueryBase {

    private String id;

    private String batchNo;

    private String busId;

    private String fileUrl;

    private String fileDesc;

    private StoreType storeType;

    private FileType fileType;

    private BatchDealBusTypes dealBusType;

    private BatchDealStatus dealStatus;

    private List<EquTradeConfig> equTradeConfigs;

}
