package com.app.idnbin.Chat;

import androidx.annotation.NonNull;

public class Message {
    String message;
    String Email;
    String key;
    String ImageURL;
    String time;

    public Message(String message, String Email,String Imageurl,String time) {
        this.message = message;
        this.Email = Email;
        this.ImageURL = Imageurl;
        this.time = time;

    }

    public Message() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @NonNull
    @Override
    public String toString() {
        return "Forex{" +
                "messgae ='" + message + '\'' +
                ", Email ='" + Email + '\'' +
                ",key ='" + key + '\'' +
                '}';
    }
}
