package com.example.socialinfluencer.DataModels;

public class InfluencerCampaigns {
    String Status;

    public InfluencerCampaigns()
    {

    }
    public InfluencerCampaigns(String status) {
        Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
