package com.bu.admin.feign.bo.product;

import com.bu.admin.extend.bo.QueryBase;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * product underlying po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SecurityBasic extends QueryBase implements Serializable {


    private Integer id;
    private String securityType;
    private String securityCode;                  //证券编号
    private String securityName;                  //证券名称
    private String securityNameEn;                //证券名称
    private String areaCode;                   //地区编码
    private String marketName;                    //市场名称
    private String fundDate;                      //成立时间
    private String issuer;
    private String issuerIndustry;
    private BigDecimal minPiece;
    private BigDecimal cpn;
    private String cpnType;
    private Integer cpnFreq;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate prevCpnDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate nxtCpnDate;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate firstCpnDate;
    private String dayCntDes;

    private String currencyType;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate maturity;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate nxtCallDate;
    private BigDecimal nxtCallPx;
    private String industryGroup;
    private String classificationName;
    private String guarantor;
    private String parentCompName;
    private String globalGuarantorName;

    private BigDecimal pxBid = new BigDecimal(0);    //
    private BigDecimal pxAsk = new BigDecimal(0);
    private BigDecimal pxLast = new BigDecimal(0);
    private BigDecimal pxDirtyBid = new BigDecimal(0);
    private BigDecimal pxDirtyAsk = new BigDecimal(0);

    private String priceDateStr;

}
