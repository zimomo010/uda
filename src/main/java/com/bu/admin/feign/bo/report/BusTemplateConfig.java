package com.bu.admin.feign.bo.report;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.common.FileType;
import com.bu.admin.feign.enumtype.common.StoreType;
import com.bu.admin.feign.enumtype.report.OutputFileType;
import com.bu.admin.feign.enumtype.report.TemplateConfigSecType;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class BusTemplateConfig extends BaseBo {

    private Integer id;
    private StoreType storeType;
    private FileType configType;
    private TemplateConfigSecType configSecType;
    private OutputFileType fileType;
    private String configName;
    private String configContent;
    private String templateFileName;
}
