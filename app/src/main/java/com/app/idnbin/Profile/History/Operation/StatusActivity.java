package com.app.idnbin.Profile.History.Operation;

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

public class StatusActivity extends BaseActivity {
    Toolbar Tb_App;
    private boolean isAllChceked = false;
    CheckBox allCheckBox,compelteCheckBox,inprocessCheckBox,canceledCheckBox,failedCheckBox;
    ArrayList<String> statusArray=new ArrayList<>(4);
    private static final String TAG = "StatusActivity";
    StringBuilder strStatus=new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        Tb_App = findViewById(R.id.Tb_App);
        allCheckBox =findViewById(R.id.allCheckBox);
        compelteCheckBox = findViewById(R.id.depositCheckBox);
        inprocessCheckBox =findViewById(R.id.withdrawalCheckBox);
        canceledCheckBox = findViewById(R.id.trebuyCheckBox);
        failedCheckBox = findViewById(R.id.trewardCheckBox);


        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Status");

        if (!getPref(this, "TransactionHistoryStatus").equalsIgnoreCase("null")) {
            statusArray = new Gson().fromJson(getPref(this, "TransactionHistoryStatus"), new TypeToken<ArrayList<String>>() {
            }.getType());

            if(statusArray.size()==4)
            {
                setAllCheckBox(true);
            }
            else {
                isDataPresent(statusArray);
            }
        }
        else {
            setAllCheckBox(true);
        }
    }

    private void isDataPresent(ArrayList<String> statusArray) {
        for(String str: statusArray)
        {
            if(str.equalsIgnoreCase("Completed"))
            {
                compelteCheckBox.setChecked(true);
            }
            else if(str.equalsIgnoreCase("In Process"))
            {
                inprocessCheckBox.setChecked(true);
            }
            else if(str.equalsIgnoreCase("Canceled"))
            {
                canceledCheckBox.setChecked(true);
            }
            else if(str.equalsIgnoreCase("Failed"))
            {
                failedCheckBox.setChecked(true);
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
        setPref(this, "TransactionHistoryStatus", new Gson().toJson(statusArray));
        if(statusArray==null || statusArray.size()==4)
        {
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
        Intent intent=new Intent();
        intent.putExtra("HistoryStatus",strStatus.toString());
        setResult(2,intent);
    }



    public void onCheckBoxClick(View view){
        boolean checked = ((CheckBox) view).isChecked();
        String statusValue=((CheckBox) view).getText().toString();

        if(checked && statusValue.equalsIgnoreCase("All"))
        {
            setAllCheckBox(true);
        }
        else if(checked && !statusValue.equalsIgnoreCase("All"))
        {
            statusArray.add(statusValue);
            ((CheckBox) view).setChecked(true);
        }
        else if(!checked && !statusValue.equalsIgnoreCase("All")) {
            statusArray.remove(statusValue);
            ((CheckBox) view).setChecked(false);
        }
        else if(!checked && statusValue.equalsIgnoreCase("All"))
        {
            statusArray.clear();
            setAllCheckBox(false);
        }

        if(statusArray.size()==4)
        {
            allCheckBox.setChecked(true);
        }
        else {
            allCheckBox.setChecked(false);
        }

        Log.i(TAG, "onCheckBoxClick: "+statusArray.size());
        for(int i=0;i<statusArray.size();i++)
        {
            Log.i(TAG, "onCheckBoxClick: "+statusArray.get(i));
        }


      /*  if(statusValue.equalsIgnoreCase("All"))
        {
        }
        else {
            for(int i=0;i<statusArray.size();i++)
            {
                if(statusArray.get(i).equalsIgnoreCase(statusValue))
                {
                    ((CheckBox) view).setChecked(true);
                }
            }
        }*/
      
       
      /*  switch (view.getId()){
            case R.id.allCheckBox:
                if(!allCheckBox.isChecked())
                {
                    setAllCheckBox(true);
                }
                else {
                    setAllCheckBox(false);
                }

                break;
            case R.id.depositCheckBox:
                if(!depositCheckBox.isChecked())
                {
                  allCheckBox.setChecked(false);
                }
            case R.id.withdrawalCheckBox:
                if(!withdrawalCheckBox.isChecked())
                {
                    allCheckBox.setChecked(false);
                }
                break;
            case R.id.trebuyCheckBox:
                if(!trebuyCheckBox.isChecked())
                {
                    allCheckBox.setChecked(false);
                }
                break;
            case R.id.trewardCheckBox:
                 if(!trewardCheckBox.isChecked())
            {
                allCheckBox.setChecked(false);
            }
               *//* if(!checked && isAllChecked){
                    allCheckBox.setChecked(false);
                }*//*
                break;
        }*/

    }

    private void setAllCheckBox(boolean checked) {

        allCheckBox.setChecked(checked);
        compelteCheckBox.setChecked(checked);
        inprocessCheckBox.setChecked(checked);
        canceledCheckBox.setChecked(checked);
        failedCheckBox.setChecked(checked);
        
        if(checked) {
            statusArray = new ArrayList<>(Arrays.asList("Completed", "In Process", "Canceled", "Failed"));
        }
        else {
            statusArray.clear();
        }
    }
}