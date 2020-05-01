package com.app.idnbin.Profile.Support.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BotResponse {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private Boolean value;
    @SerializedName("text")
    @Expose
    private String text;

    /**
     * No args constructor for use in serialization
     *
     */
    public BotResponse() {
    }

    /**
     *
     * @param text
     * @param type
     * @param value
     */
    public BotResponse(String type, Boolean value, String text) {
        super();
        this.type = type;
        this.value = value;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}