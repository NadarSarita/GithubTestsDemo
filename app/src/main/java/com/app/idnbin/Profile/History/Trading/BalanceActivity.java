package com.app.idnbin.Profile.History.Trading;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;

public class BalanceActivity extends BaseActivity implements View.OnClickListener {
    Toolbar Tb_App;
    RadioButton rb_real, rb_practice;
    String strBalance="Real";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        Tb_App = findViewById(R.id.Tb_App);


        rb_real = findViewById(R.id.rb_real);
        rb_practice = findViewById(R.id.rb_practice);

        rb_real.setOnClickListener(this);
        rb_practice.setOnClickListener(this);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Balance");

        if (!getPref(this, "HistoryTradingBalance").equalsIgnoreCase("null")) {
            strBalance = getPref(this, "HistoryTradingBalance");
            setBalanceChecked(strBalance);
        }
        else {
            rb_real.setChecked(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        passIntentData();
        onBackPressed();
        return true;
    }

    private void passIntentData() {
        setPref(this, "HistoryTradingBalance", strBalance);
        Intent intent=new Intent();
        intent.putExtra("HistoryTradingBalance",strBalance);
        setResult(2,intent);
    }


    private void setBalanceChecked(String strBalance) {
        switch (strBalance)
        {
            case "Real":
                rb_real.setChecked(true);
                break;
            case "Practice":
                rb_practice.setChecked(true);
                break;
        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.rb_real:
                strBalance="Real";
                break;

            case R.id.rb_practice:
                strBalance="Practice";
                break;
        }
    }
}