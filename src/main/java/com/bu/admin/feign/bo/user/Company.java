package com.bu.admin.feign.bo.user;


import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.company.CompanyType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 机构实体
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Company extends BaseBo implements Serializable {

    private String id;
    private CompanyType companyType;        //公司类型
    private String companyName;             //公司名称
    private String companyNameCh;           //公司名称
    private String enterpriseNumber;        //公司唯一编号
    private String regAddress;              //公司地址
    private String mailingAddress;          //公司地址
    private String foundDate;               //成立时间
    private String licenceExpireDate;       //营业执照到期日
    private String licenseFileUrl;          //营业执照

    private String phoneNo;                 //联系电话
    private String faxNo;                   //传真号码
    private String employeesNumbers;        //员工数量
    private String managementSize;          //管理规模

    private String extendInfo;        //扩展信息
    private String relationFile1;     //金融许可证
    private String relationFile2;     //标准结算指令ocr
    private String relationFile3;     //W-8BEN表格
    private String relationFile4;     //最近三年财务报告
    private String relationFile5;     //反洗钱信
    private Boolean identification;
    private String companyCode;       //国君ID


}
