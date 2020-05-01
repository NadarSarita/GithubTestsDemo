package com.app.idnbin.HomeScreen;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.Assets.SymbolListActivity;
import com.app.idnbin.BlankFragment;
import com.app.idnbin.Chat.ChatFragment;
import com.app.idnbin.Customize.CustomizeFragment;
import com.app.idnbin.Customize.CustomizeSymbol;
import com.app.idnbin.MarketAnalysis.MarketAnalysisFragment;
import com.app.idnbin.MovesAlerts.MovesAlertsFragment;
import com.app.idnbin.Portfolio.PortfolioFragment;
import com.app.idnbin.Indicator.IndiActivity;
import com.app.idnbin.Profile.Deposit.DepositActivity;
import com.app.idnbin.Profile.ProfileFragment;
import com.app.idnbin.Profile.Withdraw.WithdrawActivity;
import com.app.idnbin.Tutorial.TutorialFragment;
import com.app.idnbin.R;
import com.app.idnbin.SymbolInfo.SymbolInfoFragment;
import com.app.idnbin.util.ApiCall;
import com.app.idnbin.util.ApiIterface;
import com.app.idnbin.util.BaseActivity;
import com.fintatech.TradingChartDesigner.core.TradingChartDesignerView;
import com.fintatech.TradingChartDesigner.core.plots.TCDPlot;
import com.fintatech.TradingChartDesigner.dataseries.TCDDataSeries;
import com.fintatech.enums.PriceStyle;
import com.fintatech.pricetyles.ChartManager;
import com.fintatech.pricetyles.ChartManagerFactory;
import com.fintatech.tasdk.misc.TABar;
import com.fintatech.tasdk.misc.TAPeriodicityEnum;
import com.fintatech.tasdk.misc.TATimeFrame;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.idnbin.util.GlobalConstants.customizeArrayList;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "HomeActivity";
    @BindView(R.id.RLCustomize)
    RelativeLayout RLCustomize;
    private ImageView IvTab1, IvTab2, IvTab5, IvMenu, IvTab3, IvTab4, IvTab6, IvGraphTools, IvInfo, IvAddSymbol, IvAlert, IvGraph, img1,
            img2, img3, img4;
    private RelativeLayout RLFrames, RLNavigation, RLBalance, RLLeverage, RLPrice, RLTotal, RLProfit, RLBuy, RLProfitCall,
            RLSpread, RLSell, RLProfitPut, RLOperations;
    private TextView TvDeposit, TvInvestValue, TV_buyclose, TV_buyheading, TV_buyheading1, TV_digitaldowndirection, TV_digitalupdirection, TV_putclosed,
            txt_5_sec, txt_10_sec, txt_15_sec, txt_30_sec, txt_1_min, TvTimeDate, TvLeverage, TV_multipiler, TV_1m, TV_1d, TV_3h, TV_1h, TV_30m, TV_15m, TV_1mm, TvConfirm;
    Button BtnLogin, btnoff, btnspot, btnclosest;

    Switch switch1, switch2;
    ImageView imgBlack, imgColored;
    Group contrast_group;
    String timeGapType="Seconds";
    int timeGapInt=5;
    Disposable disposable;
    ApiIterface apiIterface;

    RecyclerView time_view;
    String[] data = {"0.6150124100", "291.6465%",};
    private List<String> list = new ArrayList<String>(Arrays.asList(data));

    //AlertDialog balancedialog;
    AlertDialog signin;
    LinearLayout LLDigital, LLDigitalVisible, LLDigitalClose, LLForex, LLForexVisible, LLForexClose, LLStocks, LLStocksVisible, LLStocksClose,
            LLETF, LLETFVisible, LLEtfClose, LLComm, LLCommVisible, LLCommClose, LLCrypto, LLCryptoVisible, LLCryptoClose, LLOperations, RLInvest, RLTime, RLStrikePrice, llbuylayout, lldigitalcalllayout;
    TextView Tv_DigitalSymbol, Tv_ForexSymbol, Tv_StocksSymbol, Tv_EtfSymbol, Tv_CommSymbol, Tv_CryptoSymbol, TVAlertRepeatValue, TVAlertPriceValue,
            TVCurrentPriceValue;

    CardView CVDigital, CVForex, CVStocks, CVETF, CVComm, CVCrypto;

    AlertDialog balancedialog, realaccedialog;
    TextView Tvclosed;

    int currentValue1 = 1;
    int currentValueNew = 0;
    boolean status = false;

    private TradingChartDesignerView chartView = null;
    private ChartManager chartManager = null;
    Map<String, TCDDataSeries> dataSeriesMap;
    String seriesType = "MOUNTAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        TvDeposit = findViewById(R.id.TvDeposit);
        IvTab1 = findViewById(R.id.IvTab1);
        IvTab2 = findViewById(R.id.IvTab2);
        IvTab5 = findViewById(R.id.IvTab5);
        IvMenu = findViewById(R.id.IvMenu);
        IvTab3 = findViewById(R.id.IvTab3);
        IvTab4 = findViewById(R.id.IvTab4);
        IvTab6 = findViewById(R.id.IvTab6);
        IvAddSymbol = findViewById(R.id.IvAddSymbol);
        IvInfo = findViewById(R.id.IvInfo);
        RLFrames = findViewById(R.id.RLFrames);
        RLNavigation = findViewById(R.id.RLNavigation);
        RLBalance = findViewById(R.id.RLBalance);
        RLInvest = findViewById(R.id.RLInvest);
        TvInvestValue = findViewById(R.id.TvInvestValue);
        RLLeverage = findViewById(R.id.RLLeverage);
        RLPrice = findViewById(R.id.RLPrice);
        RLTime = findViewById(R.id.RLTime);
        RLStrikePrice = findViewById(R.id.RLStrikePrice);
        RLTotal = findViewById(R.id.RLTotal);
        RLProfit = findViewById(R.id.RLProfit);
        RLBuy = findViewById(R.id.RLBuy);
        RLProfitCall = findViewById(R.id.RLProfitCall);
        RLSpread = findViewById(R.id.RLSpread);
        RLSell = findViewById(R.id.RLSell);
        RLProfitPut = findViewById(R.id.RLProfitPut);
        RLOperations = findViewById(R.id.RLOperations);
        IvAlert = findViewById(R.id.IvAlert);
        IvGraph = findViewById(R.id.IvGraph);
        IvGraphTools = findViewById(R.id.IvGraphTools);
        TvTimeDate = findViewById(R.id.TvTimeDate);
        TV_multipiler = findViewById(R.id.TV_multipiler);


        CVDigital = findViewById(R.id.CVDigital);
        LLDigitalVisible = findViewById(R.id.LLDigitalVisible);
        LLDigitalClose = findViewById(R.id.LLDigitalClose);
        Tv_DigitalSymbol = findViewById(R.id.Tv_DigitalSymbol);
        CVForex = findViewById(R.id.CVForex);
        LLForexVisible = findViewById(R.id.LLForexVisible);
        LLForexClose = findViewById(R.id.LLForexClose);
        Tv_ForexSymbol = findViewById(R.id.Tv_ForexSymbol);
        CVStocks = findViewById(R.id.CVStocks);
        LLStocksVisible = findViewById(R.id.LLStocksVisible);
        LLStocksClose = findViewById(R.id.LLStocksClose);
        Tv_StocksSymbol = findViewById(R.id.Tv_StocksSymbol);
        CVETF = findViewById(R.id.CVETF);
        LLETFVisible = findViewById(R.id.LLETFVisible);
        LLEtfClose = findViewById(R.id.LLEtfClose);
        Tv_EtfSymbol = findViewById(R.id.Tv_EtfSymbol);
        CVComm = findViewById(R.id.CVComm);
        LLCommVisible = findViewById(R.id.LLCommVisible);
        LLCommClose = findViewById(R.id.LLCommClose);
        Tv_CommSymbol = findViewById(R.id.Tv_CommSymbol);
        CVCrypto = findViewById(R.id.CVCrypto);
        LLCryptoVisible = findViewById(R.id.LLCryptoVisible);
        LLCryptoClose = findViewById(R.id.LLCryptoClose);
        LLOperations = findViewById(R.id.LLOperations);
        Tv_CryptoSymbol = findViewById(R.id.Tv_CryptoSymbol);
        TvDeposit = findViewById(R.id.TvDeposit);
        TV_buyclose = findViewById(R.id.TV_buyclose);
        llbuylayout = findViewById(R.id.llbuylayout);
        lldigitalcalllayout = findViewById(R.id.lldigitalcalllayout);
        TVAlertRepeatValue = findViewById(R.id.TVAlertRepeatValue);
        TVAlertPriceValue = findViewById(R.id.TVAlertPriceValue);
        TVCurrentPriceValue = findViewById(R.id.TVCurrentPriceValue);

        RLStrikePrice = findViewById(R.id.RLStrikePrice);
        RLBuy = findViewById(R.id.RLBuy);
        RLProfitCall = findViewById(R.id.RLProfitCall);
        RLSell = findViewById(R.id.RLSell);
        RLProfitPut = findViewById(R.id.RLProfitPut);
        RLSpread = findViewById(R.id.RLSpread);
        TvConfirm = findViewById(R.id.TvConfirm);
        TV_buyheading = findViewById(R.id.TV_buyheading);
        TV_buyheading1 = findViewById(R.id.TV_buyheading1);
        TV_digitaldowndirection = findViewById(R.id.TV_digitaldowndirection);
        TV_digitalupdirection = findViewById(R.id.TV_digitalupdirection);
        TV_putclosed = findViewById(R.id.TV_putclosed);
        TvLeverage = findViewById(R.id.TvLeverage);
        TV_1m = findViewById(R.id.TV_1m);
        TV_1d = findViewById(R.id.TV_1d);
        TV_1mm = findViewById(R.id.TV_1mm);
        TV_3h = findViewById(R.id.TV_3h);
        TV_1h = findViewById(R.id.TV_1h);
        TV_30m = findViewById(R.id.TV_30m);
        TV_15m = findViewById(R.id.TV_15m);

        Intent intent = getIntent();
        String info_bundle = intent.getStringExtra("info");
        String alert_bundle = intent.getStringExtra("alert");

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        assert info_bundle != null;
        if (info_bundle == null) {
            fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
            clearSelectedTagImg();
        } else {
            fragmentTransaction.replace(R.id.FragmentHolder, new SymbolInfoFragment());
            RLFrames.setVisibility(View.VISIBLE);
            highLightSelectedTagImg(7);
        }
        if (alert_bundle != null) {
            LLOperations.setVisibility(View.GONE);
            RLOperations.setVisibility(View.VISIBLE);
        }
        fragmentTransaction.commit();

         if (!getPref(this, "tabList").equalsIgnoreCase("null")) {
            customizeArrayList = new Gson().fromJson(getPref(this, "tabList"), new TypeToken<ArrayList<CustomizeSymbol>>() {
            }.getType());
        }

        if (getPref(this, "firstinstall").equalsIgnoreCase("null")) {
            setPref(this, "firstinstall", "first");

            setPref(this, "Switch_ExpirationPanel", "0");
            setPref(this, "Switch_TraderSentiments", "0");
            setPref(this, "Switch_LiveDeals", "0");
            setPref(this, "Switch_SharemyLiveDeals", "0");
            setPref(this, "Switch_ClosedDealsonChartOptions", "0");
            setPref(this, "Switch_ClosedDealsonChartFCC", "0");
            setPref(this, "Switch_Sound", "0");
            setPref(this, "Switch_InvestmentAmount", "0");
            setPref(this, "Switch_ShowHighLowonChart", "0");
            setPref(this, "Switch_BuyinOneClickOptions", "0");
            setPref(this, "Switch_BuywithconfirmationForex", "0");
            setPref(this, "Switch_UseBalancetokeepposition", "0");
            setPref(this, "Switch_trailingStop", "0");
            setPref(this, "Switch_ShwoNotificationaboutexecution", "0");
            setPref(this, "Switch_ShowPriceMovements", "0");

            setPref(this, "switch_promo", "0");
            setPref(this, "switch_tournament", "0");
            setPref(this, "switch_system_news", "0");
            setPref(this, "switch_analytical_rpts", "0");

            setPref(this, "switch_communication", "0");

            setPref(this, "switch_closed_trade", "0");
            setPref(this, "switch_succ_withdraw", "0");
            setPref(this, "switch_pending_order", "0");
            setPref(this, "switch_margin_trdng", "0");
            setPref(this, "switch_market_news", "0");
            setPref(this, "switch_sharp_jump", "0");
            setPref(this, "switch_rise_fall", "0");
            setPref(this, "switch_support", "0");
            setPref(this, "switch_price_alerts", "0");
        }

        chartView = findViewById(R.id.chart_view);

   /*     Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getChartDataFromApi();
                handler.postDelayed(this, 1000);
                changePriceStyle(seriesType);
            }
        }, 1000);*/

