package com.bu.admin.feign.bo.funding;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class FundingCurveConfig implements Serializable {

    private String userId;
    private List<FundingPlanKey> fundingCurveList = new ArrayList<>();
    private Date updateTime;
}
