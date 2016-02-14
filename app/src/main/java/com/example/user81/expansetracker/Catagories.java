package com.example.user81.expansetracker;

import java.io.Serializable;
import java.util.ArrayList;

public class Catagories implements Serializable{
    public ArrayList<String> catagories;
    public ArrayList<Double> spendings;
    public Double limit;

    public Catagories(){
        catagories=new ArrayList<String>();
        spendings=new ArrayList<Double>();

        catagories.add("Food");
        catagories.add("Cloths");
        catagories.add("Travel");
        catagories.add("Loans");
        catagories.add("Entertainment");

        spendings.add(0.0);
        spendings.add(0.0);
        spendings.add(0.0);
        spendings.add(0.0);
        spendings.add(0.0);
    }

    public void addCatagory(String catagory){
        catagories.add(catagory);
        spendings.add(0.0);
    }

    public void addSpending(int index, Double money){
        double temp=spendings.get(index);
        spendings.remove(index);
        spendings.add(index,temp+money);
    }

    public void setLimit(Double limit){
        this.limit=limit;
    }
}