/*        apiIterface = ApiCall.getChartDataApi().create(ApiIterface.class);
        disposable = Observable.interval(1000, 5000,
                TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::callChartPoints, this::onError);*/

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // update TextView here!
                                updateTime();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        thread.start();
    }

/*
    public void getChartDataFromApi() {

        Call<QuoteData> call = apiIterface.getQuoteValue();
        call.enqueue(new Callback<QuoteData>() {
            @Override
            public void onResponse(@NonNull Call<QuoteData> call, @NonNull Response<QuoteData> response) {
                if (response.code() == 200) {
                    Log.i("TAG", "if code onResponse: " + response.code());
                    if (response.body() != null) {
                        QuoteData quoteData = response.body();
                        Log.i("TAG", "inside body onResponse: " + quoteData.getSymbol() + " " + quoteData.getTimestamp());
//                        supportChatAdapter.notifyDataSetChanged();
                        getAverageValue(quoteData);

                    }
                } else {
                    Log.i("TAG", "else code onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(@NonNull Call<QuoteData> call, @NonNull Throwable t) {
                Log.d("TAG", "failure onResponse" + t.getMessage());

            }
        });

    }
*/

    private void onError(Throwable throwable) {
        Log.i(TAG, "onError: "+throwable.getMessage());
    }

    private void callChartPoints(Long aLong) {
        Observable<QuoteData> observable = apiIterface.getQuoteValue();
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .map(result -> result)
                .subscribe(this::handleResults, this::handleError);
    }

    private void handleError(Throwable throwable) {
        Log.i(TAG, "handleError: "+throwable.getMessage());
    }

    private void handleResults(QuoteData quoteData) {
        Log.i("TAG", "inside body onResponse: " + quoteData.getSymbol() + " " + quoteData.getTimestamp());
//                        supportChatAdapter.notifyDataSetChanged();
        getAverageValue(quoteData);
        changePriceStyle(seriesType);

    }

    @Override
    protected void onResume() {
        super.onResume();

     /*   if (disposable.isDisposed()) {
            disposable = Observable.interval(1000, 5000,
                    TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::callChartPoints, this::onError);
        }*/
    }


    @Override
    protected void onPause() {
        super.onPause();

     //   disposable.dispose();
    }

    private List<List<String>> readCSVFile() {
        List<List<String>> resultList = new ArrayList();
        try {
            FileInputStream fileIn = openFileInput("QuoteEur.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileIn, Charset.forName("UTF-8")));

            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                ArrayList<String> rowList = new ArrayList<>();
                for (int i = 0; i < row.length; i++) {
                    rowList.add(i, row[i]);
                }
                resultList.add(rowList);
            }
            reader.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private static List<TABar> getListBars(List<List<String>> list) {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        ArrayList<TABar> taBars = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) { // Reverse data for AAPL symbol
            List<String> strings = list.get(i);
            TABar taBar = new TABar(
                    Float.valueOf(strings.get(1)),
                    Float.valueOf(strings.get(2)),
                    Float.valueOf(strings.get(3)),
                    Float.valueOf(strings.get(4)),
                    Long.valueOf(strings.get(5)));
            DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
            taBar.setDate(DateTime.parse(strings.get(0), fmt).toLocalDateTime().toDateTime());
            taBars.add(taBar);
        }
        return taBars;
    }

    Float open = 0f, high = 0f, low = 0f, close = 0f, priceV = 0f;
    String startTimestamp,timeStamp;

    private void getAverageValue(QuoteData symbol) {
        priceV = getAveragePrice(symbol);
        if (startTimestamp == null) {
            startTimestamp = symbol.getTimestamp();
        }
        timeStamp=symbol.getTimestamp();
            if (priceV > high) {
                high = priceV;
            }
            else {
                low=priceV;
            }

            if (getFirstTimeDifference(startTimestamp, timeStamp)) {
                close=priceV;
            }

            if (getTimeDifference(startTimestamp, timeStamp)) {
                open = close;
            }

        updateCSVFile(high, low, open, close, timeStamp);
    }

    private Float getAveragePrice(QuoteData symbol) {
        Float fvalue = (float) ((symbol.getAsk() + symbol.getAsk()) / 2);
        Log.i(TAG, "getAveragePrice: fvalue "+fvalue);
        return fvalue;
    }

    private boolean getFirstTimeDifference(String startTimestamp, String timestamp) {
        boolean bool = false;
        Log.i(TAG, "getTimeDifference: NotifyData" + startTimestamp + "==>" + timestamp);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date date1 = df.parse(startTimestamp);
            Date date2 = df.parse(timestamp);

            long difference = date2.getTime() - date1.getTime();
            Log.i(TAG, "getTimeDifference: diff " + difference);
            int hours = (int) difference / (1000 * 60 * 60);
            int mins = (int) (difference / (1000 * 60)) % 60;
            int sec = (int) (difference / 1000) % 60;

            Log.i(TAG, "getTimeDifference: hours " + hours);
            Log.i(TAG, "getTimeDifference: min " + mins);
            Log.i(TAG, "getTimeDifference: sec " + sec);

            if(sec!=0)
            {
                if (sec%4==0) {
                    bool = true;
                    Log.i(TAG, "getTimeDifference: 5sec");
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bool;
    }

    private boolean getTimeDifference(String startTimestamp, String timestamp) {
        boolean bool = false;
        Log.i(TAG, "getTimeDifference: NotifyData " + startTimestamp + "==>" + timestamp);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date date1 = df.parse("2019-11-12 09:31:20.889");
            Date date2 = df.parse(timestamp);
            long year_difference=date2.getYear()-date1.getYear();
            long month_difference=date2.getMonth()-date1.getMonth();
            long date_difference=date2.getDate()-date1.getDate();
            long hours=date2.getHours()-date1.getHours();
            long mins=date2.getMinutes()-date1.getMinutes();
            long sec=date2.getSeconds()-date1.getSeconds();

            if(timeGapType.equalsIgnoreCase("Year"))
            {
                if(year_difference%timeGapInt==0){
                    bool = true;
                    Log.i(TAG, "getTimeDifference: 1 year");
                }
            }
           else if(timeGapType.equalsIgnoreCase("Month"))
            {
                if(month_difference%timeGapInt==0)
                {
                    bool = true;
                    Log.i(TAG, "getTimeDifference: 1 month");
                }
            }
         else if(timeGapType.equalsIgnoreCase("Week"))
          {
              if(date_difference%timeGapInt==0)
              {
                  bool = true;
                  Log.i(TAG, "getTimeDifference: 1 week");
              }
          }
          else   if(timeGapType.equalsIgnoreCase("Day"))
            {
                if(date_difference%timeGapInt==0)
                {
                    bool = true;
                    Log.i(TAG, "getTimeDifference: 1 day");
                }
            }

          else if(timeGapType.equalsIgnoreCase("Seconds"))
          {
              if(sec%timeGapInt==0)
              {
                  bool = true;
                  Log.i(TAG, "getTimeDifference: "+timeGapInt+ " sec");
              }
          }
          else if(timeGapType.equalsIgnoreCase("Minute"))
          {
              if(mins%timeGapInt==0)
              {
                  bool=true;
                  Log.i(TAG, "getTimeDifference: "+timeGapInt+" min");
              }
          }
          else if(timeGapType.equalsIgnoreCase("Hour"))
          {
              if(hours%timeGapInt==0)
              {
                  bool=true;
                  Log.i(TAG, "getTimeDifference: "+timeGapInt+" hours");
              }
          }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bool;
    }

    private void updateCSVFile(Float high, Float low, Float open, Float close, String timestamp) {

        try {
            FileOutputStream fileout = openFileOutput("QuoteEur.csv", Context.MODE_APPEND);
            BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(fileout));
            outputWriter.write(timestamp + "," + open + "," + high + "," + low + "," + close + "," + 0);
            outputWriter.newLine();
            outputWriter.close();

            //display file saved message

            readCSVFile();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        TvTimeDate.setText(currentDateandTime);
    }

    @OnClick({R.id.IvAlert, R.id.IvTab1, R.id.IvTab2, R.id.IvTab5, R.id.IvTab3, R.id.IvTab4, R.id.IvTab6, R.id.IvAddSymbol, R.id.IvMenu, R.id.IvInfo, R.id.RLBalance, R.id.TvDeposit, R.id.RLInvest, R.id.RLLeverage, R.id.RLPrice, R.id.CVDigital, R.id.CVForex, R.id.CVStocks, R.id.CVETF, R.id.CVComm, R.id.CVCrypto, R.id.IvGraph, R.id.IvGraphTools, R.id.TV_buyclose, R.id.TV_putclosed, R.id.RLTime, R.id.TV_1m, R.id.TV_1d, R.id.TV_3h, R.id.TV_1h, R.id.TV_30m, R.id.TV_15m, R.id.TV_1mm, R.id.TVAlertRepeatValue, R.id.TVAlertPriceValue})
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        switch (v.getId()) {
            case R.id.IvAlert:
                setAlertVisibility();
                break;
            case R.id.TVAlertRepeatValue:
                if (status) {
                    TVAlertRepeatValue.setText("ALWAYS");
                    status = false;
                } else {
                    TVAlertRepeatValue.setText("ONCE");
                    status = true;
                }
                break;

            case R.id.TVAlertPriceValue:
                AlertDialog alertprice = new AlertDialog.Builder(this).create();
                alertprice.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertprice.setView(getLayoutInflater().inflate(R.layout.layout_repeatprice, null));
                alertprice.show();
                EditText EtAlertAmount = alertprice.findViewById(R.id.EtAlertAmount);
                Button BtnPriceminus = alertprice.findViewById(R.id.BtnPriceminus);
                Button BtnPriceinto = alertprice.findViewById(R.id.BtnPriceinto);
                Button BtnPriceplus = alertprice.findViewById(R.id.BtnPriceplus);
                TextView TV_currentvalue = alertprice.findViewById(R.id.TV_currentvalue);
                TextView TV_icclosed = alertprice.findViewById(R.id.TV_icclosed);
                Button Btnconfirm = alertprice.findViewById(R.id.Btnconfirm);

                TV_icclosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertprice.dismiss();
                    }
                });

                BtnPriceplus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        plusValue();
                        EtAlertAmount.setText("0.1" + currentValue1);
                        BtnPriceinto.setVisibility(View.VISIBLE);
                    }
                });

                BtnPriceminus.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        minValue();
                        EtAlertAmount.setText("0.1" + currentValue1);
                        BtnPriceinto.setVisibility(View.VISIBLE);
                    }
                });

                BtnPriceinto.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (TV_currentvalue.getVisibility() == View.VISIBLE) {
                            TV_currentvalue.setVisibility(View.GONE);
                            BtnPriceinto.setVisibility(View.GONE);
                        } else {
                            TV_currentvalue.setVisibility(View.VISIBLE);
                            BtnPriceinto.setVisibility(View.GONE);
                        }

                        TVCurrentPriceValue.setText(EtAlertAmount.getText());
                    }
                });
                Btnconfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TVAlertPriceValue.setText(EtAlertAmount.getText());
                    }
                });

                break;
            case R.id.TV_1m:
                TV_1m.setTextColor(Color.WHITE);
                TV_1d.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.GRAY);
                TV_1mm.setTextColor(Color.GRAY);
                timeGapType="Month";
                timeGapInt=1;
                setDateScale("Month");
                break;
            case R.id.TV_1d:
                TV_1d.setTextColor(Color.WHITE);
                TV_1m.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.GRAY);
                TV_1mm.setTextColor(Color.GRAY);
                timeGapType="Day";
                timeGapInt=1;
                setDateScale("Day");
                break;
            case R.id.TV_3h:
                TV_3h.setTextColor(Color.WHITE);
                TV_1m.setTextColor(Color.GRAY);
                TV_1d.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.GRAY);
                TV_1mm.setTextColor(Color.GRAY);
                timeGapType="Hour";
                timeGapInt=3;
                setDateScale("Hour");
                break;
            case R.id.TV_1h:
                TV_1mm.setTextColor(Color.GRAY);
                TV_1m.setTextColor(Color.GRAY);
                TV_1d.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.WHITE);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.GRAY);
                timeGapType="Hour";
                timeGapInt=1;
                setDateScale("Hour");
                break;
            case R.id.TV_30m:
                TV_1mm.setTextColor(Color.GRAY);
                TV_1m.setTextColor(Color.GRAY);
                TV_1d.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.WHITE);
                TV_15m.setTextColor(Color.GRAY);
                timeGapType="Minute";
                timeGapInt=30;
                setDateScale("Minute");
                break;
            case R.id.TV_15m:
                TV_1mm.setTextColor(Color.GRAY);
                TV_1m.setTextColor(Color.GRAY);
                TV_1d.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.WHITE);
                timeGapType="Minute";
                timeGapInt=15;
                setDateScale("Minute");
                break;
            case R.id.TV_1mm:
                TV_1mm.setTextColor(Color.WHITE);
                TV_1m.setTextColor(Color.GRAY);
                TV_1d.setTextColor(Color.GRAY);
                TV_3h.setTextColor(Color.GRAY);
                TV_1h.setTextColor(Color.GRAY);
                TV_30m.setTextColor(Color.GRAY);
                TV_15m.setTextColor(Color.GRAY);
                timeGapType="Minute";
                timeGapInt=1;
                setDateScale("Minute");
                break;

            case R.id.IvGraphTools:
                startActivity(new Intent(HomeActivity.this, IndiActivity.class));
                break;
            case R.id.IvGraph:
                AlertDialog graphdialog = new AlertDialog.Builder(this).create();
                graphdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                graphdialog.setView(getLayoutInflater().inflate(R.layout.layout_chart_type_dialog, null));
                graphdialog.show();
                img1 = graphdialog.findViewById(R.id.img1);
                img2 = graphdialog.findViewById(R.id.img2);
                img3 = graphdialog.findViewById(R.id.img3);
                img4 = graphdialog.findViewById(R.id.img4);
                switch1 = graphdialog.findViewById(R.id.switch1);
                switch2 = graphdialog.findViewById(R.id.switch2);
                imgBlack = graphdialog.findViewById(R.id.imgBlack);
                imgColored = graphdialog.findViewById(R.id.imgColored);
                contrast_group = graphdialog.findViewById(R.id.contrast_group);
                txt_5_sec = graphdialog.findViewById(R.id.txt_5_sec);
                txt_10_sec = graphdialog.findViewById(R.id.txt_10_sec);
                txt_15_sec = graphdialog.findViewById(R.id.txt_15_sec);
                txt_30_sec = graphdialog.findViewById(R.id.txt_30_sec);
                txt_1_min = graphdialog.findViewById(R.id.txt_1_min);

                switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b) {
                            seriesType = "HEIKEN_ASHI";
                            changePriceStyle(seriesType);
                        }
                    }
                });

                TV_icclosed = graphdialog.findViewById(R.id.TV_icclosed);
                TV_icclosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        graphdialog.dismiss();
                    }
                });

                imgBlack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        highLightContrastImg(1);
                        seriesType = "HOLLOW_CANDLESTICK";
                        changePriceStyle(seriesType);
                    }
                });

                imgColored.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        highLightContrastImg(2);
                        seriesType = "CANDLESTICK";
                        changePriceStyle(seriesType);
                    }
                });

                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highLightImg(1);
                        contrast_group.setVisibility(View.GONE);
                        seriesType = "MOUNTAIN";
                        changePriceStyle(seriesType);
                    }
                });
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highLightImg(2);
                        contrast_group.setVisibility(View.GONE);
                        seriesType = "LINE";
                        if (chartView.isCrosshairOn()) {
                            chartView.setCrosshairOn(false);
                            chartView.layoutAndInvalidate();
                        }
                        changePriceStyle(seriesType);
                    }
                });
                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highLightImg(3);
                        contrast_group.setVisibility(View.VISIBLE);
                        seriesType = "CANDLESTICK";
                        changePriceStyle(seriesType);
                    }
                });
                img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highLightImg(4);
                        contrast_group.setVisibility(View.GONE);
                        seriesType = "BAR";
                        changePriceStyle(seriesType);
                    }
                });

                txt_5_sec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highLightText(5);
                        Toast.makeText(HomeActivity.this, "5", Toast.LENGTH_SHORT).show();
                        timeGapType="Seconds";
                        timeGapInt=5;
                        setDateScale("Seconds");
                    }
                });
                txt_10_sec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highLightText(10);
                        timeGapType="Seconds";
                        timeGapInt=10;
                        Toast.makeText(HomeActivity.this, "10", Toast.LENGTH_SHORT).show();
                        setDateScale("Seconds");
                    }
                });

                txt_30_sec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highLightText(30);
                        timeGapType="Seconds";
                        timeGapInt=30;
                        Toast.makeText(HomeActivity.this, "30", Toast.LENGTH_SHORT).show();
                        setDateScale("Seconds");
                    }
                });
                txt_15_sec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highLightText(15);
                        timeGapType="Seconds";
                        timeGapInt=15;
                        Toast.makeText(HomeActivity.this, "15", Toast.LENGTH_SHORT).show();
                        setDateScale("Seconds");
                    }
                });

                txt_1_min.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        highLightText(1);
                        timeGapType="Minute";
                        timeGapInt=1;
                        Toast.makeText(HomeActivity.this, "txt1 min", Toast.LENGTH_SHORT).show();
                        setDateScale("Minute");
                    }
                });
                break;

            case R.id.RLTime:
                AlertDialog timedialog = new AlertDialog.Builder(this).create();
                timedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                timedialog.setView(getLayoutInflater().inflate(R.layout.layout_timedialog, null));
                timedialog.show();
                time_view = timedialog.findViewById(R.id.time_view);
                time_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                DigitaltimeAdapter digitaltimeAdapter = new DigitaltimeAdapter(getApplicationContext(), (ArrayList<String>) list);
                time_view.setAdapter(digitaltimeAdapter);
                Button BTN_off = timedialog.findViewById(R.id.BTN_off);
                Button BTN_close = timedialog.findViewById(R.id.BTN_close);
                Button BTN_spot = timedialog.findViewById(R.id.BTN_spot);
                TV_icclosed = timedialog.findViewById(R.id.TV_icclosed);

                BTN_off.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    }
                });
                BTN_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                    }
                });
                BTN_spot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                    }
                });
                TV_icclosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        timedialog.dismiss();
                    }
                });
                break;

            case R.id.TV_buyclose:
                if (llbuylayout.getVisibility() == View.VISIBLE) {
                    llbuylayout.setVisibility(View.INVISIBLE);
                }
                RLPrice.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.VISIBLE);
                RLInvest.setVisibility(View.VISIBLE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                RLSpread.setVisibility(View.VISIBLE);
                RLLeverage.setVisibility(View.VISIBLE);
                llbuylayout.setVisibility(View.GONE);
                break;

            case R.id.TV_putclosed:
                lldigitalcalllayout.setVisibility(View.GONE);
                LLOperations.setVisibility(View.VISIBLE);
                break;

            /*case R.id.TvConfirm:
                Toast.makeText(this, "Confirmed", Toast.LENGTH_SHORT).show();
                //LLOperations.setVisibility(View.VISIBLE);
                //llbuylayout.setVisibility(View.GONE);
                break;*/

            case R.id.CVDigital:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                /*if (LLDigitalVisible.getVisibility() == View.VISIBLE) {
                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.GONE);
                    Tv_DigitalSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLDigitalVisible.setVisibility(View.VISIBLE);
                    LLDigitalClose.setVisibility(View.VISIBLE);
                    Tv_DigitalSymbol.setVisibility(View.GONE);

                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);

                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);

                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);

                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);

                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                    //LLDigitalVisible.startAnimation(slideDown);
                }*/
                RLTime.setVisibility(View.VISIBLE);
                RLStrikePrice.setVisibility(View.VISIBLE);
                RLLeverage.setVisibility(View.GONE);
                RLInvest.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.GONE);
                RLPrice.setVisibility(View.GONE);
                RLProfit.setVisibility(View.VISIBLE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.GONE);
                RLProfitPut.setVisibility(View.GONE);
                llbuylayout.setVisibility(View.GONE);
                lldigitalcalllayout.setVisibility(View.GONE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lldigitalcalllayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);
                    }
                });
                RLSell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lldigitalcalllayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);

                    }
                });
                break;

            case R.id.CVForex:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                /*if (LLForexVisible.getVisibility() == View.VISIBLE) {
                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLForexVisible.setVisibility(View.VISIBLE);
                    LLForexClose.setVisibility(View.VISIBLE);
                    Tv_ForexSymbol.setVisibility(View.GONE);

                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.GONE);
                    Tv_DigitalSymbol.setVisibility(View.VISIBLE);

                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);

                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);

                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);

                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                    //LLDigitalVisible.startAnimation(slideDown);
                }*/
                RLTime.setVisibility(View.GONE);
                RLStrikePrice.setVisibility(View.GONE);
                RLLeverage.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.VISIBLE);
                RLPrice.setVisibility(View.VISIBLE);
                RLProfit.setVisibility(View.GONE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.VISIBLE);
                RLProfitPut.setVisibility(View.GONE);
                llbuylayout.setVisibility(View.GONE);
                lldigitalcalllayout.setVisibility(View.GONE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);

                    }
                });
                RLSell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);
                    }
                });

                TvConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.GONE);
                        LLOperations.setVisibility(View.VISIBLE);
                    }
                });
                break;

            case R.id.CVStocks:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                /*if (LLStocksVisible.getVisibility() == View.VISIBLE) {
                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLStocksVisible.setVisibility(View.VISIBLE);
                    LLStocksClose.setVisibility(View.VISIBLE);
                    Tv_StocksSymbol.setVisibility(View.GONE);

                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.VISIBLE);
                    Tv_DigitalSymbol.setVisibility(View.GONE);

                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);

                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);

                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);

                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                    //LLDigitalVisible.startAnimation(slideDown);
                }*/
                RLTime.setVisibility(View.GONE);
                RLStrikePrice.setVisibility(View.GONE);
                RLLeverage.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.VISIBLE);
                RLPrice.setVisibility(View.VISIBLE);
                RLProfit.setVisibility(View.GONE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.VISIBLE);
                RLProfitPut.setVisibility(View.GONE);
                llbuylayout.setVisibility(View.GONE);
                lldigitalcalllayout.setVisibility(View.GONE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);

                    }
                });
                RLSell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);
                    }
                });

                TvConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.GONE);
                        LLOperations.setVisibility(View.VISIBLE);
                    }
                });
                break;

            case R.id.CVETF:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                /*if (LLETFVisible.getVisibility() == View.VISIBLE) {
                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLETFVisible.setVisibility(View.VISIBLE);
                    LLEtfClose.setVisibility(View.VISIBLE);
                    Tv_EtfSymbol.setVisibility(View.GONE);

                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.GONE);
                    Tv_DigitalSymbol.setVisibility(View.VISIBLE);

                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);

                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);

                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);

                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                    //LLDigitalVisible.startAnimation(slideDown);
                }*/

                RLTime.setVisibility(View.GONE);
                RLStrikePrice.setVisibility(View.GONE);
                RLLeverage.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.VISIBLE);
                RLPrice.setVisibility(View.VISIBLE);
                RLProfit.setVisibility(View.GONE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.VISIBLE);
                RLProfitPut.setVisibility(View.GONE);
                llbuylayout.setVisibility(View.GONE);
                lldigitalcalllayout.setVisibility(View.GONE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);

                    }
                });
                RLSell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);
                    }
                });

                TvConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.GONE);
                        LLOperations.setVisibility(View.VISIBLE);
                    }
                });
                break;

            case R.id.CVComm:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                /*if (LLCommVisible.getVisibility() == View.VISIBLE) {
                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLCommVisible.setVisibility(View.VISIBLE);
                    LLCommClose.setVisibility(View.VISIBLE);
                    Tv_CommSymbol.setVisibility(View.GONE);

                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.GONE);
                    Tv_DigitalSymbol.setVisibility(View.VISIBLE);

                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);

                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);

                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);

                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                    //LLDigitalVisible.startAnimation(slideDown);
                }*/
                RLTime.setVisibility(View.GONE);
                RLStrikePrice.setVisibility(View.GONE);
                RLLeverage.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.VISIBLE);
                RLPrice.setVisibility(View.VISIBLE);
                RLProfit.setVisibility(View.GONE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.VISIBLE);
                RLProfitPut.setVisibility(View.GONE);
                llbuylayout.setVisibility(View.GONE);
                lldigitalcalllayout.setVisibility(View.GONE);
                RLBuy.setVisibility(View.VISIBLE);
                RLSell.setVisibility(View.VISIBLE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);

                    }
                });
                RLSell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);
                    }
                });

                TvConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.GONE);
                        LLOperations.setVisibility(View.VISIBLE);
                    }
                });
                break;

            case R.id.CVCrypto:
                //Animation slideDown = AnimationUtils.loadAnimation(this, R.anim.fade_in);
                /*if (LLCryptoVisible.getVisibility() == View.VISIBLE) {
                    LLCryptoVisible.setVisibility(View.GONE);
                    LLCryptoClose.setVisibility(View.GONE);
                    Tv_CryptoSymbol.setVisibility(View.VISIBLE);
                } else {
                    LLCryptoVisible.setVisibility(View.VISIBLE);
                    LLCryptoClose.setVisibility(View.VISIBLE);
                    Tv_CryptoSymbol.setVisibility(View.GONE);

                    LLDigitalVisible.setVisibility(View.GONE);
                    LLDigitalClose.setVisibility(View.GONE);
                    Tv_DigitalSymbol.setVisibility(View.VISIBLE);

                    LLForexVisible.setVisibility(View.GONE);
                    LLForexClose.setVisibility(View.GONE);
                    Tv_ForexSymbol.setVisibility(View.VISIBLE);

                    LLStocksVisible.setVisibility(View.GONE);
                    LLStocksClose.setVisibility(View.GONE);
                    Tv_StocksSymbol.setVisibility(View.VISIBLE);

                    LLETFVisible.setVisibility(View.GONE);
                    LLEtfClose.setVisibility(View.GONE);
                    Tv_EtfSymbol.setVisibility(View.VISIBLE);

                    LLCommVisible.setVisibility(View.GONE);
                    LLCommClose.setVisibility(View.GONE);
                    Tv_CommSymbol.setVisibility(View.VISIBLE);

                    //LLDigitalVisible.startAnimation(slideDown);
                }*/
                RLTime.setVisibility(View.VISIBLE);
                RLStrikePrice.setVisibility(View.GONE);
                RLLeverage.setVisibility(View.VISIBLE);
                RLTotal.setVisibility(View.GONE);
                RLPrice.setVisibility(View.VISIBLE);
                RLProfit.setVisibility(View.GONE);
                RLProfitCall.setVisibility(View.GONE);
                RLSpread.setVisibility(View.VISIBLE);
                RLProfitPut.setVisibility(View.GONE);
                LLOperations.setVisibility(View.VISIBLE);
                RLBuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);

                    }
                });
                RLSell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.VISIBLE);
                        LLOperations.setVisibility(View.GONE);
                    }
                });

                TvConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        llbuylayout.setVisibility(View.GONE);
                        LLOperations.setVisibility(View.VISIBLE);
                    }
                });
                break;
            case R.id.RLPrice:
                AlertDialog pricedialog = new AlertDialog.Builder(this).create();
                pricedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                pricedialog.setView(getLayoutInflater().inflate(R.layout.layout_price_dialog, null));
                pricedialog.show();

                EditText EtAmount = pricedialog.findViewById(R.id.EtAmount);
                Button BtnPriceUp = pricedialog.findViewById(R.id.BtnPriceUp);
                Button BtnPriceDown = pricedialog.findViewById(R.id.BtnPriceDown);
                Button BtnCfm = pricedialog.findViewById(R.id.BtnCfm);


                BtnCfm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView TvMarket = findViewById(R.id.TvMarket);
                        TvMarket.setText(EtAmount.getText());
                        pricedialog.dismiss();
                    }
                });

                TV_icclosed = pricedialog.findViewById(R.id.TV_icclosed);
                TV_icclosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pricedialog.dismiss();
                    }
                });

                BtnPriceDown.setOnClickListener(v1 -> {
                    if (TextUtils.isEmpty(EtAmount.getText())) {
                        Toast.makeText(HomeActivity.this, "Enter a Amount", Toast.LENGTH_SHORT).show();
                    } else {
                        int amountt = Integer.parseInt(EtAmount.getText().toString());
                        if (amountt >= 2) {
                            amountt--;
                        } else {
                            Toast.makeText(HomeActivity.this, "Minimum Purchase Price should be $1", Toast.LENGTH_SHORT).show();
                        }
                        EtAmount.setText(String.valueOf(amountt));
                    }
                });

                BtnPriceUp.setOnClickListener(v12 -> {
                    if (TextUtils.isEmpty(EtAmount.getText())) {
                        Toast.makeText(HomeActivity.this, "Enter a Amount", Toast.LENGTH_SHORT).show();
                    } else {
                        int amountt = Integer.parseInt(EtAmount.getText().toString());
                        amountt++;
                        EtAmount.setText(String.valueOf(amountt));
                    }
                });
                break;
            case R.id.RLLeverage:
                int[] location = new int[2];
                TvLeverage.getLocationOnScreen(location);
                final View mView = getLayoutInflater().inflate(R.layout.layout_lever, null, false);
                final PopupWindow popUp = new PopupWindow(mView, 150, 300, false);
                popUp.setTouchable(true);
                popUp.setFocusable(true);
                popUp.setOutsideTouchable(true);
                popUp.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1]);

                TextView tv_Multiplierone = mView.findViewById(R.id.tv_Multiplierone);
                TextView tv_Multipliertwo = mView.findViewById(R.id.tv_Multipliertwo);
                TextView tv_Multiplierthree = mView.findViewById(R.id.tv_Multiplierthree);
                TextView tv_Multiplierfour = mView.findViewById(R.id.tv_Multiplierfour);
                TextView tv_Multiplierfive = mView.findViewById(R.id.tv_Multiplierfive);

                String Multiplierone, Multipliertwo, Multiplierthree, Multiplierfour, Multiplierfive;
                Multiplierone = tv_Multiplierone.getText().toString();
                Multipliertwo = tv_Multipliertwo.getText().toString();
                Multiplierthree = tv_Multiplierthree.getText().toString();
                Multiplierfour = tv_Multiplierfour.getText().toString();
                Multiplierfive = tv_Multiplierfive.getText().toString();

                tv_Multiplierone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TV_multipiler.setText(Multiplierone);
                        popUp.dismiss();

                    }
                });
                tv_Multipliertwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TV_multipiler.setText(Multipliertwo);
                        popUp.dismiss();

                    }
                });
                tv_Multiplierthree.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TV_multipiler.setText(Multiplierthree);
                        popUp.dismiss();

                    }
                });
                tv_Multiplierfour.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TV_multipiler.setText(Multiplierfour);
                        popUp.dismiss();

                    }
                });
                tv_Multiplierfive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TV_multipiler.setText(Multiplierfive);
                        popUp.dismiss();

                    }
                });

                break;
            case R.id.RLInvest:
                AlertDialog investdialog = new AlertDialog.Builder(this).create();
                investdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                investdialog.setView(getLayoutInflater().inflate(R.layout.layout_invest_dialog, null));
                investdialog.show();

                Button BtnConfirm, BtnMultiply, BtnDivide;
                EditText EtValue, EtCoefficient;
                final double[] total = new double[1];
                final double[] amount = new double[1];
                final double[] Coefficient = new double[1];
                RecyclerView RvPresets = investdialog.findViewById(R.id.RvPresets);
                BtnConfirm = investdialog.findViewById(R.id.BtnConfirm);

                EtValue = investdialog.findViewById(R.id.EtValue);
                EtCoefficient = investdialog.findViewById(R.id.EtCoefficient);

                BtnMultiply = investdialog.findViewById(R.id.BtnMultiply);
                BtnDivide = investdialog.findViewById(R.id.BtnDivide);

                TextView TV_closed = investdialog.findViewById(R.id.TV_closed);

                TV_closed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        investdialog.dismiss();
                    }
                });

                BtnDivide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((EtValue.getText().length() > 0) && (EtCoefficient.getText().length() > 0)) {
                            amount[0] = Double.parseDouble(EtValue.getText().toString());
                            Coefficient[0] = Double.parseDouble(EtCoefficient.getText().toString());
                            total[0] = amount[0] / Coefficient[0];
                            EtValue.setText(Double.toString(total[0]));
                        }

                    }
                });

                BtnMultiply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((EtValue.getText().length() > 0) && (EtCoefficient.getText().length() > 0)) {
                            amount[0] = Double.parseDouble(EtValue.getText().toString());
                            Coefficient[0] = Double.parseDouble(EtCoefficient.getText().toString());
                            total[0] = amount[0] * Coefficient[0];
                            EtValue.setText(Double.toString(total[0]));
                        }
                    }
                });

                BtnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TvInvestValue.setText(EtValue.getText());
                        investdialog.dismiss();
                    }
                });
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                RvPresets.setLayoutManager(layoutManager);


                String[] a = {"50", "100", "200", "300", "400", "500", "1000", "2000", "5000", "10000"};
                ArrayList<String> data = new ArrayList<String>(Arrays.asList(a));

                PresetsAdapter presetsAdapter = new PresetsAdapter(this, data);
                RvPresets.setAdapter(presetsAdapter);
                presetsAdapter.notifyDataSetChanged();
                presetsAdapter.onItemClickListner(new PresetsAdapter.onItemClickListner() {
                    @Override
                    public void onClick(List<String> list, int potition) {
                        EtValue.setText(list.get(potition));
                    }
                });

                break;
            case R.id.TvDeposit:
                startActivity(new Intent(this, DepositActivity.class));
                /*signin = new AlertDialog.Builder(this).create();
                signin.requestWindowFeature(Window.FEATURE_NO_TITLE);
                signin.setView(getLayoutInflater().inflate(R.layout.layout_signin_dialog, null));
                signin.show();

                BtnLogin = signin.findViewById(R.id.BtnLogin);
                BtnLogin.setOnClickListener(this);

                Tvclosed = signin.findViewById(R.id.Tvclosed);

                Tvclosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signin.dismiss();
                    }
                });*/

                break;
            case R.id.BtnLogin:
                signin.dismiss();
                break;
            case R.id.TvAddRealAccount:
                balancedialog.dismiss();
                signin = new AlertDialog.Builder(this).create();
                signin.requestWindowFeature(Window.FEATURE_NO_TITLE);
                signin.setView(getLayoutInflater().inflate(R.layout.layout_signin_dialog, null));
                signin.show();

                BtnLogin = signin.findViewById(R.id.BtnLogin);
                BtnLogin.setOnClickListener(this);

                Tvclosed = signin.findViewById(R.id.Tvclosed);

                Tvclosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signin.dismiss();
                    }
                });

                break;
            case R.id.RLBalance:

                realaccedialog = new AlertDialog.Builder(this).create();
                realaccedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                realaccedialog.setView(getLayoutInflater().inflate(R.layout.layout_real_acc_balance_dialog, null));
                realaccedialog.show();
                realaccedialog.setCanceledOnTouchOutside(true);
                ImageView Iv_withdraw = realaccedialog.findViewById(R.id.Iv_withdraw);
                ImageView Iv_deposite = realaccedialog.findViewById(R.id.Iv_deposite);
                ImageView Iv_check = realaccedialog.findViewById(R.id.Iv_check);
                ImageView Iv_check1 = realaccedialog.findViewById(R.id.Iv_check1);
                ImageView Iv_refresh = realaccedialog.findViewById(R.id.Iv_refresh);
                ImageView IV_info = realaccedialog.findViewById(R.id.IV_info);
                TextView TV_info = realaccedialog.findViewById(R.id.TV_info);
                TextView TV_close = realaccedialog.findViewById(R.id.TV_close);
                TV_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        realaccedialog.dismiss();
                    }
                });
                TextView Tv_UsdCashValue1 = realaccedialog.findViewById(R.id.Tv_UsdCashValue1);
                IV_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TV_info.setVisibility(View.VISIBLE);
                    }
                });

                Iv_refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IV_info.setVisibility(View.GONE);
                        Iv_refresh.setVisibility(View.GONE);
                        Tv_UsdCashValue1.setText("$10000");

                    }
                });

                RelativeLayout RL_Realacc = realaccedialog.findViewById(R.id.RL_Realacc);
                RelativeLayout RL_practiceacc = realaccedialog.findViewById(R.id.RL_practiceacc);

                RL_Realacc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Iv_check.setVisibility(View.VISIBLE);
                        Iv_check1.setVisibility(View.GONE);
                    }
                });

                RL_practiceacc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Iv_check1.setVisibility(View.VISIBLE);
                        Iv_check.setVisibility(View.GONE);

                    }
                });

                Iv_withdraw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(HomeActivity.this, WithdrawActivity.class));
                    }
                });
                Iv_deposite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomeActivity.this, DepositActivity.class));

                    }
                });

                /*balancedialog = new AlertDialog.Builder(this).create();
                balancedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                balancedialog.setView(getLayoutInflater().inflate(R.layout.layout_balance_dialog, null));
                balancedialog.show();

                TextView TvAddRealAccount = balancedialog.findViewById(R.id.TvAddRealAccount);

                TvAddRealAccount.setOnClickListener(this);*/

                break;
            case R.id.IvAddSymbol:
                startActivity(new Intent(HomeActivity.this, SymbolListActivity.class));
                break;
            case R.id.IvMenu:
                RLFrames.setVisibility(View.GONE);
                fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                if (RLNavigation.getVisibility() == View.VISIBLE) {
                    RLNavigation.setVisibility(View.GONE);
                    fragmentTransaction.replace(R.id.FragmentNav, new BlankFragment());
                } else {
                    fragmentTransaction.replace(R.id.FragmentNav, new ProfileFragment());
                    RLNavigation.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.IvTab1:
                if (!IvTab1.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, getFragmentToReplace(customizeArrayList.get(0).getCustomizeName()));
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(1);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }

                break;
            case R.id.IvTab2:
                if (!IvTab2.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, getFragmentToReplace(customizeArrayList.get(1).getCustomizeName()));
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(2);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }
                break;


            case R.id.IvTab3:
                if (!IvTab3.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, getFragmentToReplace(customizeArrayList.get(2).getCustomizeName()));
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(3);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }
                break;
            case R.id.IvTab4:
                if (!IvTab4.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, getFragmentToReplace(customizeArrayList.get(3).getCustomizeName()));
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(4);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }

                break;
            case R.id.IvTab5:
                if (!IvTab5.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, getFragmentToReplace(customizeArrayList.get(4).getCustomizeName()));
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(5);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }
                break;
            case R.id.IvTab6:
                customizeFun(fragmentTransaction);
                break;
            case R.id.IvInfo:
                if (!IvInfo.isSelected()) {
                    fragmentTransaction.replace(R.id.FragmentHolder, new SymbolInfoFragment());
                    RLFrames.setVisibility(View.VISIBLE);
                    highLightSelectedTagImg(7);
                } else {
                    fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
                    RLFrames.setVisibility(View.GONE);
                    clearSelectedTagImg();
                }
                break;
        }
        fragmentTransaction.commit();
    }




    private Fragment getFragmentToReplace(String customizeName) {
        Fragment returnFragment = new BlankFragment();
        if (customizeName.equalsIgnoreCase("Video Tutorial")) {
            returnFragment = new TutorialFragment();
        } else if (customizeName.equalsIgnoreCase("Chats")) {
            returnFragment = new ChatFragment();
        } else if (customizeName.equalsIgnoreCase("Portfolio")) {
            returnFragment = new PortfolioFragment();
        } else if (customizeName.equalsIgnoreCase("Market Analysis")) {
            returnFragment = new MarketAnalysisFragment();
        } else if (customizeName.equalsIgnoreCase("Price Movements")) {
            returnFragment = new MovesAlertsFragment();
        }
        return returnFragment;
    }

    public void customizeFun(FragmentTransaction fragmentTransaction) {
        RLFrames.setVisibility(View.GONE);

        setTabView(customizeArrayList);

        setPref(this, "tabList", new Gson().toJson(customizeArrayList));

//                fragmentTransaction.replace(R.id.FragmentHolder, new BlankFragment());
        if (!IvTab6.isSelected()) {
            fragmentTransaction.replace(R.id.FragmentCustomize, new CustomizeFragment(customizeArrayList));
            RLCustomize.setVisibility(View.VISIBLE);
            highLightSelectedTagImg(6);
        } else {
            fragmentTransaction.replace(R.id.FragmentCustomize, new BlankFragment());
            RLCustomize.setVisibility(View.GONE);
            clearSelectedTagImg();
        }
    }

    private void setCustomVisibility(int i) {
        int customView;
        if (customizeArrayList.get(i).getCustomizeView() == 1) {
            customView = View.VISIBLE;
        } else {
            customView = View.GONE;
        }
        if (customizeArrayList.get(i).getCustomizeName().equalsIgnoreCase("Video Tutorial")) {
            IvTab4.setVisibility(customView);
        }
        if (customizeArrayList.get(i).getCustomizeName().equalsIgnoreCase("Chats")) {
            IvTab5.setVisibility(customView);
        }
        if (customizeArrayList.get(i).getCustomizeName().equalsIgnoreCase("Portfolio")) {
            IvTab1.setVisibility(customView);
        }
        if (customizeArrayList.get(i).getCustomizeName().equalsIgnoreCase("Market Analysis")) {
            IvTab2.setVisibility(customView);
        }
        if (customizeArrayList.get(i).getCustomizeName().equalsIgnoreCase("Price Movements")) {
            IvTab3.setVisibility(customView);
        }
    }

    private void setTabView(ArrayList<CustomizeSymbol> customizeArrayList) {

        IvTab1.setImageDrawable(getResources().getDrawable(customizeArrayList.get(0).getCustomizeDrawable()));
        IvTab2.setImageDrawable(getResources().getDrawable(customizeArrayList.get(1).getCustomizeDrawable()));
        IvTab3.setImageDrawable(getResources().getDrawable(customizeArrayList.get(2).getCustomizeDrawable()));
        IvTab4.setImageDrawable(getResources().getDrawable(customizeArrayList.get(3).getCustomizeDrawable()));
        IvTab5.setImageDrawable(getResources().getDrawable(customizeArrayList.get(4).getCustomizeDrawable()));

        for (int i = 0; i < customizeArrayList.size(); i++) {
            setCustomVisibility(i);
        }
    }

    public void highLightSelectedTagImg(int i) {
        clearSelectedTagImg();
        switch (i) {
            case 1:
                IvTab1.setSelected(true);
                break;
            case 2:
                IvTab2.setSelected(true);
                break;
            case 3:
                IvTab3.setSelected(true);
                break;
            case 4:
                IvTab4.setSelected(true);
                break;
            case 5:
                IvTab5.setSelected(true);
                break;
            case 6:
                IvTab6.setSelected(true);
                break;
            case 7:
                IvInfo.setSelected(true);
                break;
        }

    }

    public void clearSelectedTagImg() {
        IvTab1.setSelected(false);
        IvTab2.setSelected(false);
        IvTab3.setSelected(false);
        IvTab4.setSelected(false);
        IvTab5.setSelected(false);
        IvTab6.setSelected(false);
        IvInfo.setSelected(false);
    }

    public void highLightText(int i) {
        clearSelectedText();
        switch (i) {
            case 5:
                txt_5_sec.setSelected(true);
                break;
            case 10:
                txt_10_sec.setSelected(true);
                break;
            case 15:
                txt_15_sec.setSelected(true);
                break;
            case 30:
                txt_30_sec.setSelected(true);
                break;
            case 1:
                txt_1_min.setSelected(true);
                break;
        }
    }

    PriceStyle priceStyle;

    private void changePriceStyle(String seriesTypeValue) {
        if (seriesTypeValue.equalsIgnoreCase("MOUNTAIN")) {
            priceStyle = PriceStyle.MOUNTAIN;
        } else if (seriesTypeValue.equalsIgnoreCase("LINE")) {
            priceStyle = PriceStyle.LINE;
        } else if (seriesTypeValue.equalsIgnoreCase("CANDLESTICK")) {
            priceStyle = PriceStyle.CANDLESTICK;
        } else if (seriesTypeValue.equalsIgnoreCase("BAR")) {
            priceStyle = PriceStyle.BAR;
        }
        Log.i(TAG, "changePriceStyle: " + priceStyle);
        //  Map<String, TCDDataSeries> dataSeriesMap = TradingChartDesignerView.getDataSeriesMap(CSVHelper.getBars(this), "Gold");;
        List<TABar> listTabar=getListBars(readCSVFile());
        Log.i(TAG, "changePriceStyle: Size "+listTabar.size());
        Map<String, TCDDataSeries> dataSeriesMap = TradingChartDesignerView.getDataSeriesMap(listTabar, "Gold");

        chartManager = new ChartManagerFactory().getManager(priceStyle, dataSeriesMap);

        ArrayList<TCDDataSeries> masterDataSeries = new ArrayList<>();

        masterDataSeries.add(chartManager.getCurrentDataSeries().get(TCDDataSeries.TCD_CLOSE_DATASERIES_SUFIX));

        chartView.setMasterDataSeries(masterDataSeries);
        chartView.setDataSeries(new ArrayList<TCDDataSeries>(chartManager.getCurrentDataSeries().values()));

        ArrayList<TCDPlot> plots = new ArrayList<TCDPlot>() {{
            add(chartManager.getPlot());
        }};

        chartView.getMainPanel().setPlots(plots);
        setDateScale("Seconds");
        chartView.getMainPanel().getYScalePanel().setMinimumFractionDigits(2);
        chartView.getMainPanel().getYScalePanel().autoscale();
        chartView.getChartPanels().setNeedAutoscale();
        chartView.layoutAndInvalidate();
    }

    TAPeriodicityEnum taPeriodicityEnum;

    private void setDateScale(String dateValue) {
        if (dateValue.equalsIgnoreCase("Seconds")) {

            taPeriodicityEnum = TAPeriodicityEnum.TAPeriodicitySecond;
        } else if (dateValue.equalsIgnoreCase("Minute")) {
            taPeriodicityEnum = TAPeriodicityEnum.TAPeriodicityMinute;
        } else if (dateValue.equalsIgnoreCase("Hour")) {
            taPeriodicityEnum = TAPeriodicityEnum.TAPeriodicityHour;
        } else if (dateValue.equalsIgnoreCase("Day")) {
            taPeriodicityEnum = TAPeriodicityEnum.TAPeriodicityDay;
        } else if (dateValue.equalsIgnoreCase("Week")) {
            taPeriodicityEnum = TAPeriodicityEnum.TAPeriodicityWeek;
        } else if (dateValue.equalsIgnoreCase("Month")) {
            taPeriodicityEnum = TAPeriodicityEnum.TAPeriodicityMonth;
        } else if (dateValue.equalsIgnoreCase("Year")) {
            taPeriodicityEnum = TAPeriodicityEnum.TAPeriodicityYear;
        }
        chartView.getDateScale().setAutoScale(true);
        chartView.getDateScale().setTimeFrame(new TATimeFrame(1, taPeriodicityEnum));
        chartView.layoutAndInvalidate();
    }

    private void highLightContrastImg(int i) {
        clearSelectedContrastImg();
        switch (i) {
            case 1:
                imgBlack.setSelected(true);
                break;
            case 2:
                imgColored.setSelected(true);
                break;
        }
    }

    private void clearSelectedContrastImg() {
        imgBlack.setSelected(false);
        imgColored.setSelected(false);
    }

    private void highLightImg(int i) {
        clearSelectedImg();
        switch (i) {
            case 1:
                img1.setSelected(true);
                break;
            case 2:
                img2.setSelected(true);
                break;
            case 3:
                img3.setSelected(true);
                break;
            case 4:
                img4.setSelected(true);
                break;
        }
    }

    private void clearSelectedText() {
        txt_5_sec.setSelected(false);
        txt_10_sec.setSelected(false);
        txt_15_sec.setSelected(false);
        txt_30_sec.setSelected(false);
        txt_1_min.setSelected(false);
    }

    private void clearSelectedImg() {
        img1.setSelected(false);
        img2.setSelected(false);
        img3.setSelected(false);
        img4.setSelected(false);
    }

    public void setAlertVisibility() {
        if (LLOperations.getVisibility() == View.VISIBLE) {
            LLOperations.setVisibility(View.GONE);
            RLOperations.setVisibility(View.VISIBLE);

        } else {
            LLOperations.setVisibility(View.VISIBLE);
            RLOperations.setVisibility(View.GONE);

        }
    }

    public void plusValue() {
        if (currentValue1 <= 999) {
            currentValue1 = currentValue1 + 1;
        }
    }

    public void minValue() {
        if (currentValue1 >= 1) {
            currentValue1 = currentValue1 - 1;
        }
    }
}
