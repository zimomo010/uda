package com.bu.admin.feign.bo.system;



import com.bu.admin.extend.bo.QueryBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 配置信息实体
 *
 * @author ghostWu
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Config extends QueryBase {

    private String configId;
    private String configName;
    private String configContent;
    private Boolean valid;
    private String userId;
    private Boolean userLike;
    private Boolean userFavorite;
    private String region;

}
