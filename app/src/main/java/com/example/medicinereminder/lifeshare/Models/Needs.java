package com.example.medicinereminder.lifeshare.Models;

/**
 * Created by sukrit on 24/2/18.
 */

public class Needs {
    String name,address,number,bloodGroup,hospitalAddress,bloodUnits,uid,datePosted,endDate;

    public Needs() {
    }

    public Needs(String name, String address, String number, String bloodGroup, String hospitalAddress, String bloodUnits, String uid, String datePosted, String endDate)
    {
        this.name = name;
        this.address = address;
        this.number = number;
        this.hospitalAddress = hospitalAddress;
        this.bloodUnits = bloodUnits;
        this.uid = uid;
        this.bloodGroup=bloodGroup;
        this.datePosted=datePosted;
        this.endDate=endDate;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getBloodUnits() {
        return bloodUnits;
    }

    public void setBloodUnits(String bloodUnits) {
        this.bloodUnits = bloodUnits;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
