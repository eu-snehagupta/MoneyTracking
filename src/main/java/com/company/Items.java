package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Items {
    public String type;
    public String title;
    public LocalDate dateFormat;
    //private String dateFormat;
    public double amount;

    public Items(String type, String title, String date, double amount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.type = type;
        this.title = title;
        this.dateFormat = LocalDate.parse(date, formatter);  //exceptional handling is required for this in futher steps.
        //this.dateFormat = date;
        this.amount = amount;

    }
    @Override
    public String toString() {

        return "" + type + "**" + title + "**" + dateFormat + "**" + amount;

    }
}
