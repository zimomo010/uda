package com.bu.admin.api;

import com.bu.admin.extend.exception.ErrorCode;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.utils.JSONUtils;
import com.google.gson.JsonObject;


public class Response {

    private Response() {
    }

    private JsonObject resultJson;

    public String build() {
        return resultJson.toString();
    }

    public static Response ok() {
        return ok(null);
    }

    public static Response ok(Object result) {

        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("code", ErrorCodes.NO_ERROR.getCode());
        resultJson.addProperty("message", "success");
        resultJson.addProperty("detailMessage", "success");

        if (result != null) {
            JsonObject r = new JsonObject();
            r.add("data", JSONUtils.toJSONElement(result));
            resultJson.add("result", r);
        }

        Response response = new Response();
        response.resultJson = resultJson;

        return response;
    }


    public static Response error(ErrorCode errorCode) {
        JsonObject resultJson = new JsonObject();
        resultJson.addProperty("code", errorCode.getCode());
        resultJson.addProperty("message", errorCode.getMessage());

        Response response = new Response();
        response.resultJson = resultJson;

        return response;
    }


    public Response message(String detailMsg) {
        resultJson.addProperty("detailMessage", detailMsg);
        return this;
    }

}
