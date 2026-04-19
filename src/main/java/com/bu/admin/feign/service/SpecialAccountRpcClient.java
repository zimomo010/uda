package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.account.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 专户 rpc client
 *
 * @author liujiegang
 * @date 2024/6/9 17:14
 */
@FeignClient(value = "gj-product-center", contextId = "specialAccountRpcClient")
public interface SpecialAccountRpcClient {

    /**
     * 新增
     * @param addBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/account/special/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String add(@RequestBody AddSpecialAccountBO addBO, @RequestHeader("MiServerName") String serverName,
               @RequestHeader("Authorization") String serverToken);

    /**
     * 修改
     * @param updateBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/account/special/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String update(@RequestBody UpdateSpecialAccountBO updateBO, @RequestHeader("MiServerName") String serverName,
                  @RequestHeader("Authorization") String serverToken);

    /**
     * 删除
     * @param deleteBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/account/special/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteSpecialAccount(@RequestBody DeleteSpecialAccountBO deleteBO, @RequestHeader("MiServerName") String serverName,
                                @RequestHeader("Authorization") String serverToken);

    /**
     * 分页查询
     * @param pageQueryBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/account/special/page", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String page(@RequestBody PageQuerySpecialAccountBO pageQueryBO, @RequestHeader("MiServerName") String serverName,
                @RequestHeader("Authorization") String serverToken);

    /**
     * 获取专户最新编号
     * @param serverName
     * @param serverToken
     * @return
     */
    @GetMapping(value = "/server/account/special/getNewCode", produces = MediaType.APPLICATION_JSON_VALUE)
    String getSpecialAccountNewCode(@RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);

    /**
     * 导入专户数据
     * @param importBO
     * @param serverName
     * @param serverToken
     * @return String
     */
    @PostMapping(value = "/server/account/special/import", produces = MediaType.APPLICATION_JSON_VALUE)
    String importData(@RequestBody ImportSpecialAccountBO importBO, @RequestHeader("MiServerName") String serverName,
                      @RequestHeader("Authorization") String serverToken);
}
