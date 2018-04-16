package com.example.user.tapbar.utils;

public final class MyMath {
    public static String parseTo2Decimal(int value){
        return value > 10 ? "" + value : "0" + value;
    }
}
