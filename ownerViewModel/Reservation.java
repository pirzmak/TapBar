package com.example.user.tapbar.ownerViewModel;

import com.example.user.tapbar.utils.MyMath;
import com.example.user.tapbar.utils.User;

import java.util.Date;

public class Reservation {
    User person;
    Date from;
    Date to;
    int numberGosts;
    String _id;
    String barId;

    public Reservation(String name){
        this.person = new User(1, name);
        this.from = new Date(118,3,16,22,10);
        this.to = new Date(118,3,17,1,30);
    }

    public Reservation(User person, Date from, Date to, int numberGosts, String barId) {
        this.person = person;
        this.from = from;
        this.to = to;
        this.numberGosts = numberGosts;
        this._id = null;
        this.barId = barId;
    }


    public String getPersonName() {
        return this.person.getName();
    }

    private String getTime(Date date) {
        return "" + MyMath.parseTo2Decimal(date.getHours()) + ":" + MyMath.parseTo2Decimal(date.getMinutes());
    }

    public String duration() {
        return "" + this.getTime(this.from) + "-" + this.getTime(this.to);
    }

    public int getNumberGosts() {
        return numberGosts;
    }
}
