package com.bu.admin.feign.bo.user;


import com.bu.admin.feign.enumtype.product.EventBusType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user favorite po
 *
 * @author ghostWu
 */
@Data
public class UserFavorite implements Serializable {

    private String id;
    private String userId;
    private EventBusType favoriteType;
    private String busId;             //收藏id
    private Date createTime;
    private Boolean add;



}
