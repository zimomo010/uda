package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import com.bu.admin.feign.enumtype.product.CurrencyType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.list.GrowthList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * user security po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TSCouponPay extends BaseBo implements Serializable {


    private Integer id;

    private Integer usId;
    private Integer transId;
    private String noteId;
    private String secName;
    private String secIsin;

    private CurrencyType ccy;                   //币种

    private BigDecimal cpn;
    private BigDecimal cpnFreq;
    private String dayCntDes;
    private BigDecimal notional;
    private LocalDate paymentDate;
    private BigDecimal couponReceived;
    private String usIncomeIndicator;
    private LocalDate buyDate;
    private LocalDate valueDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate originPaymentDate;
    private List<Integer> transIds = new GrowthList<>();
}
