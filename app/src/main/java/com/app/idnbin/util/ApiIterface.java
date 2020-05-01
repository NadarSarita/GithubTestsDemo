package com.app.idnbin.util;


import com.app.idnbin.HomeScreen.QuoteData;
import com.app.idnbin.LoginRegister.ReqMemberBody;
import com.app.idnbin.LoginRegister.ResMemberBody;
import com.app.idnbin.Profile.Settings.NotificationSettings.module.ReqSubBody;
import com.app.idnbin.Profile.Settings.NotificationSettings.module.ReqTagBody;
import com.app.idnbin.Profile.Settings.NotificationSettings.module.ResSubBody;
import com.app.idnbin.Profile.Settings.NotificationSettings.module.ResTagBody;
import com.app.idnbin.Profile.Support.Model.BotReply;
import com.app.idnbin.Profile.Support.Model.SupportRequestBody;
import com.app.idnbin.MarketAnalysis.Newsfeed;
import com.app.idnbin.democall.BidData;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiIterface {

    @GET("u/11158660/rss.xml")
    Call<Newsfeed>getnewsdata();

    @GET("device/rss/rss.html")
    Call<Newsfeed> getearningdata();

    @GET("forex-economic-calendar-events")
    Call<Newsfeed> getforexdata();

    @GET("quotes?pairs=eurusd&api_key=B70qKwu64eVlzwfIwe900xhT2Pn6KvZS")
    Call<ArrayList<BidData>> getBidData();

    @POST("bots/idnbot/converse/1")
    Call<BotReply> getSupportData(@Body SupportRequestBody supportRequestBody);

    @GET("GET_RT_QUOTE?Symbol=EURUSD")
    Observable<QuoteData> getQuoteValue();

    @POST("lists/81fcadcf71/members")
    Call<ResMemberBody> basicLogin(@Body ReqMemberBody reqMemberBody);

    @POST("lists/81fcadcf71/members/af0c49a0bae825e6939d0c9f883f97e9/tags")
    Call<ResTagBody> promotionsApi(@Body ReqTagBody reqTagBody);

    @PUT("lists/81fcadcf71/members/3e1573ee24aebfee5c30caded23b54e8")
    Call<ResSubBody> subscribeMember(@Body ReqSubBody reqSubBody);

}
