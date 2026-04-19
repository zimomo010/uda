package com.bu.admin.business.report.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.feign.bo.report.BusTemplateConfig;
import com.bu.admin.feign.bo.report.Report;
import com.bu.admin.feign.enumtype.common.FileType;
import com.bu.admin.feign.enumtype.report.OutputFileType;
import com.bu.admin.feign.enumtype.report.RequestUserType;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.utils.worddeal.WordUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Api
@RestController
@RequestMapping("/web/report")
@WsOperation("文件报告接口")
public class WebReportController {

    @Value("${tcloud.localFile.store.path.template}")
    private String localFileTemplatePath;
    @Resource
    private ProductCenterClient productCenterClient;


    @ApiOperation(value = "获取报告列表数据", notes = "获取报告列表数据")
    @PostMapping(value = "/findReportDataList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取报告列表数据", ignoreLogFields = "Authorization")
    public String findReportDataList(@RequestBody Report report, @RequestHeader("Authorization") String token) {
        return Response.ok(productCenterClient.findReportDataList(report, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "生成报告", notes = "生成报告")
    @PostMapping(value = "/generateReport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "生成报告", ignoreLogFields = {"Authorization"})
    public String generateReport(@RequestBody Report report,
                                 @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        report.setUpdateUser(userId);
        ResponseCheckUtils.checkResponse(productCenterClient.generateReport(report,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }

    @ApiOperation(value = "获取模版配置模块信息", notes = "获取模版配置模块信息")
    @GetMapping(value = "/getTemplateConfigModel", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取模版配置模块信息", ignoreLogFields = "Authorization")
    public String getTemplateConfigModel(@ModelAttribute BusTemplateConfig busTemplateConfig,
                                         @RequestHeader("Authorization") String token) {
        if (null == busTemplateConfig.getConfigType()) {
            busTemplateConfig.setConfigType(FileType.TRANSACTION);
        }
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTemplateConfigModel(busTemplateConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "获取模版配置列表", notes = "获取模版配置模块信息")
    @GetMapping(value = "/getTemplateConfigList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取模版配置列表", ignoreLogFields = "Authorization")
    public String getTemplateConfigList(@ModelAttribute BusTemplateConfig busTemplateConfig,
                                        @RequestHeader("Authorization") String token) {
        if (null == busTemplateConfig.getConfigType()) {
            busTemplateConfig.setConfigType(FileType.TRANSACTION);
        }
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getTemplateConfigList(busTemplateConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "新增业务模版配置", notes = "新增业务模版配置")
    @PostMapping(value = "/newBusTemplateConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "新增业务模版配置", ignoreLogFields = {"Authorization"}, requiredParams = {"configType", "configName", "configContent", "configSecType", "configContent"})
    public String newBusTemplateConfig(@RequestBody BusTemplateConfig busTemplateConfig,
                                       @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        busTemplateConfig.setUpdateUser(userId);
        ResponseCheckUtils.checkResponse(productCenterClient.newBusTemplateConfig(busTemplateConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }


    @ApiOperation(value = "修改业务模版配置", notes = "修改业务模版配置")
    @PostMapping(value = "/updateBusTemplateConfig", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "修改业务模版配置", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "configType", "configName", "configContent", "configSecType", "configContent"})
    public String updateBusTemplateConfig(@RequestBody BusTemplateConfig busTemplateConfig,
                                          @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        busTemplateConfig.setUpdateUser(userId);
        ResponseCheckUtils.checkResponse(productCenterClient.updateBusTemplateConfig(busTemplateConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }


    @ApiOperation(value = "模版下载")
    @GetMapping(value = "/downloadTemplate")
    @WsOperation(value = "模版下载", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String downloadTemplate(@Context HttpServletResponse response,
                                   @ModelAttribute BusTemplateConfig busTemplateConfig,
                                   @RequestHeader("Authorization") String token) {
        busTemplateConfig.setUpdateUser(WsContext.getContext().getUserId());
        BusTemplateConfig result = ResponseCheckUtils.getBeanFromResponse(productCenterClient.getBusTemplateConfigById(busTemplateConfig,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()), BusTemplateConfig.class);
        if (null != result) {
            String templateFilePath = localFileTemplatePath + result.getTemplateFileName();
            if (result.getTemplateFileName().contains("_A7A_") || result.getTemplateFileName().contains("_A8A_")) {
                String downloadFileUrl = templateFilePath.split(OutputFileType.WORD.getDesc())[0] + "_Download" + OutputFileType.WORD.getDesc();
                String attachmentFileUrl = localFileTemplatePath + "word/PS_Restructure.docx";
                WordUtils.mergeWordFiles(templateFilePath, attachmentFileUrl, downloadFileUrl);
                templateFilePath = downloadFileUrl;
            }
            File file = new File(templateFilePath);

            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ResponseCheckUtils.getHeaderContentDisposition(result.getTemplateFileName()));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            int bytesRead;
            byte[] buffer = new byte[1024];
            try (InputStream inputStream = new FileInputStream(file)) {
                response.setHeader("Content-Length", String.valueOf(inputStream.available()));
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
                return Response.ok().build();
            } catch (IOException e1) {
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "");
                return Response.error(ErrorCodes.DataDeal.IO_ERROR).build();
            } catch (Exception e) {
                e.printStackTrace();
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "");
                return Response.error(ErrorCodes.UNKNOWN_ERROR).build();
            }
        }
        return Response.error(ErrorCodes.DataDeal.OBJECT_NOT_EXIST).build();
    }


    @ApiOperation(value = "获取报告列表-数据控制", notes = "获取报告列表-数据控制")
    @PostMapping(value = "/findReportDataListForUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取报告列表-数据控制", ignoreLogFields = "Authorization")
    public String findReportDataListForUser(@RequestBody Report report, @RequestHeader("Authorization") String token) {
        report.setOpUserId(WsContext.getContext().getUserId());
        report.setRequestUserType(RequestUserType.CUSTOMER);
        return Response.ok(productCenterClient.findReportDataList(report, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).build();
    }

    @ApiOperation(value = "生成报告-数据控制", notes = "生成报告-数据控制")
    @PostMapping(value = "/generateReportForUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "生成报告-数据控制", ignoreLogFields = {"Authorization"})
    public String generateReportForUser(@RequestBody Report report,
                                        @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        report.setOpUserId(WsContext.getContext().getUserId());
        report.setUpdateUser(userId);
        report.setRequestUserType(RequestUserType.CUSTOMER);
        ResponseCheckUtils.checkResponse(productCenterClient.generateReport(report,
                ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }


}
