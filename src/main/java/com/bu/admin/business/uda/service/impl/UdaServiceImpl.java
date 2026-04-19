package com.bu.admin.business.uda.service.impl;

import com.bu.admin.business.uda.bo.PublishTimeCount;
import com.bu.admin.business.uda.bo.TagCount;
import com.bu.admin.business.uda.bo.TendCount;
import com.bu.admin.business.uda.bo.UdaVideo;
import com.bu.admin.business.uda.enums.AreaCodes;
import com.bu.admin.business.uda.po.UdaVideoEntity;
import com.bu.admin.business.uda.po.UdaVideoStaticEntity;
import com.bu.admin.business.uda.repository.UdaRepository;
import com.bu.admin.business.uda.repository.UdaStaticRepository;
import com.bu.admin.business.uda.service.UdaService;
import com.bu.admin.business.uda.utils.UdaUtil;
import com.bu.admin.utils.BeanCopyUtils;
import com.bu.admin.utils.CsvUtils;
import com.bu.admin.utils.JSONUtils;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UdaServiceImpl implements UdaService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${localFile.store.path}")
    private String udaFilePath;
    @Resource
    private UdaRepository udaRepository;
    @Resource
    private UdaStaticRepository udaStaticRepository;

    @Override
    public void syncUdaData() {
        logger.info("---->>sync uda data <<----");
        List<String> areaList = Arrays.stream(AreaCodes.values())
                .map(Enum::name)
                .toList();
        logger.info("areaList is [{}]", areaList);
        areaList.forEach(this::syncFileToDataBase);
    }

    @Override
    public List<UdaVideoEntity> getCountData() {
        return udaRepository.getCountList();
    }

    @Override
    public List<UdaVideoEntity> getCategoryCountList(AreaCodes areaCode) {
        return udaRepository.getCategoryCountList(areaCode);
    }

    @Override
    public List<UdaVideoEntity> getTopVideoList(AreaCodes areaCode, String sortParam) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, sortParam));
        Page<UdaVideoEntity> page = udaRepository.findByAreaCode(areaCode, pageable);
        return page.getContent();
    }

    @Override
    public void videoTagCount() {
        List<String> areaList = Arrays.stream(AreaCodes.values())
                .map(Enum::name)
                .toList();
        String[] sortParams = {"views", "likes", "comments"};
        for (String a : areaList) {
            for (int i = 1; i <= 44; i++) {
                logger.info("cal area code {} category {}", a, i);
                List<UdaVideoEntity> udaTotalList = new ArrayList<>();
                for (String sortParam : sortParams) {
                    Pageable pageable = PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, sortParam));
                    List<UdaVideoEntity> udaVideoEntityList = udaRepository.getAllByAreaCodeAndCategoryId(AreaCodes.valueOf(a), String.valueOf(i), pageable).stream().toList();

                    udaTotalList.addAll(udaVideoEntityList);
                }
                List<TagCount> tagCountList = UdaUtil.countTags(udaTotalList);
                List<PublishTimeCount> publishTimeCountList = UdaUtil.countPublishHour(udaTotalList);
                List<TendCount> tendCounts = UdaUtil.countPublishTrendingDayDiff(udaTotalList);
                UdaVideoStaticEntity udaVideoStaticEntityEntity = udaStaticRepository.findAllByAreaCodeAndCategoryId(AreaCodes.valueOf(a), String.valueOf(i));
                if (null == udaVideoStaticEntityEntity) {
                    udaVideoStaticEntityEntity = new UdaVideoStaticEntity();
                    udaVideoStaticEntityEntity.setAreaCode(AreaCodes.valueOf(a));
                    udaVideoStaticEntityEntity.setCategoryId(String.valueOf(i));
                }
                udaVideoStaticEntityEntity.setTagStatic(JSONUtils.toJSONString(tagCountList));
                udaVideoStaticEntityEntity.setPublishStatic(JSONUtils.toJSONString(publishTimeCountList));
                udaVideoStaticEntityEntity.setTrendStatic(JSONUtils.toJSONString(tendCounts));
                udaStaticRepository.saveAndFlush(udaVideoStaticEntityEntity);
            }
        }
    }

    @Override
    public UdaVideoStaticEntity getVideoTagTop(AreaCodes areaCode, String categoryId) {
        UdaVideoStaticEntity udaVideoStaticEntity = udaStaticRepository.findAllByAreaCodeAndCategoryId(areaCode, categoryId);
        if(null == udaVideoStaticEntity){
            return new UdaVideoStaticEntity();
        }else {
            return udaVideoStaticEntity;
        }
    }

    @Override
    public UdaVideo userVideoCal(UdaVideo userVideo) {
        //todo
        userVideo.setLikes(2011);
        userVideo.setViews(1002);
        userVideo.setComments(2939939);
        userVideo.setDescription("need modify title");
        return userVideo;
    }

    private void syncFileToDataBase(String areaCode) {
        logger.info("---->>sync {} data <<----", areaCode);
        String fileName = udaFilePath + areaCode + "_Trending.csv";
        List<UdaVideo> udaVideos = CsvUtils.readCsvDeal(fileName, UdaVideo.class,
                BeanCopyUtils.getClassParamsWithIgnores(UdaVideo.class, "videoScore", "areaCode"), 1, false);
        logger.info("get area {} data size is {}", areaCode, udaVideos.size());
        udaVideos.forEach(udaVideo -> {
            UdaVideoEntity udaVideoEntity = BeanCopyUtils.convertBusinessValue(udaVideo, UdaVideoEntity.class);
            udaVideoEntity.setAreaCode(AreaCodes.valueOf(areaCode));
            udaRepository.saveAndFlush(udaVideoEntity);
        });
    }

}
