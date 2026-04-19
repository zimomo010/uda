package com.bu.admin.extend.bo;

import com.bu.admin.utils.adapter.DateAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Base
 * @Description Bo顶级父类
 * @Author ghostWu
 * @Date 2019-07-10 17:03
 * @Version 1.0
 */
@ApiModel("顶级Bo对象")
@Data
public class BaseBo {

    @ApiModelProperty(name = "查询页",notes = "列表查询时传",value = "查询页码,列表查询时传")
    private int pageNum = 1;

    @ApiModelProperty(name = "每页条数",notes = "列表查询时传",value = "每页条数,列表查询时传")
    private int pageSize = 10;

    private String departCode;

    private String rootDepartCode;

    private String createUser;

    @JsonAdapter(DateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonAdapter(DateAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    private String updateUser;

    private String opUserId;

    private String traceId;

    /**
     * Excel数据处理错误列
     */
    private String transErrorParam;

    /**
     * Excel数据处理错误信息
     */
    private String transErrorMessage;

}
