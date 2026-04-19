package com.bu.admin.business.operationLog.bo;

import com.bu.admin.extend.bo.QueryBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class OperationLog extends QueryBase {
    private String userId;
    private String userName;
    private LocalDateTime usedTime;
    private String usedUrl;
    private String params;
    private int applicationStrategy;
}
