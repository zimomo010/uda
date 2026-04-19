package com.bu.admin.business.quartzjob.bo;

import com.bu.admin.business.quartzjob.enums.QuartzJobType;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class QuartZBo {
    private QuartzJobType quartZJobType;
    private Map<String, Object> params = new HashMap<>();
}
