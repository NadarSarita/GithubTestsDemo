package com.app.idnbin.Profile.Support.Model;

public class SupportRequestBody {

    String type, text;

    public SupportRequestBody(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public SupportRequestBody() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
