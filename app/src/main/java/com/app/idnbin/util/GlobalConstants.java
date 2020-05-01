package com.app.idnbin.util;

import com.app.idnbin.Customize.CustomizeSymbol;
import com.app.idnbin.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

public class GlobalConstants {

    public String something = "something";
    public static ArrayList<CustomizeSymbol> customizeArrayList= new ArrayList<CustomizeSymbol>(Arrays.asList(new CustomizeSymbol("Portfolio", R.drawable.ic_portfolio_selector, 1),new CustomizeSymbol("Market Analysis",R.drawable.ic_marketanalysis_selector,1), new CustomizeSymbol( "Price Movements",R.drawable.ic_alert_selector,1),new CustomizeSymbol( "Video Tutorial",R.drawable.ic_tutorial_selector,1),new CustomizeSymbol( "Chats",R.drawable.ic_chat_selector,1)));
    public static final DatabaseReference UsersInstance = FirebaseDatabase.getInstance().getReference("Android/UserDetails");
    public static final DatabaseReference DeviceDetailsInstance = FirebaseDatabase.getInstance().getReference("Android/DeviceDetails");
    public static final DatabaseReference LoginDetailsInstance = FirebaseDatabase.getInstance().getReference("Android/LoginDetails");
    public static final DatabaseReference ChatsInstance=FirebaseDatabase.getInstance().getReference("Android/Chats");
    public static final DatabaseReference TokensInstance = FirebaseDatabase.getInstance().getReference("Android/Tokens");
    public static final DatabaseReference NotificationSettingsInstance = FirebaseDatabase.getInstance().getReference("Android/NotificationSettings");

}

