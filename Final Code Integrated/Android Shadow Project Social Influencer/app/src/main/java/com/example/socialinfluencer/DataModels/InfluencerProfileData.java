package com.example.socialinfluencer.DataModels;

import java.util.ArrayList;
import java.util.List;

public class InfluencerProfileData {
    String Influcencer_Description;
    String Influcencer_Gender;
    int Influcencer_Price;
    String Influncer_Name;
    String Influncer_Status;
    Facebook Facebook;
    Instagram Instagram;
    String ProfilePhoto_Link;
    int Total_Earnings;
    Youtube Youtube;
    List<String> Categories;
    String CoverPhoto_Link;



    public InfluencerProfileData() {

    }

    public com.example.socialinfluencer.DataModels.Facebook getFacebook() {
        return Facebook;
    }

    public void setFacebook(com.example.socialinfluencer.DataModels.Facebook facebook) {
        Facebook = facebook;
    }

    public InfluencerProfileData(String influcencer_Description, String influcencer_Gender, int influcencer_Price, String influncer_Name, String influncer_Status, com.example.socialinfluencer.DataModels.Facebook facebook, com.example.socialinfluencer.DataModels.Instagram instagram, String profilePhoto_Link, int total_Earnings, com.example.socialinfluencer.DataModels.Youtube youtube, List<String> categories, String coverPhoto_Link) {
        Influcencer_Description = influcencer_Description;
        Influcencer_Gender = influcencer_Gender;
        Influcencer_Price = influcencer_Price;
        Influncer_Name = influncer_Name;
        Influncer_Status = influncer_Status;
        Facebook = facebook;
        Instagram = instagram;
        ProfilePhoto_Link = profilePhoto_Link;
        Total_Earnings = total_Earnings;
        Youtube = youtube;
        Categories = categories;
        CoverPhoto_Link = coverPhoto_Link;
    }

    public String getInflucencer_Description() {
        return Influcencer_Description;
    }

    public void setInflucencer_Description(String influcencer_Description) {
        Influcencer_Description = influcencer_Description;
    }

    public String getInflucencer_Gender() {
        return Influcencer_Gender;
    }

    public void setInflucencer_Gender(String influcencer_Gender) {
        Influcencer_Gender = influcencer_Gender;
    }

    public int getInflucencer_Price() {
        return Influcencer_Price;
    }

    public void setInflucencer_Price(int influcencer_Price) {
        Influcencer_Price = influcencer_Price;
    }

    public String getInfluncer_Name() {
        return Influncer_Name;
    }

    public void setInfluncer_Name(String influncer_Name) {
        Influncer_Name = influncer_Name;
    }

    public String getInfluncer_Status() {
        return Influncer_Status;
    }

    public void setInfluncer_Status(String influncer_Status) {
        Influncer_Status = influncer_Status;
    }

    public com.example.socialinfluencer.DataModels.Instagram getInstagram() {
        return Instagram;
    }

    public void setInstagram(com.example.socialinfluencer.DataModels.Instagram instagram) {
        Instagram = instagram;
    }

    public String getProfilePhoto_Link() {
        return ProfilePhoto_Link;
    }

    public void setProfilePhoto_Link(String profilePhoto_Link) {
        ProfilePhoto_Link = profilePhoto_Link;
    }

    public int getTotal_Earnings() {
        return Total_Earnings;
    }

    public void setTotal_Earnings(int total_Earnings) {
        Total_Earnings = total_Earnings;
    }

    public com.example.socialinfluencer.DataModels.Youtube getYoutube() {
        return Youtube;
    }

    public void setYoutube(com.example.socialinfluencer.DataModels.Youtube youtube) {
        Youtube = youtube;
    }

    public List<String> getCategories() {
        return Categories;
    }

    public void setCategories(List<String> categories) {
        Categories = categories;
    }

    public String getCoverPhoto_Link() {
        return CoverPhoto_Link;
    }

    public void setCoverPhoto_Link(String coverPhoto_Link) {
        CoverPhoto_Link = coverPhoto_Link;
    }
}
