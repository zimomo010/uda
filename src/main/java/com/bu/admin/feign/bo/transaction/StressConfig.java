package com.bu.admin.feign.bo.transaction;

import com.bu.admin.extend.bo.BaseBo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.collections4.list.GrowthList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode(callSuper = true)
public class StressConfig extends BaseBo implements Serializable {

    private List<CommonStressConfig> categoryConfigs = new GrowthList<>();
    private List<CommonStressConfig> provinceConfigs = new GrowthList<>();
    private List<CommonStressConfig> liquidityConfigs = new GrowthList<>();

    private BigDecimal stressLowerBound;
    private BigDecimal stressUpperBound;
    private BigDecimal rangeStressedPrice;
    private BigDecimal idiosyncraticFactor;

    public Map<String, BigDecimal> getCategoryConfigMap() {
        if (null != categoryConfigs) {
            return categoryConfigs.stream().collect(Collectors.toMap(CommonStressConfig::getStressName, CommonStressConfig::getStressValue));
        }
        return Collections.emptyMap();
    }

    public Map<String, BigDecimal> getProvinceConfigMap() {
        if (null != provinceConfigs) {
            return provinceConfigs.stream().collect(Collectors.toMap(CommonStressConfig::getStressName, CommonStressConfig::getStressValue));
        }
        return Collections.emptyMap();
    }
}
