package com.bu.admin.feign.bo.pnl;


import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.pnl.PnlType;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.feign.enumtype.product.ProductDealType;
import com.bu.admin.feign.enumtype.transaction.CrossBoarderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.list.GrowthList;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Base
 * @Description Bo顶级父类
 * @Author ghostWu
 * @Date 2019-07-10 17:03
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PnlQueryBo extends QueryBase implements Serializable {

    private String startDateStr;
    private String endDateStr;
    private PnlType pnlType;

    private CurrencyType targetCcy;

    private List<Integer> transIds = new GrowthList<>();
    private String noteId;
    private Boolean leveraged;      //是否杠杆
    private String cusId;                   //客户id
    private String cusNoteManageId;         //iaaId
    private CrossBoarderType crossBoarderType;

    private String orderByParam;
    private ProductDealType dealType;
}
