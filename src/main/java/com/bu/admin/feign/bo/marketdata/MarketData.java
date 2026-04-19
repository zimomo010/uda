package com.bu.admin.feign.bo.marketdata;

import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.common.FileType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * product underlying po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MarketData extends QueryBase implements Serializable {

    private Integer id;
    private String securityCode;
    private String securityName;
    private FileType fileType;
    private String areaCode;     //区域

    private String priceDateStr;         //价格日
    private Date fileTime;
    private String busId;
}
