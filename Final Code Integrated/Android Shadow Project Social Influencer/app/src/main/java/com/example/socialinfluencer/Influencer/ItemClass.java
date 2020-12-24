package com.example.socialinfluencer.Influencer;

import android.content.Context;

import com.example.socialinfluencer.DataModels.AdvertiserNotification;
import com.example.socialinfluencer.DataModels.InfluencerNotification;

import java.util.ArrayList;

public class ItemClass {

         static final int card = 0;
         static final int card1= 1;

        private int viewType;

//        ArrayList<String> aName;
//        ArrayList<String> aInfluencer;
//
//        ArrayList<String> aPrice;
//        ArrayList<String> aTime;
          InfluencerNotification ntf;
          String Notifi;
    public ItemClass(int viewType, InfluencerNotification ntf,String Notifi) {
        this.viewType = viewType;
        this.ntf = ntf;
        this.Notifi=Notifi;
    }

    Context context;

//        public ItemClass(int viewType, ArrayList<String> cName, ArrayList<String> cInfluencer,  ArrayList<String> cPrice, ArrayList<String> cTime)
//        { this.viewType = viewType;
//            this.aName = cName;
//            this.aInfluencer= cInfluencer;
//            this.aPrice = cPrice;
//            this.aTime = cTime;
//           // this.context = context;
//        }

    public ItemClass(int card, String campaign_acceptance) {
    }


    public static int getCard() {
        return card;
    }

    public static int getCard1() {
        return card1;
    }



    public android.content.Context getContext() {
        return context;
    }

    public void setContext(android.content.Context context) {
        this.context = context;
    }


    public InfluencerNotification getNtf() {
        return ntf;
    }

    public void setNtf(InfluencerNotification ntf) {
        this.ntf = ntf;
    }

    public InfluencerNotification getNdf() {
        return ndf;
    }

    public void setNdf(InfluencerNotification ndf) {
        this.ndf = ndf;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    // Variables for the item of second layout
//        ArrayList<String> bName;
//        ArrayList<String> bInfluencer;
        Context Context;
    InfluencerNotification ndf;
    String Notific;
    int a;

    public String getNotifi() {
        return Notifi;
    }

    public void setNotifi(String notifi) {
        Notifi = notifi;
    }

    public String getNotific() {
        return Notific;
    }

    public void setNotific(String notific) {
        Notific = notific;
    }

    public ItemClass(int viewType, InfluencerNotification ndf, int a, String Notific) {
        this.viewType = viewType;
        this.ndf = ndf;
        this.a = a;
        this.Notific=Notific;
    }

    public int getViewType() { return viewType; }

    public void setViewType(int viewType)
    {
        this.viewType = viewType;
    }
}

