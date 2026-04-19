package com.bu.admin.api;

import com.google.gson.JsonObject;

/**
 * JSON必输项校验
 * Created by mac on 15/3/11.
 */
class RequestStrVerifyUtils {

    private RequestStrVerifyUtils() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * @param json
     * @param verifyTargets
     */
    static void jsonObjectEmptyVerify(JsonObject json, String[] verifyTargets) throws ParamEmptyVerifyException {
        //verify input
        for (String input : verifyTargets) {
            if (!json.has(input)) {
                throw new ParamEmptyVerifyException(input);
            }

            //gson deal
            if (json.get(input).toString().equals("\"\"")) {
                throw new ParamEmptyVerifyException(input);
            }
        }
    }
}
