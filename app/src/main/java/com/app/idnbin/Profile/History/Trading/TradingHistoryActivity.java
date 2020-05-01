package com.app.idnbin.Profile.History.Trading;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.idnbin.Profile.History.Operation.DateActivity;
import com.app.idnbin.Profile.History.Operation.OperationsHistoryActivity;
import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class TradingHistoryActivity extends BaseActivity implements View.OnClickListener {
    LinearLayout LLInstrumentType,LLBalance,LLDate,LLAsset;
    TextView TvOperationValue,TvBalanceValue,TvDateValue, TvAssetValue;
    ArrayList<String> operationArray=new ArrayList<>();
    ArrayList<String> statusArray=new ArrayList<>();
    ArrayList<AssetData> assetArray=new ArrayList<>();
    ArrayList<String> dateArray=new ArrayList<>();
    StringBuilder strOperation=new StringBuilder();
    StringBuilder strAsset=new StringBuilder();
    String strDate="All Time";
    String strBalance="Real";
    private static final String TAG = "TradingHistoryActivity";
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trading_history);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Trading History");

        LLInstrumentType = findViewById(R.id.LLInstrumentType);
        LLBalance = findViewById(R.id.LLBalance);
        LLDate =findViewById(R.id.LLDate);
        LLAsset = findViewById(R.id.LLAsset);
        TextView TvApply = findViewById(R.id.TvApply);

        TvOperationValue=findViewById(R.id.TvOperationValue);
        TvDateValue=findViewById(R.id.TvDateValue);
        TvBalanceValue=findViewById(R.id.TvBalanceValue);
        TvAssetValue=findViewById(R.id.TvAssetValue);

        if (!getPref(this, "TradingInstrument").equalsIgnoreCase("null")) {
            operationArray = new Gson().fromJson(getPref(this, "TradingInstrument"), new TypeToken<ArrayList<String>>() {
            }.getType());

            if (operationArray == null || operationArray.size()==0 || operationArray.size() == 4) {
                strOperation.append("All");
            } else {
                for (int i = 0; i < operationArray.size(); i++) {
                    if (i == 0) {
                        strOperation.append(operationArray.get(i));
                    } else {
                        strOperation.append(", ");
                        strOperation.append(operationArray.get(i));
                    }
                }
            }
            TvOperationValue.setText(strOperation);
        }
        else {
            TvOperationValue.setText("All Instruments");
        }

      /*  if (!getPref(this, "TransactionHistoryStatus").equalsIgnoreCase("null")) {
            statusArray = new Gson().fromJson(getPref(this, "TransactionHistoryStatus"), new TypeToken<ArrayList<String>>() {
            }.getType());


            if (statusArray == null || statusArray.size()==0 || statusArray.size() == 4) {

                strStatus.append("All");
            }
            else {
                for (int i=0;i<statusArray.size();i++)
                {
                    if(i==0)
                    {
                        strStatus.append(statusArray.get(i));
                    }
                    else {
                        strStatus.append(", ");
                        strStatus.append(statusArray.get(i));
                    }
                }
            }
            TvStatusValue.setText(strStatus);
        }
        else {
            TvStatusValue.setText("All");
        }
*/
        if (!getPref(this, "TradingAsset").equalsIgnoreCase("null")) {
            assetArray = new Gson().fromJson(getPref(this, "TradingAsset"), new TypeToken<ArrayList<AssetData>>() {
            }.getType());



            TvAssetValue.setText(txtToDisplay(assetArray));
        }
        else {
            TvAssetValue.setText("All");
        }

        if (!getPref(this, "TradingHistoryDate").equalsIgnoreCase("null")) {
            strDate = getPref(this, "TradingHistoryDate");

            TvDateValue.setText(strDate);
        }
        else {
            TvDateValue.setText("All Time");
        }


        if (!getPref(this, "HistoryTradingBalance").equalsIgnoreCase("null")) {
            strBalance = getPref(this, "HistoryTradingBalance");

            TvBalanceValue.setText(strBalance);
        }
        else {
            TvBalanceValue.setText("Real");
        }


        LLInstrumentType.setOnClickListener(this);
        LLBalance.setOnClickListener(this);
        LLDate.setOnClickListener(this);
        LLAsset.setOnClickListener(this);
        TvApply.setOnClickListener(this);
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.LLInstrumentType:
                startActivityForResult(new Intent(this, InstrumentTypeActivity.class),1);
                break;
            case R.id.LLBalance:
                startActivityForResult(new Intent(this, BalanceActivity.class),2);
                break;
            case R.id.LLAsset:
                startActivityForResult(new Intent(this, AssetActivity.class),4);
                break;
            case R.id.LLDate:
                Intent intent=new Intent(this,DateActivity.class);
                intent.putExtra("SourceActivity", "TradingHistoryActivity");
                startActivityForResult(intent,3);
                break;
            case R.id.TvApply:
                startActivity(new Intent(this, TradHistoryActivity.class));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {
            if(!data.getExtras().getString("TradingInstrument").equalsIgnoreCase(""))
            {
                TvOperationValue.setText(data.getExtras().getString("TradingInstrument"));
            }
            else {
                TvOperationValue.setText("All Instruments");
            }
        }
        else if(requestCode==2)
        {
            if(!data.getExtras().getString("HistoryTradingBalance").equalsIgnoreCase(""))
            {
                TvBalanceValue.setText(data.getExtras().getString("HistoryTradingBalance"));
            }
            else {
                TvBalanceValue.setText("Real");
            }
        }
        else if(requestCode==4)
        {
            if(!data.getExtras().getString("TradingAsset").equalsIgnoreCase(""))
            {
                TvAssetValue.setText(data.getExtras().getString("TradingAsset"));
            }
            else {
                TvAssetValue.setText("All");
            }
        }
        else if(requestCode==3)
        {
            if(!data.getExtras().getString("TradingHistoryDate").equalsIgnoreCase(""))
            {
                TvDateValue.setText(data.getExtras().getString("TradingHistoryDate"));
            }
            else {
                TvDateValue.setText("All Time");
            }
        }
    }
}
