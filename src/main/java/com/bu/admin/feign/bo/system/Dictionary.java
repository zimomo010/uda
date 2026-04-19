package com.bu.admin.feign.bo.system;


import com.bu.admin.extend.bo.QueryBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据字典业务值对象
 *
 * @author luffy
 * @date 15/5/4
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Dictionary extends QueryBase {

    private String id;
    private String dictionaryCode;
    private String dictionaryName;
    private String typeCode;
    private String typeName;
    private String parentDictionaryCode;
    private String parentDictionaryName;
    private String parentTypeCode;
    private String parentTypeName;
    private Integer sortNumber;

}
