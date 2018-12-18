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

/**
 * Created by alex on 12/9/18.
 */

public class Blondie {

    private static String sApiKey;
    private static boolean isDisableOffline = false;
    private static Queue<BlondieEvent> blondieEventQueue = new LinkedList<>();

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

    }

    public static void triggerEvent(final Context context, final BlondieEvent blondieEvent) {
        new Thread(new Runnable() {
            public void run() {
                String ifa = getIfaInfo(context);
                blondieEvent.set("deviceId", ifa);
                blondieEventQueue.add(blondieEvent);
                if (!isDisableOffline) {
                    if (isNetworkConnected(context)) {
                        HttpRequestUtil.provideData(sApiKey, blondieEventQueue);
                    }
                } else {
                    HttpRequestUtil.provideData(sApiKey, blondieEventQueue);
                }
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

    private static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isNetworkConnected = cm.getActiveNetworkInfo() != null;
        Log.d("[Blondie]", "Check isNetworkConnected: " + isNetworkConnected);
        return isNetworkConnected;
    }
}
