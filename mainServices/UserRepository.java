package com.example.user.tapbar.mainServices;

import android.content.Context;

import com.android.volley.VolleyError;
import com.example.user.tapbar.utils.HttpService;
import com.example.user.tapbar.utils.MyCallbackInterface;
import com.example.user.tapbar.utils.User;

import org.json.JSONObject;

public class UserRepository {
    private Context context;
    private static final String ownerRepUrl = "localhost::asd/";

    public UserRepository(Context context) {
        this.context = context;
    }

    private void get(String url,
                     final MyCallbackInterface<JSONObject> onSuccess,
                     final MyCallbackInterface<VolleyError> onFailure){
        HttpService.getInstance(this.context).HTTPGetRequest(ownerRepUrl + url, onSuccess, onFailure);
    }

    public void getUserById(final MyCallbackInterface<User> onSuccess,
                            final MyCallbackInterface<VolleyError> onFailure) {
//        get("tables", (JSONObject response) -> {
//            Gson gson = new GsonBuilder().create();
//            Table[] t = gson.fromJson(response.toString(), Table[].class);
//            onSuccess.apply(new ArrayList<Table>(Arrays.asList(t)));}, onFailure);


        onSuccess.apply(new User(0, "Jan Nowak"));
    }

}
