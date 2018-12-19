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

class RequestBuilder {
    private static Queue<BlondieEvent> blondieEventQueue = new LinkedList<>();

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
        boolean isNetworkConnected = false;
        if (cm != null) {
            isNetworkConnected = cm.getActiveNetworkInfo() != null;
        }
        Log.d("[Blondie]", "Check isNetworkConnected: " + isNetworkConnected);
        return isNetworkConnected;
    }

    void buildRequest(final Context context, final BlondieEvent blondieEvent, final String apiKey,
                      final boolean isDisableOffline, final boolean isDisableAutoRetries, final String baseUrl) {
        new Thread(new Runnable() {
            public void run() {
                String ifa = getIfaInfo(context);
                blondieEvent.set("deviceId", ifa);
                blondieEventQueue.add(blondieEvent);
                if (!isDisableOffline) {
                    if (isNetworkConnected(context)) {
                        HttpRequestUtil.provideData(apiKey, blondieEventQueue, isDisableAutoRetries, baseUrl);
                    }
                } else {
                    HttpRequestUtil.provideData(apiKey, blondieEventQueue, isDisableAutoRetries, baseUrl);
                }
            }
        }).start();
    }
}
