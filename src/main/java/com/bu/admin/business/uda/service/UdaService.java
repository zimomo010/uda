package com.bu.admin.business.uda.service;


import com.bu.admin.business.uda.bo.UdaVideo;
import com.bu.admin.business.uda.enums.AreaCodes;
import com.bu.admin.business.uda.po.UdaVideoEntity;
import com.bu.admin.business.uda.po.UdaVideoStaticEntity;

import java.util.List;

public interface UdaService {

    void syncUdaData();

    void videoTagCount();

    UdaVideoStaticEntity getVideoTagTop(AreaCodes areaCode, String categoryId);

    List<UdaVideoEntity> getCountData();

    List<UdaVideoEntity> getCategoryCountList(AreaCodes areaCode);

    List<UdaVideoEntity> getTopVideoList(AreaCodes areaCode,String sortParam);

    UdaVideo userVideoCal(UdaVideo userVideo);
}
