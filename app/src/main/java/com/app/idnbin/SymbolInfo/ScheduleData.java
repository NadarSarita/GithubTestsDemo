package com.app.idnbin.SymbolInfo;

public class ScheduleData {
    String scheduleDay,scheduleDate,scheduleTime;

    public ScheduleData(String scheduleDay, String scheduleDate, String scheduleTime) {
        this.scheduleDay = scheduleDay;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
    }

    public String getScheduleDay() {
        return scheduleDay;
    }

    public void setScheduleDay(String scheduleDay) {
        this.scheduleDay = scheduleDay;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }
}
