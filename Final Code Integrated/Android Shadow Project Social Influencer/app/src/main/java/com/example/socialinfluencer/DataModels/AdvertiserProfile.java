package com.example.socialinfluencer.DataModels;

import java.util.List;

public class AdvertiserProfile {
    String BrandName;
    List<String> Categories;
    String CoverPhotoLink;
    String Description;
    String ProfilePhotoLink;
    int Total_Campaigns;


    public AdvertiserProfile() {

    }

    public AdvertiserProfile(String brandName, List<String> categories, String coverPhotoLink, String description, String profilePhotoLink, int total_Campaigns) {
        BrandName = brandName;
        Categories = categories;
        CoverPhotoLink = coverPhotoLink;
        Description = description;
        ProfilePhotoLink = profilePhotoLink;
        Total_Campaigns = total_Campaigns;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public List<String> getCategories() {
        return Categories;
    }

    public void setCategories(List<String> categories) {
        Categories = categories;
    }

    public String getCoverPhotoLink() {
        return CoverPhotoLink;
    }

    public void setCoverPhotoLink(String coverPhotoLink) {
        CoverPhotoLink = coverPhotoLink;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getProfilePhotoLink() {
        return ProfilePhotoLink;
    }

    public void setProfilePhotoLink(String profilePhotoLink) {
        ProfilePhotoLink = profilePhotoLink;
    }

    public int getTotal_Campaigns() {
        return Total_Campaigns;
    }

    public void setTotal_Campaigns(int total_Campaigns) {
        Total_Campaigns = total_Campaigns;
    }
}
