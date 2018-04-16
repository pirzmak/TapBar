package com.example.user.tapbar.ownerViewModel;

import java.util.ArrayList;
import java.util.Date;

public class Table {
    private long tableId;
    private int size;
    private ArrayList<Reservation> reservations;

    public Table(long tableId, int size, ArrayList<Reservation> reservations) {
        this.tableId = tableId;
        this.size = size;
        this.reservations = reservations;
    }

    public Reservation getActualReservation(){
        for(int i = 0; i < this.getReservations().size(); i++)
            if(this.getReservations().get(i).to.after(new Date()) && this.getReservations().get(i).from.before(new Date()))
                return this.getReservations().get(i);
        return null;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }
}
