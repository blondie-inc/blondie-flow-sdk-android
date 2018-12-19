package com.blondie.sdk;

import android.content.Context;


public class Blondie {

    private static String sApiKey = "";
    private static boolean isDisableOffline = false;
    private static boolean isDisableAutoRetries = false;
    private static String baseUrl = "https://flow.blondie.lv/webhooks/events";

    public static void setApiKey(String apiKey) {
        sApiKey = apiKey;
    }

    public static void useDevelopmentEnvironment() {
        baseUrl = " https://flow-dev.blondie.lv/webhooks/events";
    }

    public static void useTestEnvironment() {
        baseUrl = "https://flow-test.blondie.lv/webhooks/events";
    }

    public static void useProductionEnvironment() {
        baseUrl = "https://flow.blondie.lv/webhooks/events";
    }

    public static void setBaseUrl(String url) {
        baseUrl = url;
    }

    public static void disableOfflineMode() {
        isDisableOffline = true;
    }

    public static void disableAutoRetries() {
        isDisableAutoRetries = true;
    }

    public static void triggerEvent(Context context, BlondieEvent blondieEvent) {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.buildRequest(context, blondieEvent, sApiKey, isDisableOffline, isDisableAutoRetries, baseUrl);
    }
}
