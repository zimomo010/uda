package com.bu.admin.business.fileupload.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.fileupload.bo.LocalFile;
import com.bu.admin.business.fileupload.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Api
@RestController
@RequestMapping("/server/file")
@WsOperation("文件操作后台接口")
public class ServerFileController {


    @Resource
    private FileUploadService fileUploadService;

    /**
     * 新增文件上传记录
     */
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping(value = "/uploadFiles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "文件上传", ignoreLogFields = "Authorization", requiredParams = {"localUrl","targetFolderName"},
            tokenNeeded = false, permissionNeeded = false, serverTokenNeeded = true)
    public String uploadFiles(@RequestBody LocalFile localFile,
                              @RequestHeader("MiServerName") String serverName,
                              @RequestHeader("Authorization") String serverToken) {
        fileUploadService.nasFileToCos(localFile);
        return Response.ok().build();
    }

    /**
     * 删除对象
     *
     * @param objectName
     * @return
     */
    @ApiOperation(value = "删除文件夹", notes = "删除文件夹")
    @PostMapping(value = "/deleteObject", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除文件夹", ignoreLogFields = "Authorization", requiredParams = {"objectName"},
            tokenNeeded = false, permissionNeeded = false, serverTokenNeeded = true)
    public String deleteObject(@RequestParam("objectName") String objectName,
                               @RequestHeader("MiServerName") String serverName,
                               @RequestHeader("Authorization") String serverToken) {
        fileUploadService.deleteObject(objectName);
        return Response.ok().build();
    }

}
