package com.bu.admin.feign.service.impl;


import com.bu.admin.feign.bo.trade.FundsAccount;
import com.bu.admin.feign.bo.trade.Trade;
import com.bu.admin.feign.service.TradeCenterClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class TradeCenterClientHystrix implements TradeCenterClient {
    @Override
    public String getFinAccountList(FundsAccount fundsAccount, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String findAccountLog(FundsAccount fundsAccount, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String updateTradeCusRemark(Trade trade, String serverName, String serverToken) {
        return hystrixResponse();
    }

    @Override
    public String getUserFinanceAccInfo(FundsAccount fundsAccount, String serverName, String serverToken) {
        return hystrixResponse();
    }



    private String hystrixResponse() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", 500);
        jsonObject.addProperty("msg", "网络错误,请稍后再试");
        return jsonObject.toString();
    }
}
