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

    private Float neck;
    private Float bust;
    private Float chest;
    private Float waist;
    private Float hip;
    private Float inseam;

    private String comment;

    public person(String name, String date, Float neck, Float bust, Float chest,
                  Float waist, Float hip, Float inseam, String comment) {
        this.name = name;
        this.date = date;
        this.neck = neck;
        this.bust = bust;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.inseam = inseam;
        this.comment = comment;
    }

    public person(String name, String date) {
        this.name = name;
        this.date = date;
        this.neck = null;
        this.bust = null;
        this.chest = null;
        this.waist = null;
        this.hip = null;
        this.inseam = null;
        this.comment = null;
    }

    public person() {
        this.name = null;
        this.date = null;
        this.neck = null;
        this.bust = null;
        this.chest = null;
        this.waist = null;
        this.hip = null;
        this.inseam = null;
        this.comment = null;
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
    public float getNeck() {
        return neck;
    }

    public void setNeck(float neck) {
        this.neck = neck;
    }

    public void setNeck(String neck) {
        if (neck.length() == 0) {
            return;
        } else {
            this.neck = Float.valueOf(neck);
        }
    }


    // Bust
    public float getBust() {
        return bust;
    }

    public void setBust(float bust) {
        this.bust = bust;
    }

    public void setBust(String bust) {
        if (bust.length() == 0) {
            return;
        } else {
            this.bust = Float.valueOf(bust);
        }
    }


    // Chest
    public float getChest() {
        return chest;
    }

    public void setChest(float chest) {
        this.chest = chest;
    }

    public void setChest(String chest) {
        if (chest.length() == 0) {
            return;
        } else {
            this.chest = Float.valueOf(chest);
        }
    }


    // Waist
    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public void setWaist(String waist) {
        if (waist.length() == 0) {
            return;
        } else {
            this.waist = Float.valueOf(waist);
        }
    }


    // Hip
    public float getHip() {
        return hip;
    }

    public void setHip(float hip) {
        this.hip = hip;
    }

    public void setHip(String hip) {
        if (hip.length() == 0) {
            return;
        } else {
            this.hip = Float.valueOf(hip);
        }
    }


    // Inseam
    public float getInseam() {
        return inseam;
    }

    public void setInseam(float inseam) {
        this.inseam = inseam;
    }

    public void setInseam(String inseam) {
        if (inseam.length() == 0) {
            return;
        } else {
            this.inseam = Float.valueOf(inseam);
        }
    }


    // Comment
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        if (comment.length() != 0) {
            this.comment = comment;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        final person other = (person) obj;

        if ((other.name.equals(this.name)) && (other.date.equals(this.date))){
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        /*return  " Name = " + name + " Date =  " + date.toString() + " Neck = " + neck.toString()
                + " Bust = " + bust.toString() + " Chest = " + chest.toString() +
                " Waist = " + waist.toString() + " Hip = " + hip.toString() + " Inseam = " + inseam.toString()
                + " Comment = " + comment;*/
        /*
        try {
            return "Name = " + this.name + ", Date = " + date;
        } catch (NullPointerException e){
            return "Name = " + this.name;
        } catch (RuntimeException e){
            return "Name = " + this.name;
        }*/

        return "Name = " + this.name + ", Date = " + date;
    }
}
