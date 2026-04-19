package com.bu.admin.feign.bo.funding;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class FundingPlanMonth implements Serializable {


    private List<FundingPlanKey> fundingPlanKeyList = new ArrayList<>();
    private String dataName;

}
