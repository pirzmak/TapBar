package com.example.user.tapbar.ownerViewModel;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.tapbar.MyFirebaseInstanceIDService;
import com.example.user.tapbar.R;
import com.example.user.tapbar.clientViewModel.MapsActivity;
import com.example.user.tapbar.ownerViewModel.components.MyTableListAdapter;
import com.example.user.tapbar.ownerServices.OwnerRepository;
import com.example.user.tapbar.ownerViewModel.components.NewTableActivity;

import org.json.JSONException;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class OwnerActivity extends AppCompatActivity {

    private OwnerRepository repository;
    private ArrayList<Table> tables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tables);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        MyFirebaseInstanceIDService.init(this);

        repository = new OwnerRepository(getApplicationContext());

        repository.getTables((tables) -> this.tables = tables, (failure) -> {});

        recyclerView.setAdapter(new MyTableListAdapter(tables, recyclerView));

        FloatingActionButton addNewTableButton = findViewById(R.id.addNewTable);
        addNewTableButton.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, NewTableActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "");
            startActivity(intent);
        });

    }
}
