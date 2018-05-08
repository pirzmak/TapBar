package com.example.user.tapbar.ownerViewModel.components;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.tapbar.R;
import com.example.user.tapbar.clientServices.BarRepository;
import com.example.user.tapbar.ownerViewModel.Reservation;
import com.example.user.tapbar.utils.MyCallbackInterface;

import java.util.ArrayList;

public class MyReservationsListAdapter extends RecyclerView.Adapter {

    private ArrayList<Reservation> reservations = new ArrayList<>();

    private RecyclerView mRecyclerView;

    private MyCallbackInterface<String> onAccept;
    public MyCallbackInterface<String> onCancel;

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mDate;
        public TextView mGostsNumber;
        public TextView mGostName;
        public String id;
        public Button acceptButton;
        public Button cancelButton;

        public MyViewHolder(View pItem) {
            super(pItem);
            mDate = (TextView) pItem.findViewById(R.id.table_date);
            mGostsNumber = (TextView) pItem.findViewById(R.id.table_gests_number);
            mGostName = (TextView) pItem.findViewById(R.id.table_name);
            ((LinearLayout) pItem.findViewById(R.id.table_layout)).setBackgroundColor(Color.rgb(123,123,132));
            acceptButton = (Button) pItem.findViewById(R.id.accept_button);
            acceptButton.setOnClickListener((View v) -> {
                this.removeAt(getAdapterPosition());
                onAccept.apply(id);
            });
            cancelButton = (Button) pItem.findViewById(R.id.cancel_button);
            cancelButton.setOnClickListener((View v) -> {
                this.removeAt(getAdapterPosition());
                onCancel.apply(id);
            });
        }

        public void removeAt(int position) {
            reservations.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, reservations.size());
        }
    }

    public MyReservationsListAdapter(ArrayList<Reservation> pReservations, RecyclerView pRecyclerView,
                                     MyCallbackInterface<String> onAccept, MyCallbackInterface<String> onCancel){
        reservations = pReservations;
        mRecyclerView = pRecyclerView;
        this.onAccept = onAccept;
        this.onCancel = onCancel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.myreservationslist, viewGroup, false);

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
        Reservation reservation = reservations.get(i);
        ((MyViewHolder) viewHolder).mGostName.setText(reservation.getPersonName());
        ((MyViewHolder) viewHolder).mDate.setText(reservation.duration());
        ((MyViewHolder) viewHolder).mGostsNumber.setText("" + reservation.getNumberGosts());
    }

    @Override
    public int getItemCount() {
        return this.reservations.size();
    }
}