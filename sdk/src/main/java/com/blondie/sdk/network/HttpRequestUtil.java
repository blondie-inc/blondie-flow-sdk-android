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

    public static void provideData(String apiKey, JSONObject jsonParams){
        createHttpConnection(apiKey, jsonParams);
    }

    private static void createHttpConnection(String authKey, JSONObject jsonParams) {
        try {
            String basicAuth = "Basic " + authKey;
            URL url = new URL(BASE_URL);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", basicAuth);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Log.i("JSON", jsonParams.toString());
            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(jsonParams.toString());

            os.flush();
            os.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG" , conn.getResponseMessage());

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
