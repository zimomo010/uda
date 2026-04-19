package com.bu.admin.feign.bo.transaction;

import com.bu.admin.feign.enumtype.product.CurrencyType;
import com.bu.admin.utils.adapter.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * product event po
 *
 * @author ghostWu
 */
@Data
public class TransCredit implements Serializable {

    private Integer transId;
    private String reference;
    private String referenceIsin;
    private String referenceCurveId;

    private String refSecName;
    private String refIssuer;
    private BigDecimal refCoupon;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate refMaturity;
    private BigDecimal refAmountIssued;
    private String refPaymentRank;

    private CurrencyType cdsCurrency;
    private BigDecimal cdsFeeBase;
    private BigDecimal cdsFeeRate;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate cdsSettleDate;
    @JsonAdapter(LocalDateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate cdsMaturity;

    private String updateUser;
}
