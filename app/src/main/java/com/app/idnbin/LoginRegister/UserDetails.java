package com.app.idnbin.LoginRegister;

import java.io.Serializable;

public class UserDetails implements Serializable {

    private String id, email, gmail, phone, time, imageURL, securityType, securityCode, alertPrice;

    public UserDetails(String ID, String Email, String Gmail, String Phone, String Time, String imageURL, String securityType, String securityCode, String alertPrice) {
        this.id = ID;
        this.email = Email;
        this.gmail = Gmail;
        this.phone = Phone;
        this.time = Time;
        this.imageURL = imageURL;
        this.securityType = securityType;
        this.securityCode = securityCode;
        this.alertPrice = alertPrice;

    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }


    public UserDetails() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setAlert(String alertPrice) {
        this.alertPrice = alertPrice;
    }

    public String getAlertPrice() {
        return alertPrice;
    }
}
