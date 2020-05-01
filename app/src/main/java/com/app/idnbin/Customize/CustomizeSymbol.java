package com.app.idnbin.Customize;

public class CustomizeSymbol {
    String customizeName;
    int customizeDrawable;
    int customizeView;

    public CustomizeSymbol(String customizeName, int customizeDrawable,int customizeView) {
        this.customizeName = customizeName;
        this.customizeDrawable = customizeDrawable;
        this.customizeView= customizeView;
    }

    public void setCustomizeDrawable(int customizeDrawable) {
        this.customizeDrawable = customizeDrawable;
    }

    public int getCustomizeView() {
        return customizeView;
    }

    public void setCustomizeView(int customizeView) {
        this.customizeView = customizeView;
    }

    public String getCustomizeName() {
        return customizeName;
    }

    public void setCustomizeName(String customizeName) {
        this.customizeName = customizeName;
    }

    public int getCustomizeDrawable() {
        return customizeDrawable;
    }
}
