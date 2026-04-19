package com.bu.admin.feign.bo.account;

import lombok.Data;

/**
 * 导入专户
 *
 * @author liujiegang
 * @date 2025/1/26
 */
@Data
public class ImportSpecialAccountBO {

    /**
     * 导入的文件url
     */
    private String fileUrl;

    /**
     * 操作人ID
     */
    private String createUser;
}
