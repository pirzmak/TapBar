package com.example.user.tapbar.clientServices;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.user.tapbar.clientViewModel.Place;
import com.example.user.tapbar.ownerViewModel.Reservation;
import com.example.user.tapbar.utils.HttpService;
import com.example.user.tapbar.utils.MyCallbackInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class BarRepository {
    private Context context;
    private static final String BAR_URL = "https://a19addf9.ngrok.io/";

    ArrayList<Place> bars = new ArrayList<>();
    ArrayList<Reservation> reservations = new ArrayList<>();

    public BarRepository(Context context) {
        this.context = context;
    }

    private void get(String url,
                     final MyCallbackInterface<JSONArray> onSuccess,
                     final MyCallbackInterface<VolleyError> onFailure) {
        HttpService.getInstance(this.context).HTTPGetRequest(BAR_URL + url, onSuccess, onFailure);
    }

    private void post(String url,
                      JSONObject body,
                      final MyCallbackInterface<JSONObject> onSuccess,
                      final MyCallbackInterface<VolleyError> onFailure) {
        HttpService.getInstance(this.context).HTTPPostRequest(BAR_URL + url, body, onSuccess, onFailure);
    }

    public void isReserved(final MyCallbackInterface<ArrayList<Place>> onSuccess,
                        final MyCallbackInterface<VolleyError> onFailure) {
        get("reservations/reserved", (JSONArray response) -> {
            Gson gson = new GsonBuilder().create();
            PlaceServerModel[] b = gson.fromJson(response.toString(), PlaceServerModel[].class);
            ArrayList<Place> res = new ArrayList<>();
            for(int i = 0; i<b.length; ++i) {
                if(b[i].getName().equals("SuperBar"))
                    res.add(new Place(b[i]));
            }
            onSuccess.apply(res);}, onFailure);
    }

    public void getBars(final MyCallbackInterface<ArrayList<Place>> onSuccess,
                          final MyCallbackInterface<VolleyError> onFailure) {
        get("bars/", (JSONArray response) -> {
            Gson gson = new GsonBuilder().create();
            PlaceServerModel[] b = gson.fromJson(response.toString(), PlaceServerModel[].class);
            for(int i = 0; i<b.length; ++i) {
                this.bars.add(new Place(b[i]));
            }
            onSuccess.apply(this.bars);}, onFailure);
    }

    public void getReservations(final MyCallbackInterface<ArrayList<Reservation>> onSuccess,
                        final MyCallbackInterface<VolleyError> onFailure) {
        get("reservations/", (JSONArray response) -> {
            Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create();
            Reservation[] b = gson.fromJson(response.toString(), Reservation[].class);
            for(int i = 0; i<b.length; ++i) {
                this.reservations.add(b[i]);
            }
            onSuccess.apply(this.reservations);}, onFailure);
    }

    public void addReservation(Reservation reservation,
                         final MyCallbackInterface<ArrayList<Reservation>> onSuccess,
                         final MyCallbackInterface<VolleyError> onFailure) throws JSONException {
        Gson gson = new GsonBuilder().create();
        String jsonInString = gson.toJson(reservation);
        this.post("reservations/add", new JSONObject(jsonInString), (r) -> {}, (a) -> {});
    }

    public void acceptReservation(String id,
                               final MyCallbackInterface<ArrayList<Reservation>> onSuccess,
                               final MyCallbackInterface<VolleyError> onFailure) throws JSONException {
        this.post("reservations/accept", new JSONObject("{id: " + id + "}"), (r) -> {}, (a) -> {});
    }

    public void cancelReservation(String id,
                               final MyCallbackInterface<ArrayList<Reservation>> onSuccess,
                               final MyCallbackInterface<VolleyError> onFailure) throws JSONException {
        this.post("reservations/cancel", new JSONObject("{id: " + id + "}"), (r) -> {}, (a) -> {});
    }

//    public void addTable(Table table,
//                         final MyCallbackInterface<ArrayList<Table>> onSuccess,
//                         final MyCallbackInterface<VolleyError> onFailure) throws JSONException {
//        Gson gson = new GsonBuilder().create();
//        String jsonInString = gson.toJson(table);
//        tables.add(gson.fromJson(jsonInString, Table.class));
//        //  this.post("", new JSONObject(jsonInString), (JSONObject response) -> {
//        //TODO
//        // }, (a) -> {});
//    }
}
