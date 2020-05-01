package com.app.idnbin.Assets.Base;

import java.util.List;
import java.util.List;

public class SymbolsData {
    private String symbolName;
    private List<String> workingPeriod;
    private List<String> multiplier;
    private String imgaeUrl;
    private String description;

    public SymbolsData(String symbolName, List<String> workingPeriod, List<String> multiplier, String imgaeUrl, String description) {
        this.symbolName = symbolName;
        this.workingPeriod = workingPeriod;
        this.multiplier = multiplier;
        this.imgaeUrl = imgaeUrl;
        this.description = description;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public List<String> getWorkingPeriod() {
        return workingPeriod;
    }

    public void setWorkingPeriod(List<String> workingPeriod) {
        this.workingPeriod = workingPeriod;
    }

    public List<String> getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(List<String> multiplier) {
        this.multiplier = multiplier;
    }

    public String getImgaeUrl() {
        return imgaeUrl;
    }

    public void setImgaeUrl(String imgaeUrl) {
        this.imgaeUrl = imgaeUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
