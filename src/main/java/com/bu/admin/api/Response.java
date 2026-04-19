package com.bu.admin.api;

import com.bu.admin.extend.exception.ErrorCode;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.utils.JSONUtils;
import com.google.gson.JsonObject;

/**
 * webservice接口响应实体
 * Created by ghostWu on 15/8/19.
 */
public class Response {

    private Response() {
    }

    private JsonObject resultJson; // 实际的响应内容

    /**
     * 构造最终的响应内容
     *
     * @return
     */
    public String build() {
        return resultJson.toString();
    }


    /**
     * 接口调用成功
     *
     * @return
     */
    public static Response ok() {
        return ok(null);
    }

    /**
     * 接口调用成功
     *
     * @param result
     * @return
     */
    public static Response ok(Object result) {

        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("code", ErrorCodes.NO_ERROR.getCode());
        resultJson.addProperty("message", "交互成功");
        resultJson.addProperty("detailMessage", "交互成功");

        if (result != null) {
            JsonObject r = new JsonObject();
            r.add("data", JSONUtils.toJSONElement(result));
            resultJson.add("result", r);
        }

        Response response = new Response();
        response.resultJson = resultJson;

        return response;
    }


    /**
     * 接口调用失败
     *
     * @param errorCode
     * @return
     */
    public static Response error(ErrorCode errorCode) {
        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("code", errorCode.getCode());
        resultJson.addProperty("message", errorCode.getMessage());

        Response response = new Response();
        response.resultJson = resultJson;

        return response;
    }

    /**
     * 设置接口描述信息
     * @param detailMsg
     * @return
     */
    public Response message(String detailMsg) {
        resultJson.addProperty("detailMessage", detailMsg);
        return this;
    }

}
