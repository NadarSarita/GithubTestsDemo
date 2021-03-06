package com.app.idnbin.Profile.Settings.NotificationSettings;

public class NotifyData {
    private String user;
    private int icon;
    private String body;
    private String title;
    private String pageview;
    private String sented;

    public NotifyData(String user, int icon, String body, String title, String pageview, String sented) {
        this.user = user;
        this.icon = icon;
        this.body = body;
        this.title = title;
        this.pageview=pageview;
        this.sented = sented;
    }


    public String getPageview() {
        return pageview;
    }

    public void setPageview(String pageview) {
        this.pageview = pageview;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSented() {
        return sented;
    }

    public void setSented(String sented) {
        this.sented = sented;
    }
}
