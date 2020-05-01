package com.app.idnbin.LoginRegister;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MergeFields {
    @SerializedName("FNAME")
    @Expose
    private String fNAME;
    @SerializedName("LNAME")
    @Expose
    private String lNAME;

    /**
     * No args constructor for use in serialization
     *
     */
    public MergeFields() {
    }

    /**
     *
     * @param fNAME
     * @param lNAME
     */
    public MergeFields(String fNAME, String lNAME) {
        super();
        this.fNAME = fNAME;
        this.lNAME = lNAME;
    }

    public String getFNAME() {
        return fNAME;
    }

    public void setFNAME(String fNAME) {
        this.fNAME = fNAME;
    }

    public String getLNAME() {
        return lNAME;
    }

    public void setLNAME(String lNAME) {
        this.lNAME = lNAME;
    }

}

