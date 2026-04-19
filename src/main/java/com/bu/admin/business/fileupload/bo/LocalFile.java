package com.bu.admin.business.fileupload.bo;


import com.bu.admin.feign.enumtype.common.FileType;
import lombok.Data;

@Data
public class LocalFile {
    private String fileName;

    private String localUrl;

    private FileType fileType;

    private String targetFolderName;

    private String busData;

}
