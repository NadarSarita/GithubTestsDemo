package com.app.idnbin.Profile.Settings.NotificationSettings;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private static Retrofit retro = null;

    public static Retrofit getClient(String url){
        if (retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
