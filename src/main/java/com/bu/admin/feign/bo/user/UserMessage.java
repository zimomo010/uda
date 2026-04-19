package com.bu.admin.feign.bo.user;

import com.bu.admin.extend.bo.QueryBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * user comment po
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserMessage extends QueryBase implements Serializable {


    private String id;
    private String userId;
    private String content;
    private String targetUrl;
    private Boolean readMark;
    private Date readTime;


}
