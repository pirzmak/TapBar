package com.example.user.tapbar.ownerViewModel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.user.tapbar.MyFirebaseInstanceIDService;
import com.example.user.tapbar.R;
import com.example.user.tapbar.clientServices.BarRepository;
import com.example.user.tapbar.ownerViewModel.components.MyReservationsListAdapter;

import org.json.JSONException;

import java.util.ArrayList;

public class ReservationsListActivity extends AppCompatActivity {

    private BarRepository repository;
    private ArrayList<Reservation> reservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.reservations);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        MyFirebaseInstanceIDService.init(this);

        repository = new BarRepository(getApplicationContext());

        repository.getReservations((reservations) -> {
            this.reservations = reservations;

            recyclerView.setAdapter(new MyReservationsListAdapter(reservations, recyclerView,
                    (accept) -> {
                        try {
                            this.repository.acceptReservation(accept,(r) -> {},(r) -> {});
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    (cancel) -> {
                        try {
                            this.repository.cancelReservation(cancel,(r) -> {},(r) -> {});
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }));
        }, (failure) -> {});
    }
}