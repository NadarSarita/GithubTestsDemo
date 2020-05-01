package com.app.idnbin.Profile.Settings.Security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthenticationActivity extends BaseActivity{

    private Toolbar Tb_App;
    @BindView(R.id.TvPhoneNumber) TextView TvPhoneNumber;
    @BindView(R.id.rlayuout1) RelativeLayout rlayuout1;
    @BindView(R.id.rlayuout2) RelativeLayout rlayuout2;
    @BindView(R.id.et_phone) EditText et_phone;
    String securityType, phone,phoneCode;
    @BindView(R.id.faSwitch)
    Switch faSwitch;
    private static final String TAG = "AuthenticationActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Authentication");

        ButterKnife.bind(this);

        SharedPreferences shared = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        securityType = shared.getString("securityType", "");
        phone= shared.getString("Phone", "");
        phoneCode= shared.getString("phoneCode", "");


        Log.d(TAG, "onCreate: "+securityType);
        if(securityType.equalsIgnoreCase("2auth"))
        {
            TvPhoneNumber.setText(phoneCode+" "+phone);
            et_phone.setText(phoneCode+" "+phone);
            faSwitch.setChecked(true);
        }
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
    @OnClick({R.id.TvPhoneNumber,R.id.BtnNext})
    public void authClick(View view){
        switch (view.getId()){
            case R.id.TvPhoneNumber:
                rlayuout1.setVisibility(View.GONE);
                rlayuout2.setVisibility(View.VISIBLE);
                break;
            case R.id.BtnNext:
                String mobile_no = et_phone.getText().toString();
                if (mobile_no.isEmpty() || mobile_no.length() < 10) {
                    et_phone.setError("Enter Valid Mobile Number");
                    et_phone.requestFocus();
                    return;
                }
                sendDataToClass(mobile_no,"+91", CodeActivity.class,"AuthenticationActivity");
                break;
        }
    }
}