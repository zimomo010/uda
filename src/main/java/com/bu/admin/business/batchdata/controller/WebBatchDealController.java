package com.bu.admin.business.batchdata.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.fileupload.service.FileUploadService;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.feign.bo.batchdata.BatchData;
import com.bu.admin.feign.bo.product.ProductSync;
import com.bu.admin.feign.enumtype.common.BatchDealBusTypes;
import com.bu.admin.feign.enumtype.common.FileType;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName WebConfigController
 * @Description 配置管理
 * @Author ghostWu
 * @Date 2019-07-09 10:09
 * @Version 1.0
 */
@Api
@RestController
@RequestMapping("/web/batchDeal")
@WsOperation("M002_数据导入管理")
public class WebBatchDealController {


    @Value("${tcloud.localFile.store.path.sync}")
    private String syncFilePath;
    @Resource
    private ProductCenterClient productCenterClient;
    @Resource
    private FileUploadService fileUploadService;


    @ApiOperation(value = "获取上传列表", notes = "获取上传列表")
    @GetMapping(value = "/getBatchDealList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "M002_01_获取上传列表", ignoreLogFields = "Authorization")
    public String getBatchDealList(@ModelAttribute BatchData batchData, @RequestHeader("Authorization") String token) {
        batchData.setUpdateUser(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.getBatchDealList(batchData, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }

    /**
     * 新增文件上传记录
     */
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=multipart/form-data")
    @WsOperation(value = "M002_02_文件上传", ignoreLogFields = "Authorization")
    public String uploadFile(@RequestParam(value = "file") MultipartFile file,
                             @RequestParam(value = "fileType") FileType fileType,
                             @RequestParam(value = "dealBusType") BatchDealBusTypes dealBusType,
                             @RequestHeader("Authorization") String token) {
        String fileUrl = fileUploadService.newNasFile(file, fileType, WsContext.getContext().getUserId());
        BatchData batchData = new BatchData();
        batchData.setFileUrl(fileUrl);
        batchData.setDealBusType(dealBusType);
        batchData.setUpdateUser(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.checkFile(batchData,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "警告确认提交", notes = "警告确认提交")
    @PostMapping(value = "/newBatchDataWithWarning", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "M002_03_警告确认提交", requiredParams = {"fileUrl"}, ignoreLogFields = {"Authorization"})
    public String newBatchDataWithWarning(@RequestBody BatchData batchData, @RequestHeader("Authorization") String token) {
        batchData.setUpdateUser(WsContext.getContext().getUserId());
        batchData.setBusId("Warning");
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.newBatchDataWithWarning(batchData,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "撤销导入数据", notes = "撤销导入数据")
    @PostMapping(value = "/rollBackBatchData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "M002_04_撤销导入数据", requiredParams = {"batchNo", "id"}, ignoreLogFields = {"Authorization"})
    public String rollBackBatchData(@RequestBody BatchData batchData, @RequestHeader("Authorization") String token) {
        batchData.setUpdateUser(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.rollBackBatchData(batchData,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "数据处理", notes = "数据处理")
    @PostMapping(value = "/batchDataDeal", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "M002_05_数据处理", requiredParams = {"fileUrl", "id", "dealBusType"}, ignoreLogFields = {"Authorization"})
    public String batchDataDeal(@RequestBody BatchData batchData, @RequestHeader("Authorization") String token) {
        batchData.setUpdateUser(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.dealBatchData(batchData,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "原始文件下载")
    @GetMapping(value = "/downloadOriginFile")
    @WsOperation(value = "M002_06_原始文件下载", ignoreLogFields = {"Authorization"}, requiredParams = {"id", "fileUrl"})
    public String generateFileFromTrans(@Context HttpServletResponse response,
                                        @ModelAttribute BatchData batchData,
                                        @RequestHeader("Authorization") String token) {
        File file = new File(batchData.getFileUrl());
        String[] fileNameStr = batchData.getFileUrl().split("\\/");
        int bytesRead;
        byte[] buffer = new byte[1024];
        try (InputStream inputStream = new FileInputStream(file)) {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ResponseCheckUtils.getHeaderContentDisposition(fileNameStr[fileNameStr.length - 1]));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
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


    /**
     * 新增文件上传记录
     */
    @ApiOperation(value = "同步数据文件上传", notes = "同步数据文件上传")
    @PostMapping(value = "/syncDataUploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=multipart/form-data")
    @WsOperation(value = "M002_07_同步数据文件上传", ignoreLogFields = "Authorization")
    public String syncDataUploadFile(@RequestParam(value = "file") MultipartFile file,
                                     @RequestParam(value = "fileType") FileType fileType,
                                     @RequestHeader("Authorization") String token) {
        String fileUrl = fileUploadService.newNasFile(file, syncFilePath, convertSyncFileName(fileType));

        ProductSync productSync = new ProductSync();
        productSync.setFileType(fileType);
        productSync.setFileUrl(fileUrl);
        return Response.ok(ResponseCheckUtils.checkResponse(productCenterClient.dealSyncDate(productSync,
                ServerTokenUtils.getApplicationName(),
                ServerTokenUtils.getServerToken()))).build();
    }

    @ApiOperation(value = "EQT同步文件下载")
    @GetMapping(value = "/downloadEQTFile")
    @WsOperation(value = "M002_08_EQT同步文件下载", ignoreLogFields = {"Authorization"}, requiredParams = {"fileType"})
    public String downloadEQTFile(@Context HttpServletResponse response,
                                  @RequestParam(value = "fileType") FileType fileType,
                                  @RequestHeader("Authorization") String token) {
        File file = new File(syncFilePath + convertSyncFileName(fileType));
        int bytesRead;
        byte[] buffer = new byte[1024];
        try (InputStream inputStream = new FileInputStream(file)) {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, ResponseCheckUtils.getHeaderContentDisposition(convertSyncFileName(fileType)));
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
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

    //----------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------
    private String convertSyncFileName(FileType fileType) {
        return fileType.name().toLowerCase() + ".csv";
    }


}
