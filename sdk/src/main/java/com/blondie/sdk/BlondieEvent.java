package com.blondie.sdk;

import org.json.JSONException;
import org.json.JSONObject;

public class BlondieEvent {
    private String form;
    private JSONObject jsonParams;

    public BlondieEvent(String from) {
        this.form = form;
        set("eventName", from);
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
