package com.example.socialinfluencer.DataModels;

public class Facebook {
    String Engagement;
    int Followers;
    int Following;
    int Posts;

    public Facebook() {

    }
    public Facebook(String engagement, int followers, int following, int posts) {
        Engagement = engagement;
        Followers = followers;
        Following = following;
        Posts = posts;
    }

    public String getEngagement() {
        return Engagement;
    }

    public void setEngagement(String engagement) {
        Engagement = engagement;
    }

    public int getFollowers() {
        return Followers;
    }

    public void setFollowers(int followers) {
        Followers = followers;
    }

    public int getFollowing() {
        return Following;
    }

    public void setFollowing(int following) {
        Following = following;
    }

    public int getPosts() {
        return Posts;
    }

    public void setPosts(int posts) {
        Posts = posts;
    }
}
