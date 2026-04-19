package com.bu.admin.feign.bo.user;

import com.bu.admin.feign.enumtype.product.EventBusType;
import com.bu.admin.feign.enumtype.user.UserEventType;
import lombok.Data;

import java.io.Serializable;

/**
 * user event po
 *
 * @author ghostWu
 */
@Data
public class UserEvent implements Serializable {


    private String id;
    private String userId;
    private EventBusType busType;
    private String busId;                    //内容id
    private UserEventType eventType;
    private Boolean hisData;                //历史数据

}
