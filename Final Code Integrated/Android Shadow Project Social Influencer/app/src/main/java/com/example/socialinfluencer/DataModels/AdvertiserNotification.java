package com.example.socialinfluencer.DataModels;

public class AdvertiserNotification {
    String CampaignID;
    String InfluencerName;
    String NotificaionType;
    String Campaign_Name;
    String From;
    public AdvertiserNotification()
    {

    }

    public AdvertiserNotification(String campaignID, String influencerName, String notificaionType, String campaign_Name, String from) {
        CampaignID = campaignID;
        InfluencerName = influencerName;
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

    public String getInfluencerName() {
        return InfluencerName;
    }

    public void setInfluencerName(String influencerName) {
        InfluencerName = influencerName;
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
