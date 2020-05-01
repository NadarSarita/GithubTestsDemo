package com.app.idnbin.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class ApiCall {

    public static Retrofit forexnew = null;
    public static Retrofit retroforex = null;
    public static Retrofit retroearning = null;

    public static final String BASE_API = "https://api.1forge.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getApiData() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }


    public static Retrofit getForexnew() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        if (forexnew == null) {
            forexnew = new Retrofit.Builder().baseUrl("http://www.rssmix.com/").addConverterFactory(SimpleXmlConverterFactory.create()).client(client).build();
        }
        return forexnew;
    }

    public static Retrofit getearning() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();

        if (retroearning == null) {
            retroearning = new Retrofit.Builder().baseUrl("https://www.cnbc.com/id/15839135/").addConverterFactory(SimpleXmlConverterFactory.create()).client(client).build();
        }
        return retroearning;
    }

    public static Retrofit getforex() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS).build();
        if (retroforex == null) {
            retroforex = new Retrofit.Builder().baseUrl("https://www.myfxbook.com/rss/").addConverterFactory(SimpleXmlConverterFactory.create()).client(client).build();
        }
        return retroforex;
    }

    public static Retrofit getSupportRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();
        if (forexnew == null) {
            // forexnew = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/api/v1/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
//            forexnew = new Retrofit.Builder().baseUrl("http://192.168.56.1:3000/api/v1/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
            forexnew = new Retrofit.Builder().baseUrl("http://192.168.1.104:3000/api/v1/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        return forexnew;
    }

    public static Retrofit getChartDataApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();

        Gson gson=new GsonBuilder().setLenient().create();
        if (forexnew == null) {

            forexnew = new Retrofit.Builder().baseUrl("http://103.5.45.221:9011/QuoteAPI/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
        }
        return forexnew;
    }

public static Retrofit subscriberApi() {

       HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .addInterceptor(new BasicAuthInterceptor("anystring", "b9bda1e57806247299443031abc8e73e-us19"))
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();

        Gson gson=new GsonBuilder().setLenient().create();
        if (forexnew == null) {

            forexnew = new Retrofit.Builder().baseUrl("https://us19.api.mailchimp.com/3.0/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create(gson)).client(client).build();
        }
        return forexnew;
    }

}
