package com.bu.admin.business.scheduletask.controller;

import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.scheduletask.request.*;
import com.bu.admin.business.scheduletask.service.ScheduleTaskService;
import com.bu.admin.feign.bo.scheduletask.*;
import com.bu.admin.feign.service.ScheduleTaskRpcClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.utils.BeanCopyUtils;
import com.bu.admin.utils.JSONUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.OutputStream;

/**
 * 计划任务 web controller
 *
 * @author liujiegang
 * @date 2024/5/27 09:45
 */
@Slf4j
@Api(value = "计划任务")
@RestController
@RequestMapping("/web/schedule/task")
@WsOperation("计划任务")
@RequiredArgsConstructor
public class WebScheduleTaskController {

    private final ScheduleTaskRpcClient scheduleTaskRpcClient;
    private final ScheduleTaskService scheduleTaskService;

    /**
     * 新增
     * @param addRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "新增")
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新增", ignoreLogFields = {"Authorization"})
    public String add(@RequestBody @Validated WebAddScheduleTaskRequest addRequest, @RequestHeader("Authorization") String token) {
        AddScheduleTaskBO addBO = BeanCopyUtils.convertBusinessValue(addRequest, AddScheduleTaskBO.class);
        addBO.setCreateUser(WsContext.getContext().getUserId());

        return scheduleTaskRpcClient.add(addBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 修改
     * @param updateRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "修改")
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新增", ignoreLogFields = {"Authorization"})
    public String update(@RequestBody @Validated WebUpdateScheduleTaskRequest updateRequest, @RequestHeader("Authorization") String token) {
        UpdateScheduleTaskBO updateBO = BeanCopyUtils.convertBusinessValue(updateRequest, UpdateScheduleTaskBO.class);
        updateBO.setUpdateUser(WsContext.getContext().getUserId());
        log.info("updateBO: {}", updateBO);
        return scheduleTaskRpcClient.update(updateBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 详情
     * @param id
     * @param token
     * @return
     */
    @ApiOperation(value = "详情")
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "详情", ignoreLogFields = {"Authorization"})
    public String get(@RequestParam Long id, @RequestHeader("Authorization") String token) {
        return scheduleTaskRpcClient.get(id, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 分页查询
     * @param pageQueryRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/page", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "分页查询", ignoreLogFields = {"Authorization"})
    public String page(@RequestBody @Validated WebPageQueryScheduleTaskRequest pageQueryRequest, @RequestHeader("Authorization") String token) {
        PageQueryScheduleTaskBO pageQueryBO = BeanCopyUtils.convertBusinessValue(pageQueryRequest, PageQueryScheduleTaskBO.class);

        return scheduleTaskRpcClient.page(pageQueryBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 修改状态
     * @param updateRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "修改状态")
    @PostMapping(value = "/status/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改状态", ignoreLogFields = {"Authorization"})
    public String updateStatus(@RequestBody @Validated WebUpdateScheduleTaskStatusRequest updateRequest, @RequestHeader("Authorization") String token) {
        UpdateScheduleTaskStatusBO updateBO = BeanCopyUtils.convertBusinessValue(updateRequest, UpdateScheduleTaskStatusBO.class);
        updateBO.setUpdateUser(WsContext.getContext().getUserId());

        return scheduleTaskRpcClient.updateStatus(updateBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 执行任务
     * @param executeRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "执行任务")
    @PostMapping(value = "/execute", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "执行任务", ignoreLogFields = {"Authorization"})
    public String execute(@RequestBody @Validated WebExecuteScheduleTaskRequest executeRequest, @RequestHeader("Authorization") String token) {
        ExecuteScheduleTaskBO executeBO = JSONUtils.getJson(JSONUtils.toJson(executeRequest), ExecuteScheduleTaskBO.class);
        return scheduleTaskRpcClient.execute(executeBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 分页查询任务的执行日志
     * @param pageQueryRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "分页查询任务的执行日志")
    @PostMapping(value = "/execute/log/page", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "分页查询任务的执行日志", ignoreLogFields = {"Authorization"})
    public String pageForExecuteLog(@RequestBody @Validated WebPageQueryScheduleTaskExecuteLogRequest pageQueryRequest,
                                    @RequestHeader("Authorization") String token) {
        PageQueryScheduleTaskExecuteLogBO pageQueryBO = BeanCopyUtils.convertBusinessValue(pageQueryRequest, PageQueryScheduleTaskExecuteLogBO.class);

        return scheduleTaskRpcClient.pageForExecuteLog(pageQueryBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 任务的执行日志重新执行
     * @param request
     * @param token
     * @return
     */
    @ApiOperation(value = "任务的执行日志重新执行")
    @PostMapping(value = "/execute/log/restart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "任务的执行日志重新执行", ignoreLogFields = {"Authorization"})
    public String restartExecuteLog(@RequestBody @Validated WebReExecuteScheduleTaskLogRequest request, @RequestHeader("Authorization") String token) {
        ReExecuteScheduleTaskLogBO reExecuteScheduleTaskLogBO = new ReExecuteScheduleTaskLogBO();
        reExecuteScheduleTaskLogBO.setLogIdList(request.getLogIdList());
        reExecuteScheduleTaskLogBO.setUpdateUser(WsContext.getContext().getUserId());

        return scheduleTaskRpcClient.restartExecuteLog(reExecuteScheduleTaskLogBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 删除任务
     * @param request
     * @param token
     * @return
     */
    @ApiOperation(value = "删除任务")
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除任务", ignoreLogFields = {"Authorization"})
    public String delete(@RequestBody @Validated WebDelScheduleTaskRequest request, @RequestHeader("Authorization") String token) {
        DelScheduleTaskBO delBO = new DelScheduleTaskBO();
        delBO.setIdList(request.getIdList());
        delBO.setUpdateUser(WsContext.getContext().getUserId());

        return scheduleTaskRpcClient.delete(delBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 导出任务执行日志 excel
     * @param exportRequest
     * @param token
     * @param response
     */
    @ApiOperation(value = "导出任务执行日志 excel")
    @PostMapping(value = "/execute/log/export", consumes = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "导出任务执行日志 excel", ignoreLogFields = {"Authorization"})
    public void exportExecuteLog(@RequestBody @Validated WebExportScheduleTaskExecuteLogRequest exportRequest, @RequestHeader("Authorization") String token,
                                 HttpServletResponse response) {
        try {
            SXSSFWorkbook workbook = scheduleTaskService.generateExcel(exportRequest);
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                    ResponseCheckUtils.getHeaderContentDisposition(exportRequest.getTaskId() + "_" + System.currentTimeMillis() + ".xlsx"));
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            workbook.dispose();
        } catch (Exception e) {
            log.error("exportExecuteLog fail. message={}", e.getMessage(), e);
        }
    }
}
