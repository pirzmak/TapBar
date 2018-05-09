package com.example.user.tapbar.ownerViewModel.components;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.tapbar.R;
import com.example.user.tapbar.ownerViewModel.Table;

import java.util.ArrayList;

public class MyTableListAdapter extends RecyclerView.Adapter {

    private ArrayList<Table> tables = new ArrayList<>();

    private RecyclerView mRecyclerView;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mContent;
        public TextView mNumber;

        public MyViewHolder(View pItem) {
            super(pItem);
            mTitle = (TextView) pItem.findViewById(R.id.table_title);
            mContent = (TextView) pItem.findViewById(R.id.table_subtitle);
            mNumber = (TextView) pItem.findViewById(R.id.table_id);
            ((LinearLayout) pItem.findViewById(R.id.table_layout)).setBackgroundColor(Color.rgb(123,123,132));
        }
    }

    public MyTableListAdapter(ArrayList<Table> pTables, RecyclerView pRecyclerView){
        tables = pTables;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.mylist, viewGroup, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu
        Table table = tables.get(i);
        String number = "" + i;
        if(i<10)
            number = "0" + i;
        ((MyViewHolder) viewHolder).mNumber.setText(number);
        String title = table.getActualReservation() != null ? table.getActualReservation().getPersonName() : "Wolne";
        ((MyViewHolder) viewHolder).mTitle.setText(title);
        String duration = table.getActualReservation() != null ? table.getActualReservation().duration() : "";
        ((MyViewHolder) viewHolder).mContent.setText(duration);
    }

    @Override
    public int getItemCount() {
        return tables.size();
    }
}