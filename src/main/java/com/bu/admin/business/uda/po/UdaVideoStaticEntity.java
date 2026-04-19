package com.bu.admin.business.uda.po;

import com.bu.admin.business.uda.enums.AreaCodes;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Entity
@Data
@Table(name="u_video_static")
public class UdaVideoStaticEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "AREA_CODE")
    @Enumerated(EnumType.STRING)
    private AreaCodes areaCode;
    private String categoryId;

    private String tagStatic;
    private String publishStatic;
    private String trendStatic;
}
