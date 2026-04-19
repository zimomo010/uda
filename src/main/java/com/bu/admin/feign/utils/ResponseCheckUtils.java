package com.bu.admin.feign.utils;

import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCode;
import com.bu.admin.utils.JSONUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Component
public class ResponseCheckUtils {
    private ResponseCheckUtils(){
        //do nothing
    }

    private static final String RESULT = "result";
    private static final String MESSAGE = "message";
    private static final Logger logger = LoggerFactory.getLogger(ResponseCheckUtils.class);

    public static JsonElement checkResponse(String responseStr) {
        JsonObject responseData = JsonParser.parseString(responseStr).getAsJsonObject();
        if (responseData.get("code").getAsInt() == 0) {
            if (null != responseData.get(RESULT)) {
                if(responseData.get(RESULT).getAsJsonObject().get("data") instanceof JsonObject) {
                    return responseData.get(RESULT).getAsJsonObject().get("data").getAsJsonObject();
                }else {
                    return responseData.get(RESULT).getAsJsonObject().get("data").getAsJsonArray();
                }
            } else {
                return new JsonObject();
            }
        } else {
            logger.error("response error, response is [{}]", responseStr);
            throw new BasicException(new ErrorCode(responseData.get("code").getAsInt(), responseData.get(MESSAGE).getAsString()));
        }
    }

    public static JsonObject checkResponseAndData(String responseStr) {
        logger.info("get response is :[{}]", responseStr);
        JsonObject responseData = JsonParser.parseString(responseStr).getAsJsonObject();
        if (responseData.get("code").getAsInt() == 0) {
            if (null != responseData.get(RESULT)) {
                return responseData.get(RESULT).getAsJsonObject().get("data").getAsJsonObject();
            } else {
                return null;
            }
        } else {
            throw new BasicException(new ErrorCode(responseData.get("code").getAsInt(), responseData.get(MESSAGE).getAsString()));
        }
    }

    public static <T> T getBeanFromResponse(String ucResponse, Class<T> classOfT) {
        logger.info("get response is [{}]",ucResponse);
        JsonObject responseData = JsonParser.parseString(ucResponse).getAsJsonObject();
        if (responseData.get("code").getAsInt() == 0) {
            if (null != responseData.get(RESULT)) {
                if (responseData.get(RESULT).getAsJsonObject().get("data") instanceof JsonObject) {
                    return JSONUtils.getJson(String.valueOf(responseData.get(RESULT).getAsJsonObject().get("data").getAsJsonObject()), classOfT);
                } else {
                    return JSONUtils.getJson(responseData.get(RESULT).getAsJsonObject().get("data").getAsString(), classOfT);
                }
            } else {
                return null;
            }
        } else {
            throw new BasicException(new ErrorCode(responseData.get("code").getAsInt(), responseData.get(MESSAGE).getAsString()));
        }
    }

    public static String getHeaderContentDisposition(String fileName) {
        try {
            return "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "attachment;filename=" + fileName;
        }
    }
}
