package com.example.medicinereminder.lifeshare.Models;

/**
 * Created by sukrit on 20/2/18.
 */

public class User
{
    String userName;
    String userDonations;

    public User() {
    }

    public User(String userName, String userDonations) {
        this.userName = userName;
        this.userDonations = userDonations;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDonations() {
        return userDonations;
    }

    public void setUserDonations(String userDonations) {
        this.userDonations = userDonations;
    }
}
