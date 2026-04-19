package com.bu.admin.business.scheduletask.service;

import com.bu.admin.business.scheduletask.request.WebExportScheduleTaskExecuteLogRequest;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * 计划任务 service
 *
 * @author liujiegang
 * @date 2024/8/13
 */
public interface ScheduleTaskService {

    /**
     * 生成excel
     * @param exportRequest
     * @return
     */
    SXSSFWorkbook generateExcel(WebExportScheduleTaskExecuteLogRequest exportRequest);
}
