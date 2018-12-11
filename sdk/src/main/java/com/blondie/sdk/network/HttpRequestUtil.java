package com.blondie.sdk.network;

import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by alex on 12/9/18.
 */

public class HttpRequestUtil {


    private static final String BASE_URL = "https://flow.blondie.lv/webhooks/events";

    public static void provideData(String apiKey, JSONObject jsonParams) {
        createHttpConnection(apiKey, jsonParams);
    }

    private static void createHttpConnection(final String authKey, final JSONObject jsonParams) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    URL url = new URL(BASE_URL);
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                    httpsURLConnection.setRequestMethod("POST");
                    httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                    httpsURLConnection.setRequestProperty("Authorization", "Bearer " + authKey);
                    httpsURLConnection.setDoInput(true);
                    httpsURLConnection.setDoOutput(true);
                    httpsURLConnection.connect();

                    Log.i("[Blondie]", jsonParams.toString());

                    DataOutputStream os = new DataOutputStream(httpsURLConnection.getOutputStream());
                    os.writeBytes(jsonParams.toString());
                    os.flush();
                    os.close();


                    Log.i("[Blondie]", String.valueOf(httpsURLConnection.getResponseCode()));
                    Log.i("[Blondie]", httpsURLConnection.getResponseMessage());

                    httpsURLConnection.disconnect();

//                    autoRetries(conn.getResponseCode());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

//    private static void autoRetries(int responseCode) {
//        if(responseCode < 499){
//
//        }
//    }

}
