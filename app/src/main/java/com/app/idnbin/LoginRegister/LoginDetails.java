package com.app.idnbin.LoginRegister;

public class LoginDetails {

    String logIn,logOut;

    public LoginDetails() {
    }

    public LoginDetails(String logIn, String logOut) {
        this.logIn = logIn;
        this.logOut = logOut;
    }

    public String getLogIn() {
        return logIn;
    }

    public void setLogIn(String logIn) {
        this.logIn = logIn;
    }

    public String getLogOut() {
        return logOut;
    }

    public void setLogOut(String logOut) {
        this.logOut = logOut;
    }
}
