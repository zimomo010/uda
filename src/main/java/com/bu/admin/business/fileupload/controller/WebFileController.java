package com.bu.admin.business.fileupload.controller;

import com.bu.admin.api.Response;
import com.bu.admin.api.WsOperation;
import com.bu.admin.business.fileupload.enums.FileRequestType;
import com.bu.admin.business.fileupload.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Api
@RestController
@RequestMapping("/web/file")
@WsOperation("文件存储操作接口")
public class WebFileController {

    @Resource
    private FileUploadService fileUploadService;

    /**
     * @param folderName
     * @param token
     * @return
     */
    @ApiOperation(value = "获取文件夹列表", notes = "获取文件夹列表")
    @GetMapping(value = "/getFolder", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取文件夹列表", ignoreLogFields = "Authorization")
    public String getFolder(@RequestParam("folderName") String folderName, @RequestHeader("Authorization") String token) {
        List<String> fileList = fileUploadService.getFolders(folderName);
        return Response.ok(fileList).build();
    }

    /**
     * @param folderName
     * @param token
     * @return
     *
     **/
    @ApiOperation(value = "获取文件列表", notes = "获取文件列表")
    @GetMapping(value = "/getFileList", produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "获取文件列表", ignoreLogFields = "Authorization")
    public String getFileList(@RequestParam("folderName") String folderName, @RequestHeader("Authorization") String token) {
        List<String> fileList = fileUploadService.getFileList(folderName);
        return Response.ok(fileList).build();
    }

    /**
     * @param token
     * @param files
     * @param targetFolderName
     * @return
     */
    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping(value = "/uploadFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @WsOperation(value = "文件上传", ignoreLogFields = "Authorization")
    public String uploadFiles(@RequestPart("file") MultipartFile[] files,
                              @RequestParam("targetFolderName") String targetFolderName,
                              @RequestHeader("Authorization") String token) {
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            fileUploadService.fileToCos(file, targetFolderName, fileName, FileRequestType.PRIVATE);
        }
        return Response.ok().build();
    }

    /**
     * 创建文件夹
     *
     * @param folderName
     * @param token
     * @return
     */
    @ApiOperation(value = "创建文件夹", notes = "创建文件夹")
    @PostMapping(value = "/createFolder", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "创建文件夹", ignoreLogFields = "Authorization")
    public String createFolder(@RequestParam("folderName") String folderName, @RequestHeader("Authorization") String token) {
        fileUploadService.newFolder(folderName);
        return Response.ok().build();
    }

    /**
     * 删除文件夹
     *
     * @param folderName
     * @param token
     * @return
     */
    @ApiOperation(value = "删除文件夹", notes = "删除文件夹")
    @PostMapping(value = "/deleteFolder", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "删除文件夹", ignoreLogFields = "Authorization")
    public String deleteFolder(@RequestParam("folderName") String folderName, @RequestHeader("Authorization") String token) {
        fileUploadService.deleteObject(folderName);
        return Response.ok().build();
    }


    @ApiOperation(value = "刷新cdn", notes = "刷新cdn")
    @PostMapping(value = "/cdnRefresh", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @WsOperation(value = "刷新cdn", ignoreLogFields = "Authorization")
    public String cdnRefresh(@RequestParam("filePath") String filePath, @RequestHeader("Authorization") String token) {
        fileUploadService.refreshCDN(filePath);
        return Response.ok().build();
    }


}
