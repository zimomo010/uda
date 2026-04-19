package com.bu.admin.business.uda.repository;

import com.bu.admin.business.uda.enums.AreaCodes;
import com.bu.admin.business.uda.po.UdaVideoEntity;
import com.bu.admin.extend.repository.BasicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UdaRepository extends JpaRepository<UdaVideoEntity, String>, BasicRepository<UdaVideoEntity> {

    @Query("select new UdaVideoEntity(t.areaCode, count(t.videoId)) from UdaVideoEntity t group by t.areaCode")
    List<UdaVideoEntity> getCountList();

    @Query("select new UdaVideoEntity(t.categoryId, count(t.videoId)) " +
            "from UdaVideoEntity t where t.areaCode=?1 group by t.categoryId")
    List<UdaVideoEntity> getCategoryCountList(AreaCodes areaCode);

    Page<UdaVideoEntity> getAllByAreaCodeAndCategoryId(AreaCodes areaCode, String categoryId,Pageable pageable);

    @Query("select u from UdaVideoEntity u where u.areaCode = :areaCode")
    Page<UdaVideoEntity> findByAreaCode(AreaCodes areaCode, Pageable pageable);
}
