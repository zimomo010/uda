package com.bu.admin.feign.bo.user;


import com.bu.admin.extend.bo.QueryBase;
import com.bu.admin.feign.enumtype.product.EventBusType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * user comment po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserComment extends QueryBase implements Serializable {

    private String id;
    private String userId;
    private String userAlias;
    private String content;
    private EventBusType busType;
    private String busId;                    //内容id
    private String targetCommentId;
    private Integer childComments;
    private Boolean status;
    private String targetCommentIdNull;

}
