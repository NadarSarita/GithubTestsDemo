package com.app.idnbin.Profile.History.Operation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class OperationActivity extends BaseActivity {
    Toolbar Tb_App;
    CheckBox allCheckBox,depositCheckBox,withdrawalCheckBox,trebuyCheckBox,trewardCheckBox;
    private boolean isAllChecked = false;
    ArrayList<String> operationArray=new ArrayList<>(2);
    private static final String TAG = "OperationActivity";
    StringBuilder strOperation=new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        Tb_App = findViewById(R.id.Tb_App);

        allCheckBox = findViewById(R.id.allCheckBox);
        depositCheckBox = findViewById(R.id.depositCheckBox);
        withdrawalCheckBox = findViewById(R.id.withdrawalCheckBox);
       /* trebuyCheckBox = findViewById(R.id.trebuyCheckBox);
        trewardCheckBox = findViewById(R.id.trewardCheckBox);*/

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Operation");

        if (!getPref(this, "TransactionHistoryOperation").equalsIgnoreCase("null")) {
            operationArray = new Gson().fromJson(getPref(this, "TransactionHistoryOperation"), new TypeToken<ArrayList<String>>() {
            }.getType());

            if(operationArray.size()==2)
            {
                setAllCheckBox(true);
            }
            else {
                isDataPresent(operationArray);
            }
        }
        else {
            setAllCheckBox(true);
        }
    }

    private void isDataPresent(ArrayList<String> operationArray) {
        for(String str: operationArray)
        {
            if(str.equalsIgnoreCase("Deposit"))
            {
                depositCheckBox.setChecked(true);
            }
            else if(str.equalsIgnoreCase("Withdrawal"))
            {
                withdrawalCheckBox.setChecked(true);
            }
          /*  else if(str.equalsIgnoreCase("Tournament rebuy"))
            {
                trebuyCheckBox.setChecked(true);
            }
            else if(str.equalsIgnoreCase("Tournament reward"))
            {
                trewardCheckBox.setChecked(true);
            }*/
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        passIntentData();
        onBackPressed();
        return true;
    }

    private void passIntentData() {
        setPref(this, "TransactionHistoryOperation", new Gson().toJson(operationArray));
        if(operationArray==null || operationArray.size()==2)
        {
            strOperation.append("All");
        }
        else {
            for (int i=0;i<operationArray.size();i++)
            {
                if(i==0)
                {
                    strOperation.append(operationArray.get(i));
                }
                else {
                    strOperation.append(", ");
                    strOperation.append(operationArray.get(i));
                }
            }
        }
        Intent intent=new Intent();
        intent.putExtra("HistoryOperation",strOperation.toString());
        setResult(2,intent);
    }

    public void onCheckBoxClick(View view){
        boolean checked = ((CheckBox) view).isChecked();
        String operationValue=((CheckBox) view).getText().toString();

        if(checked && operationValue.equalsIgnoreCase("All"))
        {
            setAllCheckBox(true);
        }
        else if(checked && !operationValue.equalsIgnoreCase("All"))
        {
            operationArray.add(operationValue);
            ((CheckBox) view).setChecked(true);
        }
        else if(!checked && !operationValue.equalsIgnoreCase("All")) {
            operationArray.remove(operationValue);
            ((CheckBox) view).setChecked(false);
        }
        else if(!checked && operationValue.equalsIgnoreCase("All"))
        {
            operationArray.clear();
            setAllCheckBox(false);
        }

        if(operationArray.size()==2)
        {
            allCheckBox.setChecked(true);
        }
        else {
            allCheckBox.setChecked(false);
        }

        Log.i(TAG, "onCheckBoxClick: "+operationArray.size());
        for(int i=0;i<operationArray.size();i++)
        {
            Log.i(TAG, "onCheckBoxClick: "+operationArray.get(i));
        }
    }

    private void setAllCheckBox(boolean checked) {

        allCheckBox.setChecked(checked);
        depositCheckBox.setChecked(checked);
        withdrawalCheckBox.setChecked(checked);
      /*  trebuyCheckBox.setChecked(checked);
        trewardCheckBox.setChecked(checked);*/
        if(checked) {
            operationArray = new ArrayList<>(Arrays.asList("Deposit", "Withdrawal"));
        }
        else {
            operationArray.clear();
        }
    }
}