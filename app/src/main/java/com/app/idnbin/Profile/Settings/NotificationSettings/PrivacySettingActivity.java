package com.app.idnbin.Profile.Settings.NotificationSettings;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.app.idnbin.Profile.Settings.NotificationSettings.module.ReqSubBody;
import com.app.idnbin.Profile.Settings.NotificationSettings.module.ResSubBody;
import com.app.idnbin.R;
import com.app.idnbin.util.ApiCall;
import com.app.idnbin.util.ApiIterface;
import com.app.idnbin.util.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacySettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    Toolbar Tb_App;
    private static final String TAG = "PrivacySettingActivity";
    Switch switch_communication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_setting);

        Tb_App = findViewById(R.id.Tb_App);

        switch_communication = findViewById(R.id.switch_communication);

        switch_communication.setOnCheckedChangeListener(this);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Privacy Settings");
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

    public void setPrivacy(String status){

        ApiIterface apiIterface = ApiCall.subscriberApi().create(ApiIterface.class);
        Call<ResSubBody> call = apiIterface.subscribeMember(new ReqSubBody(status));
        call.enqueue(new Callback<ResSubBody>() {
            @Override
            public void onResponse(@NonNull Call<ResSubBody> call, @NonNull Response<ResSubBody> response) {
                Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ResSubBody> call, @NonNull Throwable t) {
                Log.d("Error", t.getMessage());


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            setPrivacy("subscribed");
            setPref(this, "switch_communication", "1");
        } else {
            setPrivacy("unsubscribed");
            setPref(this, "switch_communication", "0");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPref(this, "switch_communication").equalsIgnoreCase("1")) {
            switch_communication.setChecked(true);
        }
    }
}
