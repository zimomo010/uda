package com.bu.admin.business.uda.bo;

import com.bu.admin.business.uda.enums.AreaCodes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Data
public class UdaVideo implements Serializable {

    @Id
    @Column(name = "ID")
    private String videoId;
    private String trendingDate;
    private String title;
    private String channelTitle;
    private Integer views;
    private Integer likes;
    private Integer dislikes;
    private String publishTime;
    private String categoryId;
    private String tags;
    private Integer comments;
    private String channelId;
    private String description;
    private String sortParam;
    private BigDecimal videoScore;
    private AreaCodes areaCode;
}
