package com.app.idnbin.Assets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.idnbin.Assets.Commodities.CommodSymbolFragment;
import com.app.idnbin.Assets.Crypto.CryptoSymbolFragment;
import com.app.idnbin.Assets.Digital.DigitalSymbolFragment;
import com.app.idnbin.Assets.ETF.ETFSymbolFragment;
import com.app.idnbin.Assets.Forex.ForexSymbolFragment;
import com.app.idnbin.R;
import com.app.idnbin.Assets.Stocks.StocksSymbolFragment;

public class SymbolListActivity extends AppCompatActivity implements View.OnClickListener {

    TextView TvBack, Tv_Digital, Tv_Forex, Tv_Stocks, Tv_ETF, Tv_Commodities, Tv_Crypto;
    RelativeLayout RLFrames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_symbol_list);

        TvBack = findViewById(R.id.TvBack);
        Tv_Digital = findViewById(R.id.Tv_Digital);
        Tv_Forex = findViewById(R.id.Tv_Forex);
        Tv_Stocks = findViewById(R.id.Tv_Stocks);
        Tv_ETF = findViewById(R.id.Tv_ETF);
        Tv_Commodities= findViewById(R.id.Tv_Commodities);
        Tv_Crypto= findViewById(R.id.Tv_Crypto);
        RLFrames = findViewById(R.id.RLFrames);

        TvBack.setOnClickListener(this);
        Tv_Digital.setOnClickListener(this);
        Tv_Forex.setOnClickListener(this);
        Tv_Stocks.setOnClickListener(this);
        Tv_ETF.setOnClickListener(this);
        Tv_Commodities.setOnClickListener(this);
        Tv_Crypto.setOnClickListener(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(R.id.FragmentHolder1, new DigitalSymbolFragment());
        digital();
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        switch (v.getId()){
            case R.id.TvBack:
                finish();
                break;
            case R.id.Tv_Digital:
                fragmentTransaction.replace(R.id.FragmentHolder1, new DigitalSymbolFragment());
                digital();
                break;
            case R.id.Tv_Forex:
                fragmentTransaction.replace(R.id.FragmentHolder1, new ForexSymbolFragment());
                forex();
                break;
            case R.id.Tv_Stocks:
                fragmentTransaction.replace(R.id.FragmentHolder1, new StocksSymbolFragment());
                stocks();
                break;
            case R.id.Tv_ETF:
                fragmentTransaction.replace(R.id.FragmentHolder1, new ETFSymbolFragment());
                etf();
                break;
            case R.id.Tv_Commodities:
                fragmentTransaction.replace(R.id.FragmentHolder1, new CommodSymbolFragment());
                commodity();
                break;
            case R.id.Tv_Crypto:
                fragmentTransaction.replace(R.id.FragmentHolder1, new CryptoSymbolFragment());
                crypto();
                break;
        }
        fragmentTransaction.commit();
    }

    public void digital(){
        Tv_Digital.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_option_white, 0, 0, 0);
        Tv_Digital.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        Tv_Digital.setTextColor(Color.WHITE);
        Tv_Digital.setBackground(getResources().getDrawable(R.drawable.bg_rounded_transparent));

        Tv_Forex.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_forex, 0, 0, 0);
        Tv_Forex.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Forex.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Forex.setBackground(null);

        Tv_Stocks.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_stocks, 0, 0, 0);
        Tv_Stocks.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Stocks.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Stocks.setBackground(null);

        Tv_ETF.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_etf, 0, 0, 0);
        Tv_ETF.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_ETF.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_ETF.setBackground(null);

        Tv_Commodities.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_commod, 0, 0, 0);
        Tv_Commodities.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Commodities.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Commodities.setBackground(null);

        Tv_Crypto.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_crypto, 0, 0, 0);
        Tv_Crypto.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Crypto.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Crypto.setBackground(null);
    }

    public void forex(){
        Tv_Forex.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_forex_white, 0, 0, 0);
        Tv_Forex.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        Tv_Forex.setTextColor(Color.WHITE);
        Tv_Forex.setBackground(getResources().getDrawable(R.drawable.bg_rounded_transparent));

        Tv_Digital.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_option, 0, 0, 0);
        Tv_Digital.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Digital.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Digital.setBackground(null);

        Tv_Stocks.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_stocks, 0, 0, 0);
        Tv_Stocks.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Stocks.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Stocks.setBackground(null);

        Tv_ETF.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_etf, 0, 0, 0);
        Tv_ETF.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_ETF.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_ETF.setBackground(null);

        Tv_Commodities.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_commod, 0, 0, 0);
        Tv_Commodities.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Commodities.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Commodities.setBackground(null);

        Tv_Crypto.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_crypto, 0, 0, 0);
        Tv_Crypto.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Crypto.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Crypto.setBackground(null);
    }

    public void stocks(){
        Tv_Stocks.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_stocks_white, 0, 0, 0);
        Tv_Stocks.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        Tv_Stocks.setTextColor(Color.WHITE);
        Tv_Stocks.setBackground(getResources().getDrawable(R.drawable.bg_rounded_transparent));

        Tv_Digital.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_option, 0, 0, 0);
        Tv_Digital.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Digital.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Digital.setBackground(null);

        Tv_Forex.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_forex, 0, 0, 0);
        Tv_Forex.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Forex.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Forex.setBackground(null);

        Tv_ETF.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_etf, 0, 0, 0);
        Tv_ETF.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_ETF.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_ETF.setBackground(null);

        Tv_Commodities.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_commod, 0, 0, 0);
        Tv_Commodities.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Commodities.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Commodities.setBackground(null);

        Tv_Crypto.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_crypto, 0, 0, 0);
        Tv_Crypto.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Crypto.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Crypto.setBackground(null);
    }

    public void etf(){
        Tv_ETF.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_etf_white, 0, 0, 0);
        Tv_ETF.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        Tv_ETF.setTextColor(Color.WHITE);
        Tv_ETF.setBackground(getResources().getDrawable(R.drawable.bg_rounded_transparent));

        Tv_Digital.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_option, 0, 0, 0);
        Tv_Digital.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Digital.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Digital.setBackground(null);

        Tv_Stocks.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_stocks, 0, 0, 0);
        Tv_Stocks.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Stocks.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Stocks.setBackground(null);

        Tv_Forex.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_forex, 0, 0, 0);
        Tv_Forex.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Forex.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Forex.setBackground(null);

        Tv_Commodities.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_commod, 0, 0, 0);
        Tv_Commodities.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Commodities.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Commodities.setBackground(null);

        Tv_Crypto.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_crypto, 0, 0, 0);
        Tv_Crypto.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Crypto.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Crypto.setBackground(null);
    }

    public void commodity(){
        Tv_Commodities.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_commod_white, 0, 0, 0);
        Tv_Commodities.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        Tv_Commodities.setTextColor(Color.WHITE);
        Tv_Commodities.setBackground(getResources().getDrawable(R.drawable.bg_rounded_transparent));

        Tv_Digital.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_option, 0, 0, 0);
        Tv_Digital.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Digital.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Digital.setBackground(null);

        Tv_Stocks.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_stocks, 0, 0, 0);
        Tv_Stocks.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Stocks.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Stocks.setBackground(null);

        Tv_ETF.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_etf, 0, 0, 0);
        Tv_ETF.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_ETF.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_ETF.setBackground(null);

        Tv_Forex.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_forex, 0, 0, 0);
        Tv_Forex.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Forex.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Forex.setBackground(null);

        Tv_Crypto.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_crypto, 0, 0, 0);
        Tv_Crypto.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Crypto.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Crypto.setBackground(null);
    }

    public void crypto(){
        Tv_Crypto.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_crypto_white, 0, 0, 0);
        Tv_Crypto.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        Tv_Crypto.setTextColor(Color.WHITE);
        Tv_Crypto.setBackground(getResources().getDrawable(R.drawable.bg_rounded_transparent));

        Tv_Digital.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_option, 0, 0, 0);
        Tv_Digital.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Digital.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Digital.setBackground(null);

        Tv_Stocks.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_stocks, 0, 0, 0);
        Tv_Stocks.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Stocks.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Stocks.setBackground(null);

        Tv_ETF.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_etf, 0, 0, 0);
        Tv_ETF.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_ETF.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_ETF.setBackground(null);

        Tv_Commodities.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_commod, 0, 0, 0);
        Tv_Commodities.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Commodities.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Commodities.setBackground(null);

        Tv_Forex.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_asset_forex, 0, 0, 0);
        Tv_Forex.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        Tv_Forex.setTextColor(getResources().getColor(R.color.colorLightGray));
        Tv_Forex.setBackground(null);
    }
}
