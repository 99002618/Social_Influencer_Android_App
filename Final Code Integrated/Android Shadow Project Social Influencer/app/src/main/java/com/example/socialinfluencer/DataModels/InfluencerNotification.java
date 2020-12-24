package com.example.socialinfluencer.DataModels;

public class InfluencerNotification {
    String CampaignID;
    String AdvertiserName;
    String NotificaionType;
    String Campaign_Name;
    String From;

    public InfluencerNotification() {

    }

    public InfluencerNotification(String campaignID, String advertiserName, String notificaionType, String campaign_Name, String from) {
        CampaignID = campaignID;
        AdvertiserName = advertiserName;
        NotificaionType = notificaionType;
        Campaign_Name = campaign_Name;
        From = from;
    }

    public String getCampaignID() {
        return CampaignID;
    }

    public void setCampaignID(String campaignID) {
        CampaignID = campaignID;
    }

    public String getAdvertiserName() {
        return AdvertiserName;
    }

    public void setAdvertiserName(String advertiserName) {
        AdvertiserName = advertiserName;
    }

    public String getNotificaionType() {
        return NotificaionType;
    }

    public void setNotificaionType(String notificaionType) {
        NotificaionType = notificaionType;
    }

    public String getCampaign_Name() {
        return Campaign_Name;
    }

    public void setCampaign_Name(String campaign_Name) {
        Campaign_Name = campaign_Name;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }
}
