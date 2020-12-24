package com.example.socialinfluencer.SendNotification;

import com.google.gson.annotations.SerializedName;

public class NotificationSender {
    @SerializedName("notification")
    public Data data;
    @SerializedName("registration_ids")
    public String to;

    public NotificationSender(String to,Data data) {
        this.data = data;
        this.to = to;
    }

    public NotificationSender() {
    }
}
