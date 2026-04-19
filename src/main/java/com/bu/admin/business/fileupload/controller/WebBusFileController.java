package com.bu.admin.business.fileupload.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsContext;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.fileupload.service.FileUploadService;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.feign.bo.file.BusFile;
import com.bu.admin.feign.enumtype.common.FileType;
import com.bu.admin.feign.service.ProductCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/web/busFile")
@WsOperation("业务文件操作接口")
public class WebBusFileController {

    @Value("${tcloud.localFile.store.path.upload}")
    private String uploadFilePath;
    private static final Logger logger = LoggerFactory.getLogger(WebBusFileController.class);
    @Resource
    private ProductCenterClient orderCenterClient;
    @Resource
    private FileUploadService fileUploadService;

    /**
     * @param busFile
     * @param token
     * @return
     */
    @ApiOperation(value = "获取业务文件列表", notes = "获取业务文件列表")
    @GetMapping(value = "/getBusFileList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取业务文件列表", ignoreLogFields = "Authorization")
    public String getBusFileList(@ModelAttribute BusFile busFile, @RequestHeader("Authorization") String token) {
        busFile.setUpdateUser(WsContext.getContext().getUserId());
        return Response.ok(ResponseCheckUtils.checkResponse(orderCenterClient.findBusFileList(
                busFile, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()))).build();
    }


    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping(value = "/uploadBusFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, headers = "content-type=multipart/form-data")
    @WsOperation(value = "文件上传", ignoreLogFields = "Authorization")
    public String uploadFiles(@RequestParam(value = "file") MultipartFile file,
                              @RequestParam(value = "busType") FileType busType,
                              @RequestParam(value = "busId") String busId,
                              @RequestParam(value = "busCode") String busCode,
                              @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        BusFile busFile = new BusFile();
        busFile.setBusCode(busCode);
        busFile.setBusId(busId);
        busFile.setCreateUser(userId);
        busFile.setUpdateUser(userId);
        busFile.setBusType(busType);
        String filePath = new StringBuilder().append(uploadFilePath).append(busType.name()).append("/").append(busId).append("/").toString();
        busFile.setFileUrl(fileUploadService.newNasFile(file, filePath, file.getOriginalFilename()));
        ResponseCheckUtils.checkResponse(orderCenterClient.newBusFile(busFile, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }


    /**
     * @param busFile
     * @param token
     * @return
     **/
    @ApiOperation(value = "业务文件记录新建", notes = "业务文件记录新建")
    @PostMapping(value = "/newBusFile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "业务文件记录新建", requiredParams = {"busType", "busId", "fileUrl", "busCode", "storeType"}, ignoreLogFields = {"Authorization"})
    public String newBusFile(@RequestBody BusFile busFile, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        busFile.setUpdateUser(userId);
        ResponseCheckUtils.checkResponse(orderCenterClient.newBusFile(busFile, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok().build();
    }


    /**
     * @param busFile
     * @param token
     * @return
     **/
    @ApiOperation(value = "删除业务文件", notes = "删除业务文件")
    @PostMapping(value = "/delBusFile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除业务文件", requiredParams = {"id", "fileUrl"}, ignoreLogFields = {"Authorization"})
    public String delBusFile(@RequestBody BusFile busFile, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        busFile.setUpdateUser(userId);
        ResponseCheckUtils.checkResponse(orderCenterClient.delBusFile(busFile, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        fileUploadService.deleteObject(busFile.getFileUrl());
        return Response.ok().build();
    }

    /**
     * @param busFile
     * @param token
     * @return
     */
    @ApiOperation(value = "获取业务文件详细", notes = "获取业务文件详细")
    @GetMapping(value = "/getBusFileUrl", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取业务文件详细", requiredParams = {"fileUrl"}, ignoreLogFields = "Authorization")
    public String getBusFileUrl(@ModelAttribute BusFile busFile, @RequestHeader("Authorization") String token) {
        String userId = WsContext.getContext().getUserId();
        busFile.setUpdateUser(userId);
        ResponseCheckUtils.checkResponse(orderCenterClient.checkBusFilePurview(busFile, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken()));
        return Response.ok(fileUploadService.getObjectUrl(busFile.getFileUrl(), userId)).build();
    }


    @ApiOperation(value = "报告下载")
    @GetMapping(value = "/downloadBusFile")
    @WsOperation(value = "报告下载", ignoreLogFields = {"Authorization"}, requiredParams = {"id"})
    public String downloadBusFile(@Context HttpServletResponse response,
                                  @ModelAttribute BusFile busFile,
                                  @RequestHeader("Authorization") String token) {
        busFile.setUpdateUser(WsContext.getContext().getUserId());
        JsonObject result = ResponseCheckUtils.checkResponse(orderCenterClient.checkBusFilePurview(busFile, ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken())).getAsJsonObject();
        File file = new File(result.get("fileUrl").getAsString());
        String[] fileNameStr = result.get("fileUrl").getAsString().split("\\/");
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
            logger.error(e.getMessage());
            return Response.error(ErrorCodes.UNKNOWN_ERROR).build();
        }
    }

}
