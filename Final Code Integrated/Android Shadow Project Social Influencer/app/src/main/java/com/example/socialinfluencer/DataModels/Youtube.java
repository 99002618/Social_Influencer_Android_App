package com.example.socialinfluencer.DataModels;

public class Youtube {
    int Impressions;
    int Posts;
    int Subscribers;
    int WatchHours;
    public Youtube() {

    }
    public Youtube(int impressions, int posts, int subscribers, int watchHours) {
        Impressions = impressions;
        Posts = posts;
        Subscribers = subscribers;
        WatchHours = watchHours;
    }

    public int getImpressions() {
        return Impressions;
    }

    public void setImpressions(int impressions) {
        Impressions = impressions;
    }

    public int getPosts() {
        return Posts;
    }

    public void setPosts(int posts) {
        Posts = posts;
    }

    public int getSubscribers() {
        return Subscribers;
    }

    public void setSubscribers(int subscribers) {
        Subscribers = subscribers;
    }

    public int getWatchHours() {
        return WatchHours;
    }

    public void setWatchHours(int watchHours) {
        WatchHours = watchHours;
    }
}
