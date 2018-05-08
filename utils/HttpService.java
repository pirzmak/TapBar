package com.example.user.tapbar.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.user.tapbar.AppSingleton;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpService {
    private static HttpService ourInstance;

    public static HttpService getInstance(Context context) {

        if (ourInstance == null) {
            ourInstance = new HttpService(context);
        }
        return ourInstance;
    }

    private Context context;

    private HttpService(Context context) {
        this.context = context;
    }

    public void HTTPGetRequest(String url,
                               final MyCallbackInterface<JSONArray> onSuccess,
                               final MyCallbackInterface<VolleyError> onFailure) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, (a) -> {
                    Log.d("GET", "Send" + a); onSuccess.apply(a);}, (a) -> {
                    Log.d("GET", "Send" + a);});

        AppSingleton.getInstance(this.context).addToRequestQueue(jsonArrayRequest);
    }

    public void HTTPPostRequest(String url,
                               JSONObject body,
                               final MyCallbackInterface<JSONObject> onSuccess,
                               final MyCallbackInterface<VolleyError> onFailure) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, body, onSuccess::apply, onFailure::apply);

        AppSingleton.getInstance(this.context).addToRequestQueue(jsonObjectRequest);
    }
}
