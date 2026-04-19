package com.bu.admin.api;

import com.google.gson.JsonObject;


class RequestStrVerifyUtils {

    private RequestStrVerifyUtils() {
        throw new IllegalStateException("Utility class");
    }

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
