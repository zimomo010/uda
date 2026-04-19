package com.bu.admin.feign.utils;

import com.bu.admin.utils.codec.RSACoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@SuppressWarnings("squid:S2696")
public class ServerTokenUtils {
    private ServerTokenUtils(){
        //do nothing
    }
    private static String applicationName;

    private  static final String PUB_KEY = """
            MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCo9ZNCf80nm4IdeiaS70jDcpOS
            7kA9+SXYz2Rve+Q+KavV8qmnSOHNHISqPycLCu/FNvD7PW/OOU4SKyrGvkQuvlqq
            YNarDlfaQMCJohOI41GSomDw3MmoItra6Dms2Zz0+DPh71VGFGqnkEp1Mcz9Piyd
            wGvyBf13cDJFeF6kEQIDAQAB
            """;

    @Value("${spring.application.name}")
    private void setApplicationName(String applicationNameStr) {
        applicationName = applicationNameStr;
    }

    public static String getApplicationName() {
        return applicationName;
    }

    public static String getServerToken() {
        return RSACoder.signByPub(applicationName, PUB_KEY, "utf-8");
    }

}
