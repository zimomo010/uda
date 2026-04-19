package com.bu.admin.business.account.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 删除专户
 *
 * @author liujiegang
 * @date 2024/7/11
 */
@Data
public class WebDeleteSpecialAccountRequest {

    /**
     * id
     */
    @NotNull
    private Long id;
}
