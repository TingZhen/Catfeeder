package com.example.anew.myapplication;

import android.widget.TextView;

/**
 * Created by NEW on 2017/12/3.
 */

public class feedset {
    private String time;
    private int weight;
    public feedset(TextView textView, int weight) {
    }
    public feedset(String time,int weight) {
        this.time = time;
        this.weight  = weight;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight=weight;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time=time;
    }


}
