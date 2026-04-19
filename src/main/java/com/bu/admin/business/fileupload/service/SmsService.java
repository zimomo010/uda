package com.bu.admin.business.fileupload.service;


import com.bu.admin.business.fileupload.enums.NoticeBusType;
import com.bu.admin.feign.enumtype.user.PhoneAreaCodes;

/**
 * 画廊Service接口
 *
 * @author ghostWu
 * @date 15/5/6
 */
public interface SmsService {

    /**
     * 发送短信
     * @param userAreaCodes
     * @param mobileNum
     * @param templateKeys
     * @param busType
     * @return
     */
    void sendBusSMS(PhoneAreaCodes userAreaCodes, String mobileNum, String[] templateKeys, NoticeBusType busType);
}
