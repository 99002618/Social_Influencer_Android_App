package com.example.socialinfluencer.DataModels;

import java.util.List;

/*
 "Advertiser_ID" : "T5R9xOfZ5TWdbqNicwTjtdmFQy23",
                "Advertiser_Name" : "iCkae",
                "Budget" : 15000,
                "Campaign_Name" : "Canine Crew",
                "Campaign_image" : "https://firebasestorage.googleapis.com/v0/b/socialinfluencer-10d72.appspot.com/o/Campaigns%2Fpet1.jpg?alt=media&token=d3db9f4c-453d-433a-bce3-436ec4ef74a2",
                "Categories" : [ "Pets" ],
                "Descripton" :"For wagging tails and more",
                "End_Date" : "23-12-2020",
                "Start_Date" : "12-12-2020"
 */
public class Campaign_Data_Model {
    String Campaign_Name;
    String Campaign_image;
    List<String> Categories;
    String Descripton;
    String End_Date;
    String Start_Date;
    String Id;
    long Budget;
    String Advertiser_Name;
    String Advertiser_ID;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Campaign_Data_Model()
    {

    }

    public String getCampaign_Name() {
        return Campaign_Name;
    }

    public void setCampaign_Name(String campaign_Name) {
        Campaign_Name = campaign_Name;
    }

    public String getCampaign_image() {
        return Campaign_image;
    }

    public void setCampaign_image(String campaign_image) {
        Campaign_image = campaign_image;
    }

    public List<String> getCategories() {
        return Categories;
    }

    public void setCategories(List<String> categories) {
        Categories = categories;
    }

    public String getDescripton() {
        return Descripton;
    }

    public void setDescripton(String descripton) {
        Descripton = descripton;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String end_Date) {
        End_Date = end_Date;
    }

    public String getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(String start_Date) {
        Start_Date = start_Date;
    }

    public long getBudget() {
        return Budget;
    }

    public void setBudget(long budget) {
        Budget = budget;
    }

    public String getAdvertiser_Name() {
        return Advertiser_Name;
    }

    public void setAdvertiser_Name(String advertiser_Name) {
        Advertiser_Name = advertiser_Name;
    }

    public String getAdvertiser_ID() {
        return Advertiser_ID;
    }

    public void setAdvertiser_ID(String advertiser_ID) {
        Advertiser_ID = advertiser_ID;
    }
}

