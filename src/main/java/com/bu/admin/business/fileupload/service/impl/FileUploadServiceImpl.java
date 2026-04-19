package com.bu.admin.business.fileupload.service.impl;

import com.bu.admin.business.cache.BusCacheRegionConstant;
import com.bu.admin.business.fileupload.bo.LocalFile;
import com.bu.admin.business.fileupload.enums.FileRequestType;
import com.bu.admin.business.fileupload.service.FileUploadService;
import com.bu.admin.business.fileupload.utils.tcloud.TcloudConfig;
import com.bu.admin.business.fileupload.utils.tcloud.TcloudUtil;
import com.bu.admin.cache.CacheService;
import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.feign.enumtype.common.FileType;
import com.bu.admin.feign.service.UserCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.utils.CommonUtils;
import com.bu.admin.utils.DateConverterUtils;
import com.bu.admin.utils.JSONUtils;
import com.bu.admin.utils.sequence.SequenceGenerator;
import com.google.gson.JsonObject;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.ObjectListing;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * 商品管理业务层Service
 *
 * @author ghostWu
 */
@Service
public class FileUploadServiceImpl  implements FileUploadService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${file.upload.type}")
    private String fileUploadType;
    @Value("${file.cos.domain}")
    private String fileCosDomain;
    @Value("${file.cdn.domain}")
    private String fileCdnDomain;
    @Value("${tcloud.localFile.store.path.temporary}")
    private String localFilePath;
    @Value("${tcloud.localFile.store.path.upload}")
    private String uploadPath;
    @Resource
    private UserCenterClient userCenterClient;
    private static final String COS_FILE_PRE = "busfile/";

    @Override
    public String fileUploadWithBusType(MultipartFile file, FileType busType, String busId, FileRequestType requestType) {
        logger.info("---->>bus file upload start bus type is :[{}] bus id is :[{}]<<----", busType, busId);
        String fileName = getFileName(file.getOriginalFilename());
        String folderName = COS_FILE_PRE + busType.name() + "/" + busId + "/"
                + DateConverterUtils.formatDateToString(new Date(), DateConverterUtils.FormatterType.FORMAT5) + "/";
        return this.fileToCos(file, folderName, fileName, requestType);
    }

    @Override
    public String fileUploadWithFolderName(MultipartFile file, String folderName, String busId, FileRequestType requestType) {
        logger.info("---->>bus file upload start bus type is :[{}] bus id is :[{}]<<----", folderName, busId);
        String fileName = getFileName(file.getOriginalFilename());
        folderName += "/" + busId + "/" + DateConverterUtils.formatDateToString(new Date(), DateConverterUtils.FormatterType.FORMAT5) + "/";
        return this.fileToCos(file, folderName, fileName, requestType);
    }

    @Override
    public String fileToCos(MultipartFile file, String folderName, String fileName, FileRequestType requestType) {
        try {
            logger.info("---->>file upload start folder name is [{}] get file name is :[{}]<<----", folderName, fileName);
            InputStream is = file.getInputStream();
            TcloudConfig tcloudCosConfig = getTcloudCosConfig();
            TcloudUtil.uploadFileInput(is, fileName, folderName, tcloudCosConfig);
            if (requestType == FileRequestType.PRIVATE) {
                return folderName + fileName;
            } else {
                return fileCdnDomain + folderName + fileName;
            }
        } catch (IOException e) {
            throw new BasicException(ErrorCodes.DataDeal.IO_ERROR);
        }
    }

    @Override
    public String nasFileToCos(LocalFile localFile) {
        try {
            logger.info("---->>local file upload start folder name is [{}] get file name is :[{}]<<----",
                    localFile.getTargetFolderName(), localFile.getFileName());
            if (StringUtils.isBlank(localFile.getLocalUrl())) {
                localFile.setLocalUrl(localFilePath + localFile.getFileType().name() + "/" + localFile.getFileName());
            }
            InputStream is = new FileInputStream(localFile.getLocalUrl());
            TcloudConfig tcloudCosConfig = getTcloudCosConfig();
            TcloudUtil.uploadFileInput(is, localFile.getFileName(), localFile.getTargetFolderName(), tcloudCosConfig);
            is.close();
            logger.info("---->>delete local file, file url is :[{}]<<----", localFile.getLocalUrl());
            Files.delete(Paths.get(localFile.getLocalUrl()));
            return fileCdnDomain + localFile.getTargetFolderName() + localFile.getFileName();
        } catch (IOException e) {
            throw new BasicException(ErrorCodes.DataDeal.IO_ERROR);
        }
    }

    @Override
    public String newNasFile(MultipartFile file, FileType fileType, String userId) {
        try {
            logger.info("---->>new local file type is [{}], userId :[{}]<<----",
                    fileType, userId);

            String originalFileName = file.getOriginalFilename();
            String localFolderName = uploadPath + DateConverterUtils.formatDateToString(new Date(), DateConverterUtils.FormatterType.FORMAT5) + "/";
            CommonUtils.checkPathAndCreate(localFolderName);
            String fileName = UUID.randomUUID() + "." + CommonUtils.getExtension(originalFileName);
            logger.info("new local file path is  [{}],file name is [{}]", localFolderName, fileName);
            File nasFile = new File(localFolderName + fileName);
            file.transferTo(nasFile);
            return localFolderName + fileName;
        } catch (IOException e) {
            throw new BasicException(ErrorCodes.DataDeal.IO_ERROR);
        }
    }
    @Override
    public String newNasFile(MultipartFile file,String fileName) {
        try {
            logger.info("---->>new nas file  fileName :[{}]<<----", fileName);
            String localFolderName = uploadPath + DateConverterUtils.formatDateToString(new Date(), DateConverterUtils.FormatterType.FORMAT5) + "/";
            CommonUtils.checkPathAndCreate(localFolderName);
            File nasFile = new File(localFolderName + fileName);
            file.transferTo(nasFile);
            return localFolderName + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BasicException(ErrorCodes.DataDeal.IO_ERROR);
        }
    }

    @Override
    public String newNasFile(MultipartFile file, String filePath, String fileName) {
        try {
            logger.info("---->>new nas file path is [{}], fileName :[{}]<<----", filePath,
                    fileName);
            CommonUtils.checkPathAndCreate(filePath);
            File nasFile = new File(filePath + fileName);
            file.transferTo(nasFile);
            return filePath + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new BasicException(ErrorCodes.DataDeal.IO_ERROR);
        }
    }

    @Override
    public void newFolder(String folderName) {
        logger.info("---->>new folder [{}] is create<<----", folderName);
        TcloudConfig tcloudCosConfig = getTcloudCosConfig();
        TcloudUtil.newFolder(folderName, tcloudCosConfig);
    }

    @Override
    public List<String> getFolders(String folderName) {
        TcloudConfig tcloudCosConfig = getTcloudCosConfig();
        ObjectListing objectListing = TcloudUtil.listObjects(folderName, tcloudCosConfig);
        // 获取下次 list 的 marker getNextMarker
        // 判断是否已经 list 完, 如果 list 结束, 则 isTruncated 为 false, 否则为 true
        List<String> objectSummaries = objectListing.getCommonPrefixes();
        logger.info("---->>folder [{}] get file numbers is :{}<<----", folderName, objectSummaries.size());
        return objectSummaries;
    }

    @Override
    public List<String> getFileList(String folderName) {
        TcloudConfig tcloudCosConfig = getTcloudCosConfig();
        ObjectListing objectListing = TcloudUtil.listObjects(folderName, tcloudCosConfig);
        // 获取下次 list 的 marker getNextMarker
        // 判断是否已经 list 完, 如果 list 结束, 则 isTruncated 为 false, 否则为 true
        List<COSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        logger.info("---->>folder [{}] get file numbers is :{}<<----", folderName, objectSummaries.size());
        List<String> objectStr = new ArrayList<>();
        for (COSObjectSummary cosObjectSummary : objectSummaries) {
            // get file path
            String key = cosObjectSummary.getKey();
            objectStr.add(key);
        }
        return objectStr;
    }

    @Override
    public void deleteObject(String objectName) {
        logger.info("---->>object [{}] delete start<<----", objectName);
        TcloudConfig tcloudCosConfig = getTcloudCosConfig();
        TcloudUtil.deleteObject(objectName, tcloudCosConfig);
    }

    @Override
    public String getObjectUrl(String objectName, String userId) {
        logger.info("---->>get object [{}] url start<<----", objectName);
        String fileUrl;
        TcloudConfig tcloudCosConfig = getTcloudCosConfig();
        if (CacheService.INST.exists(BusCacheRegionConstant.USER_COS_FILE_URL.name(), userId + objectName)) {
            fileUrl = CacheService.INST.getObject(BusCacheRegionConstant.USER_COS_FILE_URL.name(), userId + objectName, String.class);
            logger.info("---->>get object [{}] url from redis<<----", fileUrl);
            return fileUrl;
        }
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, 1);
        fileUrl = TcloudUtil.getFileUrl(objectName, now.getTime(), tcloudCosConfig);
        fileUrl = fileUrl.replace(fileCosDomain, "");
        logger.info("cos file url is :[{}]", fileUrl);
        CacheService.INST.put(BusCacheRegionConstant.USER_COS_FILE_URL.name(), userId + objectName, fileUrl, 24 * 60 * 60L);
        return fileUrl;
    }

    @Override
    public String refreshCDN(String url) {
        TcloudConfig tcloudCosConfig = getTcloudCosConfig();
        String[] urls = new String[]{fileCdnDomain + url};
        logger.info("---->>refresh CDN,domain is [{}], url is [{}], start <<----", fileCdnDomain, url);
        return TcloudUtil.refreshCDN(urls, tcloudCosConfig);
    }

    //----------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------
    private String getFileName(String fileName) {
        return DateConverterUtils.formatDateToString(new Date(), DateConverterUtils.FormatterType.FORMAT5) +
                String.format("%08d", SequenceGenerator.nextValue("FILENAME_SEQ")) + "." +
                CommonUtils.getExtension(fileName);
    }


    private TcloudConfig getTcloudCosConfig() {
        TcloudConfig tcloudCosConfig;
        tcloudCosConfig = JSONUtils.getJson(getConfig(fileUploadType).toString(), TcloudConfig.class);
        if (null == tcloudCosConfig) {
            logger.info("未查询到配置信息，使用默认信息");
            throw new BasicException(ErrorCodes.DataDeal.CONFIG_NOT_EXIST);
        }
        return tcloudCosConfig;
    }

    private JsonObject getConfig(String configName) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("configName", configName);
        String ucResponse = userCenterClient.getConfigDetail(JSONUtils.toJson(jsonObject), ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
        logger.info("uc response info is :{}", ucResponse);
        return ResponseCheckUtils.checkResponse(ucResponse).getAsJsonObject();
    }

}
