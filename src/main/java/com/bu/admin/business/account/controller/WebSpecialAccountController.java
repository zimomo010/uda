package com.bu.admin.business.account.controller;

import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.account.request.WebAddSpecialAccountRequest;
import com.bu.admin.business.account.request.WebDeleteSpecialAccountRequest;
import com.bu.admin.business.account.request.WebPageQuerySpecialAccountRequest;
import com.bu.admin.business.account.request.WebUpdateSpecialAccountRequest;
import com.bu.admin.business.fileupload.service.FileUploadService;
import com.bu.admin.feign.bo.account.*;
import com.bu.admin.feign.service.SpecialAccountRpcClient;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.utils.BeanCopyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 专户 web controller
 *
 * @author liujiegang
 * @date 2024/6/9 17:22
 */
@RestController
@RequestMapping("/web/account/special")
@WsOperation("M001_专户管理")
@RequiredArgsConstructor
public class WebSpecialAccountController {

    private final SpecialAccountRpcClient specialAccountRpcClient;
    private final FileUploadService fileUploadService;

    @Value("${tcloud.localFile.store.path.sync}")
    private String syncFilePath;

    /**
     * 新增
     * @param addRequest
     * @param token
     * @return
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "M001_01_新增", ignoreLogFields = {"Authorization"})
    public String add(@RequestBody @Validated WebAddSpecialAccountRequest addRequest, @RequestHeader("Authorization") String token) {
        AddSpecialAccountBO addBO = BeanCopyUtils.convertBusinessValue(addRequest, AddSpecialAccountBO.class);
        addBO.setCreateUser(WsContext.getContext().getUserId());

        return specialAccountRpcClient.add(addBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 修改
     * @param updateRequest
     * @param token
     * @return
     */
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "M001_02_修改", ignoreLogFields = {"Authorization"})
    public String update(@RequestBody @Validated WebUpdateSpecialAccountRequest updateRequest, @RequestHeader("Authorization") String token) {
        UpdateSpecialAccountBO updateBO = BeanCopyUtils.convertBusinessValue(updateRequest, UpdateSpecialAccountBO.class);
        updateBO.setUpdateUser(WsContext.getContext().getUserId());

        return specialAccountRpcClient.update(updateBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 删除
     * @param deleteRequest
     * @param token
     * @return
     */
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "M001_03_删除", ignoreLogFields = {"Authorization"})
    public String deleteSpecialAccount(@RequestBody @Validated WebDeleteSpecialAccountRequest deleteRequest, @RequestHeader("Authorization") String token) {
        DeleteSpecialAccountBO deleteBO = BeanCopyUtils.convertBusinessValue(deleteRequest, DeleteSpecialAccountBO.class);
        deleteBO.setUpdateUser(WsContext.getContext().getUserId());

        return specialAccountRpcClient.deleteSpecialAccount(deleteBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 分页查询
     * @param pageQueryRequest
     * @param token
     * @return
     */
    @PostMapping(value = "/page", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "M001_04_分页查询", ignoreLogFields = {"Authorization"})
    public String page(@RequestBody @Validated WebPageQuerySpecialAccountRequest pageQueryRequest, @RequestHeader("Authorization") String token) {
        PageQuerySpecialAccountBO pageQueryBO = BeanCopyUtils.convertBusinessValue(pageQueryRequest, PageQuerySpecialAccountBO.class);

        return specialAccountRpcClient.page(pageQueryBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 获取专户最新编号
     * @param token
     * @return
     */
    @GetMapping(value = "/getNewCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "M001_05_获取专户最新编号", ignoreLogFields = {"Authorization"})
    public String getSpecialAccountNewCode(@RequestHeader("Authorization") String token) {
        return specialAccountRpcClient.getSpecialAccountNewCode(ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }

    /**
     * 导入专户数据
     * @param file
     * @param token
     * @return String
     */
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "M001_06_导入专户数据", ignoreLogFields = {"Authorization"})
    public String importData(@RequestParam(value = "file") MultipartFile file, @RequestHeader("Authorization") String token) {
        String fileUrl = fileUploadService.newNasFile(file, syncFilePath, file.getOriginalFilename());

        ImportSpecialAccountBO importBO = new ImportSpecialAccountBO();
        importBO.setFileUrl(fileUrl);
        importBO.setCreateUser(WsContext.getContext().getUserId());

        return specialAccountRpcClient.importData(importBO, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
    }
}
