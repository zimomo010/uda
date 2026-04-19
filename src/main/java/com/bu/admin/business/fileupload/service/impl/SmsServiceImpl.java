package com.bu.admin.business.fileupload.service.impl;

import com.bu.admin.business.fileupload.enums.NoticeBusType;
import com.bu.admin.business.fileupload.service.SmsService;
import com.bu.admin.feign.enumtype.user.PhoneAreaCodes;
import com.bu.admin.feign.service.UserCenterClient;
import com.bu.admin.feign.utils.ResponseCheckUtils;
import com.bu.admin.feign.utils.ServerTokenUtils;
import com.bu.admin.system.Init;
import com.bu.admin.utils.JSONUtils;
import com.google.gson.JsonObject;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.EnumMap;


/**
 * sms Service
 *
 * @author ghostWu
 */
@Service
public class SmsServiceImpl implements SmsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${file.upload.type}")
    private String fileUploadType;
    @Resource
    private UserCenterClient userCenterClient;
    private static EnumMap<PhoneAreaCodes, String> regSmsTemplateMap = new EnumMap<>(PhoneAreaCodes.class);
    private static EnumMap<PhoneAreaCodes, String> resetPassSmsTemplateMap = new EnumMap<>(PhoneAreaCodes.class);


    @Init
    public void setSmsTemplate() {
        logger.info("初始化短信配置信息......");
        JsonObject smsTemplate = getConfig("smsTemplate");
        logger.info("sms template config is [{}]", smsTemplate);
        JsonObject regTemps = smsTemplate.getAsJsonObject("regSms");
        if (null != regTemps) {
            regSmsTemplateMap.put(PhoneAreaCodes.CN, regTemps.get(PhoneAreaCodes.CN.name()).getAsString());
            regSmsTemplateMap.put(PhoneAreaCodes.HKG, regTemps.get(PhoneAreaCodes.HKG.name()).getAsString());
        } else {
            logger.error("reg sms template could not get......");
        }

        JsonObject resetPassTemps = smsTemplate.getAsJsonObject("resetPassSms");
        if (null != regTemps) {
            resetPassSmsTemplateMap.put(PhoneAreaCodes.CN, resetPassTemps.get(PhoneAreaCodes.CN.name()).getAsString());
            resetPassSmsTemplateMap.put(PhoneAreaCodes.HKG, resetPassTemps.get(PhoneAreaCodes.HKG.name()).getAsString());
        } else {
            logger.error("resetPass sms template could not get......");
        }
    }


    @Override
    public void sendBusSMS(PhoneAreaCodes userAreaCodes, String mobileNum, String[] templateKeys, NoticeBusType busType) {
        logger.info("---->>send [{}] area mobile number [{}] message start<<----", userAreaCodes, mobileNum);
    }

    //----------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------
    private JsonObject getConfig(String configName) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("configName", configName);
        String ucResponse = userCenterClient.getConfigDetail(JSONUtils.toJson(jsonObject), ServerTokenUtils.getApplicationName(), ServerTokenUtils.getServerToken());
        logger.info("uc response info is:{}", ucResponse);
        return ResponseCheckUtils.checkResponse(ucResponse).getAsJsonObject();
    }
}
