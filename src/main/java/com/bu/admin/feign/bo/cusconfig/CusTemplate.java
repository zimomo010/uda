package com.bu.admin.feign.bo.cusconfig;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.common.EnableStatus;
import com.bu.admin.feign.enumtype.product.ProductDealType;
import com.bu.admin.feign.enumtype.report.ReportType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * company po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CusTemplate extends BaseBo {

    private Long id;

    private ProductDealType dealType;       //票价分类

    private ReportType reportType;          //报告类型

    private String templateName;            //模版名称

    private String downloadName;            //下载名称

    private String paramsContent;           //字段配置

    private List<TemplateCusParam> templateCusParamList = new ArrayList<>();

    private EnableStatus enableStatus;      //启用状态

    private String cusDepartCode;

}
