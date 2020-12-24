package com.example.socialinfluencer.Advertiser;

import android.content.Context;

import com.example.socialinfluencer.DataModels.AdvertiserNotification;

import java.util.ArrayList;

public class ItemClass {

         static final int card = 0;
         static final int card1= 1;

        private int viewType;

       AdvertiserNotification ntf;
       String notificationID;
        Context context;

//    public ItemClass(int viewType, ArrayList<String> aName, ArrayList<String> aInfluencer, ArrayList<String> aCampaign, ArrayList<String> aPrice, ArrayList<String> aTime, android.content.Context context, ArrayList<String> bName, ArrayList<String> bInfluencer, ArrayList<String> bCampaign, android.content.Context context1) {
//        this.viewType = viewType;
//        this.aName = aName;
//        this.aInfluencer = aInfluencer;
//        this.aCampaign = aCampaign;
//        this.aPrice = aPrice;
//        this.aTime = aTime;
//        this.context = context;
//        this.bName = bName;
//        this.bInfluencer = bInfluencer;
//        this.bCampaign = bCampaign;
//        Context = context1;
//    }


        public ItemClass(int viewType, AdvertiserNotification ntf,String notificationID)
        { this.viewType = viewType;
           this.ntf=ntf;
           this.notificationID=notificationID;
        }

    public static int getCard() {
        return card;
    }

    public static int getCard1() {
        return card1;
    }

    public AdvertiserNotification getNtf() {
        return ntf;
    }

    public void setNtf(AdvertiserNotification ntf) {
        this.ntf = ntf;
    }

    public android.content.Context getContext() {
        return context;
    }

    public void setContext(android.content.Context context) {
        this.context = context;
    }

    public AdvertiserNotification getAdt() {
        return adt;
    }

    public void setAdt(AdvertiserNotification adt) {
        this.adt = adt;
    }


//        public ArrayList<String> getText() { return aName; }
//        public ArrayList<String> getText_1() { return aInfluencer; }
//        public ArrayList<String> getText_2() { return aCampaign; }
//        public ArrayList<String> getText_3() { return aPrice; }
//        public ArrayList<String> getText_4() { return aTime ; }
//
//        public void setText(ArrayList<String> aName) { this.aName = aName; }
//        public void setText_1(ArrayList<String> aInfluencer) { this.aInfluencer= aInfluencer; }
//        public void setText_2(ArrayList<String> aCampaign) {  this.aCampaign = aCampaign;}
//        public void setText_4(ArrayList<String> aPrice) { this.aPrice = aPrice; }
//        public void setText_3(ArrayList<String> aTime ) { this.aTime = aTime;}



    // Variables for the item of second layout
       AdvertiserNotification adt;
        Context Context;
        String Noti;

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getNoti() {
        return Noti;
    }

    public void setNoti(String noti) {
        Noti = noti;
    }

    public ItemClass(int viewType, AdvertiserNotification adt, int a, String noti)
    {this.viewType = viewType;
       this.adt=adt;
       this.Noti=noti;
       // this.Context =Context;
    }


//    public ArrayList<String> gettext() { return bName; }
//    public ArrayList<String> getText_11() { return bInfluencer; }
//    public ArrayList<String> getText_22() { return bCampaign; }
//
//    public void settext(ArrayList<String> bName ) { this.bName = aName; }
//    public void setText_11(ArrayList<String> bInfluencer) { this.bInfluencer= bInfluencer; }
//    public void setText_22(ArrayList<String> bCampaign ) {  this.bCampaign = bCampaign;}

    public int getViewType() { return viewType; }

    public void setViewType(int viewType)
    {
        this.viewType = viewType;
    }
}

