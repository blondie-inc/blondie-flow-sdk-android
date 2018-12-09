package com.blondie.sdk;

import com.blondie.sdk.network.HttpRequestUtil;

/**
 * Created by alex on 12/9/18.
 */

public class Blondie{

    private static String sApiKey;

    public static void setApiKey(String apiKey){
        sApiKey = apiKey;
    }

    public static void setBaseUrl(String url){

    }

    public static void disableOfflineMode(){

    }

    public static void disableAutoRetries(){

    }

    public static void triggerEvent(BlondieEvent blondieEvent){

        HttpRequestUtil.provideData(sApiKey ,blondieEvent.getJsonParams());
    }
}
