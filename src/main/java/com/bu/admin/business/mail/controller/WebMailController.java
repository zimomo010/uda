package com.bu.admin.business.mail.controller;

import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.mail.request.*;
import com.bu.admin.feign.bo.mail.*;
import com.bu.admin.feign.service.MailRpcClient;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * description
 *
 * @author liujiegang
 * @date 2024/5/26 17:04
 */
@Api(value = "邮件")
@RestController
@RequestMapping("/web/mail")
@WsOperation("邮件")
@RequiredArgsConstructor
public class WebMailController {

    private final MailRpcClient mailRpcClient;

    /**
     * 创建邮件模板
     * @param addRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "创建邮件模板")
    @PostMapping(value = "/template/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "创建邮件模板", ignoreLogFields = {"Authorization"})
    public String addTemplate(@RequestBody @Validated WebAddMailTemplateRequest addRequest, @RequestHeader("Authorization") String token) {
        AddMailTemplateBO addBO = BeanCopyUtils.convertBusinessValue(addRequest, AddMailTemplateBO.class);
        addBO.setCreateUser(WsContext.getContext().getUserId());

        return mailRpcClient.addTemplate(addBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 修改邮件模板
     * @param updateRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "修改邮件模板")
    @PostMapping(value = "/template/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改邮件模板", ignoreLogFields = {"Authorization"})
    public String updateTemplate(@RequestBody @Validated WebUpdateMailTemplateRequest updateRequest, @RequestHeader("Authorization") String token) {
        UpdateMailTemplateBO updateBO = BeanCopyUtils.convertBusinessValue(updateRequest, UpdateMailTemplateBO.class);
        updateBO.setUpdateUser(WsContext.getContext().getUserId());

        return mailRpcClient.updateTemplate(updateBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 删除邮件模板
     * @param delRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "删除邮件模板")
    @PostMapping(value = "/template/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除邮件模板", ignoreLogFields = {"Authorization"})
    public String deleteTemplate(@RequestBody @Validated WebDelMailTemplateRequest delRequest, @RequestHeader("Authorization") String token) {
        DelMailTemplateBO delBO = BeanCopyUtils.convertBusinessValue(delRequest, DelMailTemplateBO.class);
        delBO.setUpdateUser(WsContext.getContext().getUserId());

        return mailRpcClient.deleteTemplate(delBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 分页查询邮件模板
     * @param pageQueryRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "分页查询邮件模板")
    @PostMapping(value = "/template/page", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "分页查询邮件模板", ignoreLogFields = {"Authorization"})
    public String pageTemplate(@RequestBody @Validated WebPageQueryMailTemplateRequest pageQueryRequest, @RequestHeader("Authorization") String token) {
        PageQueryMailTemplateBO pageQueryBO = BeanCopyUtils.convertBusinessValue(pageQueryRequest, PageQueryMailTemplateBO.class);

        return mailRpcClient.pageTemplate(pageQueryBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 查询邮件模板
     * @param queryRequest
     * @param token
     * @return
     */
    @ApiOperation(value = "查询邮件模板")
    @PostMapping(value = "/template/query", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "查询邮件模板", ignoreLogFields = {"Authorization"})
    public String queryTemplate(@RequestBody @Validated WebQueryMailTemplateRequest queryRequest, @RequestHeader("Authorization") String token) {
        QueryMailTemplateBO queryBO = BeanCopyUtils.convertBusinessValue(queryRequest, QueryMailTemplateBO.class);

        return mailRpcClient.queryTemplate(queryBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 邮件模板的详情
     * @param id
     * @param token
     * @return
     */
    @ApiOperation(value = "邮件模板的详情")
    @GetMapping(value = "/template/get", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "邮件模板的详情", ignoreLogFields = {"Authorization"})
    public String getTemplate(@RequestParam Long id, @RequestHeader("Authorization") String token) {
        return mailRpcClient.getTemplate(id, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }
}
