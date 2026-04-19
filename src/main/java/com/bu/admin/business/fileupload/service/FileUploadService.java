package com.bu.admin.business.fileupload.service;


import com.bu.admin.business.fileupload.bo.LocalFile;
import com.bu.admin.business.fileupload.enums.FileRequestType;
import com.bu.admin.feign.enumtype.common.FileType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传Service接口
 *
 * @author ghostWu
 */
public interface FileUploadService {

    /**
     * 根据业务类型保存文件
     *
     * @param file
     * @return
     */
    String fileUploadWithBusType(MultipartFile file, FileType busType, String busId, FileRequestType requestType);

    /**
     * 指定文件夹保存文件
     *
     * @param file
     * @return
     */
    String fileUploadWithFolderName(MultipartFile file, String folderName, String busId,FileRequestType requestType);

    /**
     * 指定文件夹及文件名保存文件
     *
     * @param file
     * @return
     */
    String fileToCos(MultipartFile file, String folderName, String fileName, FileRequestType requestType);

    /**
     * 保存NAS文件到cos
     *
     * @param localFile
     * @return
     */
    String nasFileToCos(LocalFile localFile);

    /**
     * 保存本地NAS文件
     *
     * @param file
     * @return
     */
    String newNasFile(MultipartFile file, FileType fileType, String userId);

    /**
     * 创建目录
     *
     * @param folderName
     * @return
     */
    void newFolder(String folderName);

    /**
     * 获取目录列表
     *
     * @param folderName
     * @return
     */
    List<String> getFolders(String folderName);

    /**
     * 获取文件列表
     *
     * @param folderName
     * @return
     */
    List<String> getFileList(String folderName);

    /**
     * 删除对象
     *
     * @param objectName
     * @return
     */
    void deleteObject(String objectName);

    /**
     * 获取文件路径
     *
     * @param objectName
     * @param userId
     * @return
     */
    String getObjectUrl(String objectName, String userId);

    /**
     * 刷新CDN
     *
     * @param url
     * @return
     */
    String refreshCDN(String url);

    String newNasFile(MultipartFile file,String fileName);

    String newNasFile(MultipartFile file, String filePath, String fileName);
}
