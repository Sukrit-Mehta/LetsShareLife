package com.example.medicinereminder.lifeshare.Models;

/**
 * Created by sukrit on 20/2/18.
 */

public class RequiredModel {

    String userName;
    String userAddress;
    String userHospital;
    String userContactNumber;
    String userBloodGroup;
    String userEmailAddress;

    public RequiredModel() {
    }

    public RequiredModel(String userName, String userAddress, String userHospital, String userContactNumber, String userBloodGroup, String userEmailAddress) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.userHospital = userHospital;
        this.userContactNumber = userContactNumber;
        this.userBloodGroup = userBloodGroup;
        this.userEmailAddress = userEmailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserHospital() {
        return userHospital;
    }

    public void setUserHospital(String userHospital) {
        this.userHospital = userHospital;
    }

    public String getUserContactNumber() {
        return userContactNumber;
    }

    public void setUserContactNumber(String userContactNumber) {
        this.userContactNumber = userContactNumber;
    }

    public String getUserBloodGroup() {
        return userBloodGroup;
    }

    public void setUserBloodGroup(String userBloodGroup) {
        this.userBloodGroup = userBloodGroup;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }
}
