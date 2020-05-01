package com.app.idnbin.Profile.Support;

public class BotChat {

    private String sender, group_name, receiver;
    private String message, msg_date, msg_time;
    boolean isseen;

    public BotChat(String sender, String receiver, String group_name, String message, String msg_date, String msg_time, boolean isseen) {
        this.sender = sender;
        this.receiver=receiver;
        this.group_name = group_name;
        this.message = message;
        this.msg_date = msg_date;
        this.msg_time = msg_time;
        this.isseen=isseen;
    }

    public BotChat() {
    }

    public boolean isIsseen() {
        return isseen;
    }

    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg_date() {
        return msg_date;
    }

    public void setMsg_date(String msg_date) {
        this.msg_date = msg_date;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }
}