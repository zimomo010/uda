package com.bu.admin.feign.service;

import com.bu.admin.feign.bo.mail.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 邮件 rpc client
 *
 * @author liujiegang
 * @date 2024/5/26 16:54
 */
@FeignClient(value = "gj-product-center", contextId = "mailRpcClient")
public interface MailRpcClient {

    /**
     * 创建邮件模板
     * @param addBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/mail/template/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String addTemplate(@RequestBody AddMailTemplateBO addBO, @RequestHeader("MiServerName") String serverName,
                       @RequestHeader("Authorization") String serverToken);

    /**
     * 修改邮件模板
     * @param updateBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/mail/template/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String updateTemplate(@RequestBody UpdateMailTemplateBO updateBO, @RequestHeader("MiServerName") String serverName,
                          @RequestHeader("Authorization") String serverToken);

    /**
     * 删除邮件模板
     * @param delBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/mail/template/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String deleteTemplate(@RequestBody DelMailTemplateBO delBO, @RequestHeader("MiServerName") String serverName,
                          @RequestHeader("Authorization") String serverToken);

    /**
     * 分页查询邮件模板
     * @param pageQueryBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/mail/template/page", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String pageTemplate(@RequestBody PageQueryMailTemplateBO pageQueryBO, @RequestHeader("MiServerName") String serverName,
                        @RequestHeader("Authorization") String serverToken);

    /**
     * 查询邮件模板
     * @param queryBO
     * @param serverName
     * @param serverToken
     * @return
     */
    @PostMapping(value = "/server/mail/template/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    String queryTemplate(@RequestBody QueryMailTemplateBO queryBO, @RequestHeader("MiServerName") String serverName,
                         @RequestHeader("Authorization") String serverToken);

    /**
     * 邮件模板的详情
     * @param id
     * @param serverName
     * @param serverToken
     * @return
     */
    @GetMapping(value = "/server/mail/template/get", produces = MediaType.APPLICATION_JSON_VALUE)
    String getTemplate(@RequestParam Long id, @RequestHeader("MiServerName") String serverName, @RequestHeader("Authorization") String serverToken);
}
