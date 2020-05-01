package com.app.idnbin.Profile.History.Trading;

public class AssetData {
    String assetName;
    boolean isSelected;

    public AssetData(String assetName, boolean isSelected) {
        this.assetName = assetName;
        this.isSelected = isSelected;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}


