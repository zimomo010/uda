package com.bu.admin.business.fileupload.utils.tcloud;


import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.tencentcloudapi.cdn.v20180606.CdnClient;
import com.tencentcloudapi.cdn.v20180606.models.PurgeUrlsCacheRequest;
import com.tencentcloudapi.cdn.v20180606.models.PurgeUrlsCacheResponse;
import com.tencentcloudapi.common.AbstractModel;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Component
public class TcloudUtil {
    protected static final Logger logger = LoggerFactory.getLogger(TcloudUtil.class);

    /**
     * 上传文件
     *
     * @return //绝对路径和相对路径都OK
     */
    public static void uploadFileInput(InputStream inputStream, String fileName, String fileHost, TcloudConfig tcloudCosConfig) {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(tcloudCosConfig.getRegionId()));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(inputStream.available());
            PutObjectRequest putObjectRequest = new PutObjectRequest(tcloudCosConfig.getBucketName(), fileHost + fileName, inputStream, metadata);

            // 设置存储类型, 默认是标准(Standard), 低频(standard_ia),一般为标准的
            putObjectRequest.setStorageClass(StorageClass.Standard);


            cosClient.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
        } catch (CosServiceException e) {
            logger.error("get cos service error,message is :{}", e.getMessage());
        } catch (CosClientException e) {
            logger.error("get cos client error,message is :", e);
        } catch (IOException e) {
            logger.error("get IOException error,message is :", e);
        } finally {
            // 关闭客户端
            cosClient.shutdown();
        }

    }

    /**
     * 上传文件
     *
     * @return //绝对路径和相对路径都OK
     */
    public static void newFolder(String folderName, TcloudConfig tcloudCosConfig) {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(tcloudCosConfig.getRegionId()));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        try (InputStream inputStream = new ByteArrayInputStream(new byte[0])) {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(0);
            PutObjectRequest putObjectRequest = new PutObjectRequest(tcloudCosConfig.getBucketName(), folderName, inputStream, metadata);
            cosClient.putObject(putObjectRequest);
            // putobjectResult会返回文件的etag
        } catch (CosServiceException e) {
            logger.error("get cos service error,message is :{}", e.getMessage());
        } catch (CosClientException e) {
            logger.error("get cos client error,message is :{}", e.getMessage());
        } catch (IOException e) {
            logger.error("get IOException error,message is :{}", e.getMessage());
        } finally {
            // 关闭客户端
            cosClient.shutdown();
        }

    }

    /**
     * 下载文件
     *
     * @param bucketName
     * @param key
     * @return
     */
    public String downLoadFile(String bucketName, String key, TcloudConfig tcloudCosConfig) {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(tcloudCosConfig.getRegionId()));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);

        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, null);
        cosClient.shutdown();
        return downObjectMeta.getETag();
    }

    /**
     * 删除文件
     *
     * @param key
     */
    public static void deleteObject(String key, TcloudConfig tcloudCosConfig) {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(tcloudCosConfig.getRegionId()));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        try {
            cosClient.deleteObject(tcloudCosConfig.getBucketName(), key);
        } catch (CosClientException e) {
            e.printStackTrace();
        } finally {
            cosClient.shutdown();
        }

    }

    /**
     * 创建桶
     *
     * @param bucketName
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public Bucket createBucket(String bucketName, TcloudConfig tcloudCosConfig) throws CosClientException, CosServiceException {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(tcloudCosConfig.getRegionId()));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        Bucket bucket = null;
        try {
            bucket = cosClient.createBucket(bucketName);
        } catch (CosClientException e) {
            e.printStackTrace();
        } finally {
            cosClient.shutdown();
        }
        return bucket;
    }


    /**
     * 删除桶
     *
     * @param bucketName
     * @throws CosClientException
     * @throws CosServiceException
     */
    public void deleteBucket(String bucketName, TcloudConfig tcloudCosConfig) throws CosClientException, CosServiceException {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(tcloudCosConfig.getRegionId()));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);
        try {
            cosClient.deleteBucket(bucketName);
        } catch (CosClientException e) {
            e.printStackTrace();
        } finally {
            cosClient.shutdown();
        }
    }


    /**
     * 判断桶是否存在
     *
     * @param bucketName
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public boolean doesBucketExist(String bucketName, TcloudConfig tcloudCosConfig) throws CosClientException, CosServiceException {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(tcloudCosConfig.getRegionId()));
        // 3 生成cos客户端
        COSClient cc = new COSClient(cred, clientConfig);
        return cc.doesBucketExist(bucketName);
    }


    /**
     * 查看桶文件
     *
     * @param prefix
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public static ObjectListing listObjects(String prefix, TcloudConfig tcloudCosConfig) throws CosClientException, CosServiceException {
        COSCredentials cred = new BasicCOSCredentials(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
        ClientConfig clientConfig = new ClientConfig(new Region(tcloudCosConfig.getRegionId()));
        COSClient cosClient = new COSClient(cred, clientConfig);
        try {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
            listObjectsRequest.setBucketName(tcloudCosConfig.getBucketName());
            // 设置 list 的 prefix, 表示 list 出来的文件 key 都是以这个 prefix 开始
            listObjectsRequest.setPrefix(prefix);
            // 设置 delimiter 为/, 即获取的是直接成员，不包含目录下的递归子成员
            listObjectsRequest.setDelimiter("/");
            listObjectsRequest.setMarker("");
            listObjectsRequest.setMaxKeys(1000);

            return cosClient.listObjects(listObjectsRequest);
        } finally {
            cosClient.shutdown();
        }
    }

    /**
     * @param fileHost 地址文件
     * @param date     过期时间
     * @return
     * @throws Exception
     */
    public static String getFileUrl(String fileHost, Date date, TcloudConfig tcloudCosConfig) {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(tcloudCosConfig.getRegionId()));
        // 3 生成cos客户端
        COSClient cc = new COSClient(cred, clientConfig);
        try {
            URL url = cc.generatePresignedUrl(tcloudCosConfig.getBucketName(), fileHost, date);
            return url.toString();
        } finally {
            cc.shutdown();
        }
    }

    /**
     * 查询一个 Bucket 所在的 Region。
     *
     * @param bucketName
     * @return
     * @throws CosClientException
     * @throws CosServiceException
     */
    public String getBucketLocation(String bucketName, TcloudConfig tcloudCosConfig) throws CosClientException, CosServiceException {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置region, https(默认http), 超时, 代理等set方法, 使用可参见源码或者接口文档FAQ中说明
        ClientConfig clientConfig = new ClientConfig(new Region(tcloudCosConfig.getRegionId()));
        // 3 生成cos客户端
        COSClient cc = new COSClient(cred, clientConfig);
        // bucket 的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        return cc.getBucketLocation(bucketName);
    }

    public static String refreshCDN(String[] urls, TcloudConfig tcloudCosConfig){
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential(tcloudCosConfig.getSecretId(), tcloudCosConfig.getSecretKey());
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("cdn.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            CdnClient client = new CdnClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            PurgeUrlsCacheRequest req = new PurgeUrlsCacheRequest();
            req.setUrls(urls);
            // 返回的resp是一个PurgeUrlsCacheResponse的实例，与请求对象对应
            PurgeUrlsCacheResponse resp = client.PurgeUrlsCache(req);
            // 输出json格式的字符串回包
            return AbstractModel.toJsonString(resp);
        } catch (TencentCloudSDKException e) {
            logger.error("refresh error, error message is [{}]", e.getMessage());
            return null;
        }
    }




}
