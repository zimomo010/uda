package com.bu.admin.business.uda.repository;

import com.bu.admin.business.uda.enums.AreaCodes;
import com.bu.admin.business.uda.po.UdaVideoStaticEntity;
import com.bu.admin.extend.repository.BasicRepository;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UdaStaticRepository extends JpaRepository<UdaVideoStaticEntity, Long>, BasicRepository<UdaVideoStaticEntity> {

    UdaVideoStaticEntity findAllByAreaCodeAndCategoryId(AreaCodes areaCode, String categoryId);
}
