package com.blondie.sdk.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.blondie.sdk.BlondieEvent;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by alex on 12/9/18.
 */

public class HttpRequestUtil {


    private static final String BASE_URL = "https://flow.blondie.lv/webhooks/events";

    public static void provideData(String apiKey, Queue<BlondieEvent> blondieEventQueue) {
        for (int i = blondieEventQueue.size(); i > 0 ; i--){
            BlondieEvent blondieEvent = blondieEventQueue.remove();
            Log.d("[Blondie]", blondieEvent.getJsonParams().toString());
            JSONObject jsonParams = blondieEvent.getJsonParams();
            createHttpConnection(apiKey, jsonParams);
        }
    }

    private static void createHttpConnection(final String authKey, final JSONObject jsonParams) {
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

            httpsURLConnection.disconnect();

        } catch (IOException e) {
            Log.i("[Blondie]", e.toString());
            e.printStackTrace();
        }
    }
}
