package com.bu.admin.business.uda.po;

import com.bu.admin.business.uda.enums.AreaCodes;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Data
@Table(name="u_video_info")
public class UdaVideoEntity implements Serializable {

    public UdaVideoEntity() {
    }

    public UdaVideoEntity(AreaCodes areaCode, Long id) {
        this.areaCode = areaCode;
        this.views = id.intValue();
    }

    public UdaVideoEntity(String categoryId, Long id) {
        this.categoryId = categoryId;
        this.views = id.intValue();
    }

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
    private BigDecimal videoScore;

    @Column(name = "AREA_CODE")
    @Enumerated(EnumType.STRING)
    private AreaCodes areaCode;
}
