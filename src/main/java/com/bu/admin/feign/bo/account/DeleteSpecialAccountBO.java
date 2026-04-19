package com.bu.admin.feign.bo.account;

import lombok.Data;

/**
 * 删除专户
 *
 * @author liujiegang
 * @date 2024/7/11
 */
@Data
public class DeleteSpecialAccountBO {

    /**
     * id
     */
    private Long id;

    /**
     * 操作人ID
     */
    private String updateUser;
}
