package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.scheduletask.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 计划任务 rpc client
 *
 * @author liujiegang
 * @date 2024/5/27 09:22
 */
@FeignClient(value = "gj-product-center", contextId = "scheduleTaskRpcClient")
public interface ScheduleTaskRpcClient {

    /**
     * 新增
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/schedule/task/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String add(@RequestBody AddScheduleTaskBO addBO, @RequestHeader("MiServerName") String serverName,
               @RequestHeader("Authorization") String serverToken);

    /**
     * 修改
     * @param updateBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/schedule/task/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String update(@RequestBody UpdateScheduleTaskBO updateBO, @RequestHeader("MiServerName") String serverName,
                  @RequestHeader("Authorization") String serverToken);

    /**
     * 详情
     * @param id
     * @param serverName
     * @param serverToken
     * @return
     */
    @GetMapping(value = "/server/schedule/task/get", produces = MediaType.APPLICATION_JSON_VALUE)
    String get(@RequestParam Long id, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    /**
     * 分页查询
     * @param pageQueryBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/schedule/task/page", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String page(@RequestBody PageQueryScheduleTaskBO pageQueryBO, @RequestHeader("MiServerName") String serverName,
                @RequestHeader("Authorization") String serverToken);

    /**
     * 修改状态
     * @param updateBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/schedule/task/status/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateStatus(@RequestBody UpdateScheduleTaskStatusBO updateBO, @RequestHeader("MiServerName") String serverName,
                        @RequestHeader("Authorization") String serverToken);

    /**
     * 执行任务
     * @param executeBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/schedule/task/execute", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String execute(@RequestBody ExecuteScheduleTaskBO executeBO, @RequestHeader("MiServerName") String serverName,
                          @RequestHeader("Authorization") String serverToken);

    /**
     * 分页查询任务的执行日志
     * @param pageQueryBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/schedule/task/execute/log/page", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String pageForExecuteLog(@RequestBody PageQueryScheduleTaskExecuteLogBO pageQueryBO, @RequestHeader("MiServerName") String serverName,
                             @RequestHeader("Authorization") String serverToken);

    /**
     * 任务的执行日志重新执行
     * @param reExecuteLogBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/schedule/task/execute/log/restart", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String restartExecuteLog(@RequestBody ReExecuteScheduleTaskLogBO reExecuteLogBO, @RequestHeader("MiServerName") String serverName,
                             @RequestHeader("Authorization") String serverToken);

    /**
     * 删除任务
     * @param delBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/schedule/task/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String delete(@RequestBody DelScheduleTaskBO delBO, @RequestHeader("MiServerName") String serverName,
                  @RequestHeader("Authorization") String serverToken);
}
