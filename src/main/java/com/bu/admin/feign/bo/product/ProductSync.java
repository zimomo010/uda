package com.bu.admin.feign.bo.product;

import com.bu.admin.feign.enumtype.common.FileType;
import lombok.Data;

@Data
public class ProductSync {
    private FileType fileType;
    private String fileUrl;
}
