package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.report.OutputFileType;
import com.bu.admin.feign.enumtype.report.TemplateConfigSecType;
import com.bu.admin.feign.enumtype.report.TransTemplateType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class TransTemplate extends BaseBo implements Serializable {


    private Integer id;
    private Integer transId;
    private String noteId;
    private TransTemplateType transTemplateType;
    private TemplateConfigSecType configSecType;
    private Integer templateId;
    private String templateUrl;
    private OutputFileType fileType;
    private Boolean valuationMark = false;
    private TransValuationParams transValuationParams;

}
