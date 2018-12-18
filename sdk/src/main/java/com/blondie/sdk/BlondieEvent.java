package com.blondie.sdk;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by alex on 12/9/18.
 */

public class BlondieEvent {
    private String form;
    private JSONObject jsonParams;

    public BlondieEvent(String from) {
        this.form = form;
    }

    public void set(String key, String value) {
        if (jsonParams == null) {
            jsonParams = new JSONObject();
        }
        try {
            jsonParams.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void set(String key, int value) {
        if (jsonParams == null) {
            jsonParams = new JSONObject();
        }
        try {
            jsonParams.put(key, String.valueOf(value));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJsonParams() {
        return jsonParams;
    }
}
