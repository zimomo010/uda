package com.bu.admin.business.order.controller;


import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.fileupload.service.FileUploadService;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.feign.bo.file.BusFile;
import com.bu.admin.feign.bo.transaction.TransTemplate;
import com.bu.admin.feign.enumtype.common.FileType;
import com.bu.admin.feign.enumtype.report.OutputFileType;
import com.bu.admin.feign.enumtype.report.TemplateConfigSecType;
import com.bu.admin.feign.enumtype.report.TransTemplateType;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.service.TransTemplateRpcClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Api
@RestController
@RequestMapping("/web/transTemplate")
@WsOperation("组合文件模版管理")
public class WebTransTemplateController {


    @Resource
    private TransTemplateRpcClient transTemplateRpcClient;
    @Resource
    private FileUploadService fileUploadService;
    @Resource
    private ProductCenterClient productCenterClient;


    @ApiOperation(value = "获取组合关联模板列表", notes = "获取组合关联模板列表")
    @GetMapping(value = "/getTransTemplateList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取组合关联模板列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String getTransTemplateList(@ModelAttribute TransTemplate transTemplate, @RequestHeader("Authorization") String token) {
        transTemplate.setTransTemplateType(TransTemplateType.WORD_REPORT);
        return Response.ok(transTemplateRpcClient.getTransTemplateList(transTemplate,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "获取组合关联文件列表", notes = "获取组合关联文件列表")
    @GetMapping(value = "/getTransBusFileList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取组合关联文件列表", ignoreLogFields = {"Authorization"}, requiredParams = {"transId", "noteId"})
    public String getTransBusFileList(@ModelAttribute TransTemplate transTemplate, @RequestHeader("Authorization") String token) {
        BusFile busFile = new BusFile();
        busFile.setBusType(FileType.TRANSACTION);
        busFile.setBusId(transTemplate.getNoteId());
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.findBusFileList(
                busFile, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "关联组合模板", notes = "关联组合模板")
    @PostMapping(value = "/addOrModifyTransTemplate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "关联组合模板", ignoreLogFields = {"Authorization"})
    public String addOrModifyTransTemplate(@RequestBody TransTemplate transTemplate, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transTemplate.setTransTemplateType(TransTemplateType.WORD_REPORT);
        transTemplate.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transTemplateRpcClient.addOrModifyTransTemplate(transTemplate,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "自定义模版文件上传", notes = "自定义模版文件上传")
    @PostMapping(value = "/uploadTemplateFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=multipart/form-data")
    @WsOperation(value = "自定义模版文件上传", ignoreLogFields = "Authorization")
    public String uploadFile(@RequestParam(value = "file") MultipartFile file,
                             @RequestParam(value = "id", required = false) Integer id,
                             @RequestParam(value = "transId") Integer transId,
                             @RequestParam(value = "templateId", required = false) Integer templateId,
                             @RequestParam(value = "noteId") String noteId,
                             @RequestParam(value = "configSecType") TemplateConfigSecType configSecType,
                             @RequestHeader("Authorization") String token) {
        String fileUrl = fileUploadService.newNasFile(file, setTemplateFileName(noteId, configSecType));
        TransTemplate transTemplate = new TransTemplate();
        transTemplate.setId(id);
        transTemplate.setTemplateId(templateId);
        transTemplate.setTransId(transId);
        transTemplate.setTemplateUrl(fileUrl);
        transTemplate.setTransTemplateType(TransTemplateType.WORD_REPORT);
        transTemplate.setConfigSecType(configSecType);
        transTemplate.setUpdateUser(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(transTemplateRpcClient.addOrModifyTransTemplate(transTemplate,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "自定义模版下载")
    @GetMapping(value = "/downloadCusTemplateFile")
    @WsOperation(value = "自定义模版下载", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String downloadCusTemplateFile(@Context HttpServletResponse response,
                                          @ModelAttribute TransTemplate transTemplate,
                                          @RequestHeader("Authorization") String token) {
        transTemplate.setUpdateUser(WsContext.getContext().getUserId());
        TransTemplate result = ResponseCheckUtils.getBeanFromResponse(transTemplateRpcClient.getTransTemplateDetail(transTemplate,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()), TransTemplate.class);
        if (null != result) {
            File file = new File(result.getTemplateUrl());

            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ResponseCheckUtils.getHeaderContentDisposition(result.getTemplateUrl()));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            int bytesRead;
            byte[] buffer = new byte[1024];
            try (InputStream inputStream = new FileInputStream(file)) {
                response.setHeader("Content-Length", String.valueOf(inputStream.available()));
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
                return Response.ok().build();
            } catch (Exception e) {
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "");
                return Response.error(ErrorCodes.UNKNOWN_ERROR).build();
            }
        }
        return Response.error(ErrorCodes.DataDeal.OBJECT_NOT_EXIST).build();
    }


    @ApiOperation(value = "按照模版生成组合文档", notes = "按照模版生成组合文档")
    @GetMapping(value = "/generateFile", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "按照模版生成组合文档", ignoreLogFields = {"Authorization"})
    public String generateFile(@Context HttpServletResponse response,
                               @ModelAttribute TransTemplate transTemplate,
                               @RequestHeader("Authorization") String token) {
        transTemplate.setUpdateUser(WsContext.getContext().getUserId());
        JsonObject result = ResponseCheckUtils.checkResponse(transTemplateRpcClient.generateFile(transTemplate, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).getAsJsonObject();
        String fileName = result.get("busCode").getAsString();
        File file;
        if (null == transTemplate.getFileType()) {
            fileName = fileName.split("\\.")[0] + OutputFileType.PDF.getDesc();
            file = new File(result.get("fileUrl").getAsString().split("\\.")[0] + OutputFileType.PDF.getDesc());
        } else {
            file = new File(result.get("fileUrl").getAsString());
        }
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ResponseCheckUtils.getHeaderContentDisposition(fileName));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        int bytesRead;
        byte[] buffer = new byte[1024];
        try (InputStream inputStream = new FileInputStream(file)) {
            response.setHeader("Content-Length", String.valueOf(inputStream.available()));
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
            return Response.ok().build();
        } catch (Exception e) {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "");
            return Response.error(ErrorCodes.UNKNOWN_ERROR).build();
        }
    }

    @ApiOperation(value = "获取订单估值关联模板", notes = "获取订单估值关联模板")
    @GetMapping(value = "/getTransValuationTemplate", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取订单估值关联模板", ignoreLogFields = {"Authorization"}, requiredParams = {"transId"})
    public String getTransValuationTemplate(@ModelAttribute TransTemplate transTemplate, @RequestHeader("Authorization") String token) {
        return Response.ok(transTemplateRpcClient.getTransTemplateList(transTemplate,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "设置组合估值关联模板", notes = "设置组合估值关联模板")
    @PostMapping(value = "/addOrModifyTransValuationTemplate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "设置组合估值关联模板", ignoreLogFields = {"Authorization"}, requiredParams = {"configSecType","transTemplateType"})
    public String addOrModifyTransValuationTemplate(@RequestBody TransTemplate transTemplate, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        transTemplate.setUpdateUser(userId);
        return Response.ok(ResponseCheckUtils.checkResponse(transTemplateRpcClient.addOrModifyTransTemplate(transTemplate,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    //----------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------
    private String setTemplateFileName(String noteId, TemplateConfigSecType configSecType) {
        return "Template_" + noteId + "_" + configSecType.name() + OutputFileType.WORD.getDesc();
    }
}
