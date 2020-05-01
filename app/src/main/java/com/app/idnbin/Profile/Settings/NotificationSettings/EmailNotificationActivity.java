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

import com.app.idnbin.Profile.Settings.NotificationSettings.module.ReqTagBody;
import com.app.idnbin.Profile.Settings.NotificationSettings.module.ResTagBody;
import com.app.idnbin.R;
import com.app.idnbin.util.ApiCall;
import com.app.idnbin.util.ApiIterface;
import com.app.idnbin.util.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailNotificationActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    Toolbar Tb_App;
    private static final String TAG = "EmailNotificationActivi";

    Switch switch_promo,switch_tournament,switch_system_news,switch_analytical_rpts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_notification);
        Tb_App = findViewById(R.id.Tb_App);

        switch_promo = findViewById(R.id.switch_promo);
//        switch_tournament = findViewById(R.id.switch_tournament);
        switch_system_news = findViewById(R.id.switch_system_news);
        switch_analytical_rpts = findViewById(R.id.switch_analytical_rpts);

        switch_promo.setOnCheckedChangeListener(this);
//        switch_tournament.setOnCheckedChangeListener(this);
        switch_system_news.setOnCheckedChangeListener(this);
        switch_analytical_rpts.setOnCheckedChangeListener(this);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Email Notifications");

    }

   public void setTag(String tagName, String status){
       List<TagBody> list=new ArrayList<TagBody>(Arrays.asList(new TagBody(tagName,status)));

       ApiIterface apiIterface = ApiCall.subscriberApi().create(ApiIterface.class);
        //  Call<ResMemberBody> call = apiIterface.basicLogin(new ReqMemberBody("binarybrics@gmail.com","subscribed", new MergeFields("Binary","Brics")));
        Call<ResTagBody> call = apiIterface.promotionsApi(new ReqTagBody(list));
        call.enqueue(new Callback<ResTagBody>() {
            @Override
            public void onResponse(@NonNull Call<ResTagBody> call, @NonNull Response<ResTagBody> response) {
                Log.d(TAG, "onResponse: "+response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ResTagBody> call, @NonNull Throwable t) {
                Log.d("Error", t.getMessage());


            }
        });
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.switch_promo:
                if (isChecked) {
                    setTag("Promotions","active");
                    setPref(this, "switch_promo", "1");
                } else {
                    setTag("Promotions","inactive");
                    setPref(this, "switch_promo", "0");
                }
                break;
      /*      case R.id.switch_tournament:
                if (isChecked) {
                    setPref(this, "switch_tournament", "1");
                } else {
                    setPref(this, "switch_tournament", "0");
                }
                break;*/
            case R.id.switch_system_news:
                if (isChecked) {
                    setTag("System news","active");
                    setPref(this, "switch_system_news", "1");
                } else {
                    setTag("System news","inactive");
                    setPref(this, "switch_system_news", "0");
                }
                break;
            case R.id.switch_analytical_rpts:
                if (isChecked) {
                    setTag("Analytical reports","active");
                    setPref(this, "switch_analytical_rpts", "1");
                } else {
                    setTag("Analytical reports","inactive");
                    setPref(this, "switch_analytical_rpts", "0");
                }
                break;
        }
    }

    public void checkSetting(){
        if (getPref(this, "switch_promo").equalsIgnoreCase("1")) {
            switch_promo.setChecked(true);
        }
    /*    if (getPref(this, "switch_tournament").equalsIgnoreCase("1")) {
            switch_tournament.setChecked(true);
        }*/

        if (getPref(this, "switch_system_news").equalsIgnoreCase("1")) {
            switch_system_news.setChecked(true);
        }

        if (getPref(this, "switch_analytical_rpts").equalsIgnoreCase("1")) {
            switch_analytical_rpts.setChecked(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkSetting();
    }
}
