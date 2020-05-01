package com.app.idnbin.Profile.History.Trading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class AssetActivity extends BaseActivity {
    Toolbar Tb_App;
    RecyclerView RvAsset;
    CheckBox CbSelectAll;
    ArrayList<AssetData> assetArray=new ArrayList<>(Arrays.asList( new AssetData("EUR/USD",false), new AssetData("EUR/GBP",false), new AssetData("GBP/JPY",false), new AssetData("EUR/JPY",false), new AssetData("GBP/USD",false), new AssetData("USD/JPY",false), new AssetData("GBP/USD",false), new AssetData("USD/JPY",false), new AssetData("AUD/CAD",false), new AssetData("USD/CHF",false), new AssetData("AUD/USD",false), new AssetData("USD/CAD",false), new AssetData("AUD/JPY",false)));
    AssetAdapter assetAdapter;
    private static final String TAG = "AssetActivity";
    StringBuilder strAsset=new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset);
        RvAsset = findViewById(R.id.RvAsset);
        CbSelectAll = findViewById(R.id.CbSelectAll);
        Tb_App = findViewById(R.id.Tb_App);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AssetActivity.this);
        RvAsset.setLayoutManager(layoutManager);

        if (!getPref(this, "TradingAsset").equalsIgnoreCase("null")) {
            assetArray = new Gson().fromJson(getPref(this, "TradingAsset"), new TypeToken<ArrayList<AssetData>>() {}.getType());
        }

        CbSelectAll.setChecked(isAllAssetDataTrue(assetArray));

        assetAdapter = new AssetAdapter(AssetActivity.this, assetArray, new AssetAdapter.AssetCheckedInterface() {
            @Override
            public void assetChecked(int pos) {
                if(assetArray.get(pos).isSelected())
                {
                    assetArray.get(pos).setSelected(false);
                }
                else {
                    assetArray.get(pos).setSelected(true);
                }

                CbSelectAll.setChecked(isAllAssetDataTrue(assetArray));

                assetAdapter.notifyDataSetChanged();
            }


        });
        RvAsset.setAdapter(assetAdapter);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Asset");

    }

    @Override
    public boolean onSupportNavigateUp() {
        passIntentData();
        onBackPressed();
        return true;
    }

    private void passIntentData() {
        setPref(this, "TradingAsset", new Gson().toJson(assetArray));
        Intent intent=new Intent();
        intent.putExtra("TradingAsset",txtToDisplay(assetArray));
        setResult(4,intent);
    }

    public String txtToDisplay(ArrayList<AssetData> assetArray)
    {
        int dataCount=0;
        if (assetArray == null || assetArray.size()==0 || isAllAssetDataTrue(assetArray)) {
            strAsset.append("All");
        }
        else {
            for (int i=0;i<assetArray.size();i++) {
                if (assetArray.get(i).isSelected()) {
                    dataCount++;
                    if (dataCount <= 3) {
                        if (strAsset.length() == 0) {
                            strAsset.append(assetArray.get(i).getAssetName());
                        } else {
                            strAsset.append(", ");
                            strAsset.append(assetArray.get(i).getAssetName());
                        }
                    }

                }
            }
            strAsset.append(" & "+(dataCount-3)+" More");
        }
        return strAsset.toString();
    }


    public boolean isAllAssetDataTrue(ArrayList<AssetData> assetArray) {
        for(AssetData assetData:assetArray)
            if(!assetData.isSelected()) return false;
            return true;

    }

    public void onSelectAllClick(View view) {

            for(AssetData assetData:assetArray)
            {
                assetData.setSelected(CbSelectAll.isChecked());
            }

        assetAdapter.notifyDataSetChanged();
    }
}