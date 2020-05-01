package com.app.idnbin.Profile.History.Trading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;

public class InstrumentTypeActivity extends BaseActivity {
    Toolbar Tb_App;
    CheckBox allCheckBox, OptionsCheckBox, forexCheckBox, stocksCheckBox, cryptoCheckBox, commoditiesCheckBox, etfCheckBox;
    private static final String TAG = "InstrumentTypeActivity";
    ArrayList<String> instrumentArray=new ArrayList<>(6);
    StringBuilder strInstrument=new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_type);
        Tb_App = findViewById(R.id.Tb_App);

        allCheckBox = findViewById(R.id.allCheckBox);
        OptionsCheckBox = findViewById(R.id.OptionsCheckBox);
        forexCheckBox = findViewById(R.id.forexCheckBox);
        stocksCheckBox = findViewById(R.id.stocksCheckBox);
        cryptoCheckBox = findViewById(R.id.cryptoCheckBox);
        commoditiesCheckBox = findViewById(R.id.commoditiesCheckBox);
        etfCheckBox = findViewById(R.id.etfCheckBox);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Instrument type");

        if (!getPref(this, "TradingInstrument").equalsIgnoreCase("null")) {
            instrumentArray = new Gson().fromJson(getPref(this, "TradingInstrument"), new TypeToken<ArrayList<String>>() {
            }.getType());

            if(instrumentArray.size()==6)
            {
                setAllCheckBox(true);
            }
            else {
                isDataPresent(instrumentArray);
            }

            Log.i(TAG, "onCreate: "+instrumentArray.size());
        }
        else {
            setAllCheckBox(true);
        }

    }

    private void isDataPresent(ArrayList<String> instrumentArray) {
        for(String str: instrumentArray)
        {
            if(str.equalsIgnoreCase("Options"))
            {
                OptionsCheckBox.setChecked(true);
            }
            else if(str.equalsIgnoreCase("Forex"))
            {
                forexCheckBox.setChecked(true);
            }
            else if(str.equalsIgnoreCase("Stocks"))
            {
                stocksCheckBox.setChecked(true);
            }
            else if(str.equalsIgnoreCase("Crypto"))
            {
                cryptoCheckBox.setChecked(true);
            }
            else if(str.equalsIgnoreCase("Commodities"))
            {
                commoditiesCheckBox.setChecked(true);
            }
            else if(str.equalsIgnoreCase("ETFs"))
            {
                etfCheckBox.setChecked(true);
            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        passIntentData();
        onBackPressed();
        return true;
    }

    private void passIntentData() {
        setPref(this, "TradingInstrument", new Gson().toJson(instrumentArray));
        if(instrumentArray==null || instrumentArray.size()==6)
        {
            strInstrument.append("All Instruments");
        }
        else {
            for (int i=0;i<instrumentArray.size();i++)
            {
                if(i==0)
                {
                    strInstrument.append(instrumentArray.get(i));
                }
                else {
                    strInstrument.append(", ");
                    strInstrument.append(instrumentArray.get(i));
                }
            }
        }
        Intent intent=new Intent();
        intent.putExtra("TradingInstrument",strInstrument.toString());
        setResult(2,intent);
    }


    public void onCheckBoxClick(View view){
        boolean checked = ((CheckBox) view).isChecked();
        String instrumentValue=((CheckBox) view).getText().toString();

        if(checked && instrumentValue.equalsIgnoreCase("All Instruments"))
        {
            setAllCheckBox(true);
        }
        else if(checked && !instrumentValue.equalsIgnoreCase("All Instruments"))
        {
            instrumentArray.add(instrumentValue);
            ((CheckBox) view).setChecked(true);
        }
        else if(!checked && !instrumentValue.equalsIgnoreCase("All Instruments")) {
            instrumentArray.remove(instrumentValue);
            ((CheckBox) view).setChecked(false);
        }
        else if(!checked && instrumentValue.equalsIgnoreCase("All Instruments"))
        {
            instrumentArray.clear();
            setAllCheckBox(false);
        }

        if(instrumentArray.size()==6)
        {
            allCheckBox.setChecked(true);
        }
        else {
            allCheckBox.setChecked(false);
        }

        Log.i(TAG, "onCheckBoxClick: "+instrumentArray.size());
        for(int i=0;i<instrumentArray.size();i++)
        {
            Log.i(TAG, "onCheckBoxClick: "+instrumentArray.get(i));
        }
    }

    private void setAllCheckBox(boolean checked) {

        allCheckBox.setChecked(checked);
        OptionsCheckBox.setChecked(checked);
        forexCheckBox.setChecked(checked);
        stocksCheckBox.setChecked(checked);
        cryptoCheckBox.setChecked(checked);
        commoditiesCheckBox.setChecked(checked);
        etfCheckBox.setChecked(checked);
        if(checked) {
            instrumentArray = new ArrayList<>(Arrays.asList("Options", "Forex", "Stocks", "Crypto","Commodities","ETFs"));
        }
        else {
            instrumentArray.clear();
        }
    }
}
