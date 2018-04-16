package com.example.user.tapbar.ownerServices;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.user.tapbar.ownerViewModel.Reservation;
import com.example.user.tapbar.ownerViewModel.Table;
import com.example.user.tapbar.utils.HttpService;
import com.example.user.tapbar.utils.MyCallbackInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OwnerRepository {
    private Context context;
    private static final String ownerRepUrl = "localhost::asd/";

    public OwnerRepository(Context context) {
        this.context = context;
    }

    private void get(String url,
                     final MyCallbackInterface<JSONObject> onSuccess,
                     final MyCallbackInterface<VolleyError> onFailure) {
        HttpService.getInstance(this.context).HTTPGetRequest(ownerRepUrl + url, onSuccess, onFailure);
    }

    private void post(String url,
                      JSONObject body,
                      final MyCallbackInterface<JSONObject> onSuccess,
                      final MyCallbackInterface<VolleyError> onFailure) {
        HttpService.getInstance(this.context).HTTPPostRequest(ownerRepUrl + url, body, onSuccess, onFailure);
    }

    public void getTables(final MyCallbackInterface<ArrayList<Table>> onSuccess,
                          final MyCallbackInterface<VolleyError> onFailure) {
//        get("tables", (JSONObject response) -> {
//            Gson gson = new GsonBuilder().create();
//            Table[] t = gson.fromJson(response.toString(), Table[].class);
//            onSuccess.apply(new ArrayList<Table>(Arrays.asList(t)));}, onFailure);

        ArrayList<Table> tables = new ArrayList<>();
        ArrayList<Reservation> res = new ArrayList<>();
        res.add(new Reservation("Adam Nowak"));
        tables.add(new Table(1, 5, res));
        res = new ArrayList<>();
        res.add(new Reservation("Karol Kowal"));
        tables.add(new Table(1, 5, res));
        res = new ArrayList<>();
        res.add(new Reservation("Marek Zieliński"));
        tables.add(new Table(1, 5, res));
        res = new ArrayList<>();
        res.add(new Reservation("Asia Szpak"));
        tables.add(new Table(1, 5, res));

        onSuccess.apply(tables);
    }

    public void addTable(Table table,
                    final MyCallbackInterface<ArrayList<Table>> onSuccess,
                    final MyCallbackInterface<VolleyError> onFailure) throws JSONException {
        Gson gson = new GsonBuilder().create();
        String jsonInString = gson.toJson(table);
        this.post("", new JSONObject(jsonInString), (JSONObject response) -> {
            //TODO
        }, (a) -> {});
    }

}
