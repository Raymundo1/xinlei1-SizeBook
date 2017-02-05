package com.example.mac.xinlei1_sizebook;

import android.util.Log;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mac on 2017/1/23.
 * Person class
 */

public class person implements Serializable{

    private String name;

    //private Calendar date = Calendar.getInstance();
    //private SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
    private String date;

    private double neck;
    private double bust;
    private double chest;
    private double waist;
    private double hip;
    private double inseam;

    private String comment;

    private Integer id;

    public person(String name, String date, double neck, double bust, double chest,
                  double waist, double hip, double inseam, String comment) {
        this.name = name;
        this.date = date;
        this.neck = neck;
        this.bust = bust;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.inseam = inseam;
        this.comment = comment;
        this.id = 0;
    }

    public person(String name, String date) {
        this.name = name;
        this.date = date;
        this.neck = 0.0;
        this.bust = 0.0;
        this.chest = 0.0;
        this.waist = 0.0;
        this.hip = 0.0;
        this.inseam = 0.0;
        this.comment = "";
        this.id = 0;
    }

    public person() {
        this.name = "";
        this.date = "";
        this.neck = 0.0;
        this.bust = 0.0;
        this.chest = 0.0;
        this.waist = 0.0;
        this.hip = 0.0;
        this.inseam = 0.0;
        this.comment = "";
        this.id = 0;
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // Date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /*
    public void setDate(String date) {
        if (date.length() == 0) {
                return;
        } else {
                this.date = date;
        }

    }*/


    // Neck
    public double getNeck() {
        return neck;
    }

    public void setNeck(float neck) {
        this.neck = neck;
    }

    public void setNeck(String neck) {
        if (neck.length() == 0) {
            this.neck = 0.0;
        } else {
            this.neck = Double.parseDouble(neck);
        }
    }


    // Bust
    public double getBust() {
        return bust;
    }

    public void setBust(float bust) {
        this.bust = bust;
    }

    public void setBust(String bust) {
        if (bust.length() == 0) {
            this.bust = 0.0;
        } else {
            this.bust = Double.parseDouble(bust);
        }
    }


    // Chest
    public double getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public void setChest(String chest) {
        if (chest.length() == 0) {
            this.chest = 0.0;
        } else {
            this.chest = Double.parseDouble(chest);
        }
    }


    // Waist
    public double getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public void setWaist(String waist) {
        if (waist.length() == 0) {
            this.waist = 0.0;
        } else {
            this.waist = Double.parseDouble(waist);
        }
    }


    // Hip
    public double getHip() {
        return hip;
    }

    public void setHip(float hip) {
        this.hip = hip;
    }

    public void setHip(String hip) {
        if (hip.length() == 0) {
            this.hip = 0.0;
        } else {
            this.hip = Double.parseDouble(hip);
        }
    }


    // Inseam
    public double getInseam() {
        return inseam;
    }

    public void setInseam(float inseam) {
        this.inseam = inseam;
    }

    public void setInseam(String inseam) {
        if (inseam.length() == 0) {
            this.inseam = 0.0;
        } else {
            this.inseam = Double.parseDouble(inseam);
        }
    }


    // Comment
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
            this.comment = comment;
    }

    // ID
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        final person other = (person) obj;

        if (other.id.equals(this.id)){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {

        return "Name = " + this.name + ", Date = " + date + ", Id = " + this.id.toString();
    }
}
