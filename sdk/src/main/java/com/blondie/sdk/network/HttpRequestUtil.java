package com.blondie.sdk.network;

import android.util.Log;

import com.blondie.sdk.BlondieEvent;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;

import javax.net.ssl.HttpsURLConnection;

public class HttpRequestUtil {

    private static final int TRY_COUNT = 5;
    private static final String BASE_URL = "https://flow.blondie.lv/webhooks/events";

    public static void provideData(String apiKey, Queue<BlondieEvent> blondieEventQueue, boolean isDisableAutoRetries) {
        for (int i = blondieEventQueue.size(); i > 0; i--) {
            Log.d("[Blondie]", "Check size(): " + blondieEventQueue.size());
            BlondieEvent blondieEvent = blondieEventQueue.remove();
            Log.d("[Blondie]", blondieEvent.getJsonParams().toString());
            JSONObject jsonParams = blondieEvent.getJsonParams();
            if(!isDisableAutoRetries){
                for(int j = 0; j <= TRY_COUNT; j++){
                    int responseCode = createHttpConnection(apiKey, jsonParams);
                    Log.d("[Blondie]", "Check response code: " + responseCode);
                    if(responseCode < 400){
                        break;
                    }
                }
            }
        }
    }

    private static int createHttpConnection(final String authKey, final JSONObject jsonParams) {
        int responseCode = 0;
        try {
            URL url = new URL(BASE_URL);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("Content-Type", "application/json");
            httpsURLConnection.setRequestProperty("Authorization", "Bearer " + authKey);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.connect();

            DataOutputStream os = new DataOutputStream(httpsURLConnection.getOutputStream());
            os.writeBytes(jsonParams.toString());
            os.flush();
            os.close();


            Log.i("[Blondie]", String.valueOf(httpsURLConnection.getResponseCode()));
            Log.i("[Blondie]", httpsURLConnection.getResponseMessage());
            responseCode = httpsURLConnection.getResponseCode();

            httpsURLConnection.disconnect();
        } catch (IOException e) {
            Log.i("[Blondie]", e.toString());
            e.printStackTrace();
        }
        return responseCode;
    }
}
