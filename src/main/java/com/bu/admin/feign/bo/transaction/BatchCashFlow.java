package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.list.GrowthList;

import java.util.List;

/**
 *  BatchTransSecurityLog
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BatchCashFlow extends BaseBo {

    private List<CashFlow> cashFlowList = new GrowthList<>();
}
