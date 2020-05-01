package com.app.idnbin.Profile.Deposit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.app.idnbin.R;

public class DepositActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar Tb_App;
    CardView Cv_Local,Cv_Visa,Cv_Skrill,Cv_bitcoin,Cv_Astropaycard,Cv_AdvCash,Cv_WebMoneyWMZ,Cv_Qiwi,Cv_WebMoneyWME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        Tb_App = findViewById(R.id.Tb_App);

        Cv_Local = findViewById(R.id.Cv_Local);
        Cv_Visa = findViewById(R.id.Cv_Visa);
        Cv_bitcoin = findViewById(R.id.Cv_bitcoin);
        Cv_Skrill = findViewById(R.id.Cv_Skrill);
        Cv_Astropaycard = findViewById(R.id.Cv_Astropaycard);
        Cv_AdvCash = findViewById(R.id.Cv_AdvCash);
        Cv_WebMoneyWMZ = findViewById(R.id.Cv_WebMoneyWMZ);
        Cv_Qiwi = findViewById(R.id.Cv_Qiwi);
        Cv_WebMoneyWME = findViewById(R.id.Cv_WebMoneyWME);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Deposit");

        Cv_Local.setOnClickListener(this);
        Cv_Visa.setOnClickListener(this);
        Cv_bitcoin.setOnClickListener(this);
        Cv_Skrill.setOnClickListener(this);
        Cv_Astropaycard.setOnClickListener(this);
        Cv_AdvCash.setOnClickListener(this);
        Cv_WebMoneyWMZ.setOnClickListener(this);
        Cv_Qiwi.setOnClickListener(this);
        Cv_WebMoneyWME.setOnClickListener(this);
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
            case R.id.Cv_Local:
                startActivity(new Intent(this, LocalPaymentActivity.class));
                break;
            case R.id.Cv_Visa:
                startActivity(new Intent(this, VisaCardActivity.class));
                break;
            case R.id.Cv_bitcoin:
                startActivity(new Intent(this, BitCoinActivity.class));
                break;
            case R.id.Cv_Skrill:
                startActivity(new Intent(this, SkrillActivity.class));
                break;
            case R.id.Cv_Astropaycard:
                startActivity(new Intent(this, AstroPayCardActivity.class));
                break;
            case R.id.Cv_AdvCash:
                startActivity(new Intent(this, AdvCashActivity.class));
                break;
            case R.id.Cv_WebMoneyWMZ:
                startActivity(new Intent(this, WebMoneyWmzActivity.class));
                break;
            case R.id.Cv_Qiwi:
                startActivity(new Intent(this, QiwiActivity.class));
                break;
            case R.id.Cv_WebMoneyWME:
                startActivity(new Intent(this, WebMoneyWmeActivity.class));
                break;
        }
    }
}
