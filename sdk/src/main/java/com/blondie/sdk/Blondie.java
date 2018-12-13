package com.blondie.sdk;

import android.content.Context;
import android.util.Log;

import com.blondie.sdk.network.HttpRequestUtil;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

/**
 * Created by alex on 12/9/18.
 */

public class Blondie {

    private static String sApiKey;

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

    }

    public static void disableAutoRetries() {

    }

    public static void triggerEvent(final Context context, final BlondieEvent blondieEvent) {
        Log.d("[Blondie]", "Go trough triggerEvent");
        new Thread(new Runnable() {
            public void run() {
                String ifa = getIfaInfo(context);
                blondieEvent.set("deviceId", ifa);
                HttpRequestUtil.provideData(sApiKey, blondieEvent.getJsonParams());
            }
        }).start();
    }

    private static String getIfaInfo(Context context) {
        String AdvertisingID = "";
        try {
            AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            AdvertisingID = adInfo.getId();
            Log.d("[Blondie]", "Check ID: " + AdvertisingID);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        }
        return AdvertisingID;
    }
}
