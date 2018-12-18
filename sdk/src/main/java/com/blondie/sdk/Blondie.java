package com.blondie.sdk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.blondie.sdk.network.HttpRequestUtil;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class Blondie {

    private static String sApiKey= "";
    private static boolean isDisableOffline = false;
    private static boolean isDisableAutoRetries = false;

    public static void setApiKey(String apiKey) {
        sApiKey = apiKey;
    }

    public static void useDevelopmentEnvironment() {

    }

    public static void useTestEnvironment() {

    }

    public static void useProductionEnvironment() {

    }

    public static void setBaseUrl(String url) {

    }

    public static void disableOfflineMode() {
        isDisableOffline = true;
    }

    public static void disableAutoRetries() {
        isDisableAutoRetries = true;
    }

    public static void triggerEvent(Context context, BlondieEvent blondieEvent) {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.buildRequest(context, blondieEvent, sApiKey, isDisableOffline, isDisableAutoRetries);
    }
}
