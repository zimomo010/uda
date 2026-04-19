package com.bu.admin.feign.bo.file;


import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.common.FileType;
import com.bu.admin.feign.enumtype.common.StoreType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BusFile extends QueryBase {

    private String id;
    private String busCode;
    private String busId;
    private String fileUrl;
    private String fileDesc;
    private StoreType storeType;
    private FileType busType;
    private Boolean limitMark;
    private String opUserId;

}
