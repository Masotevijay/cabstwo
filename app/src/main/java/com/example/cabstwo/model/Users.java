package com.example.cabstwo.model;

public class Users {

    String userNames, userEmail, cityLiveIn;

    public Users(String userNames, String userEmail, String cityLiveIn) {
        this.userNames = userNames;
        this.userEmail = userEmail;
        this.cityLiveIn = cityLiveIn;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getCityLiveIn() {
        return cityLiveIn;
    }

    public void setCityLiveIn(String cityLiveIn) {
        this.cityLiveIn = cityLiveIn;
    }
}
