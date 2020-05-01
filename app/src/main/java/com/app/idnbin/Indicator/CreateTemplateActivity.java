package com.app.idnbin.Indicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.app.idnbin.R;

public class CreateTemplateActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar Tb_App;
    Switch switch_chartsettings;
    RelativeLayout RLchartsettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_template);
        Tb_App = findViewById(R.id.Tb_App);

        switch_chartsettings =findViewById(R.id.switch_chartsettings);
        RLchartsettings = findViewById(R.id.RLchartsettings);

        switch_chartsettings.setOnClickListener(this);
        RLchartsettings.setOnClickListener(this);


        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Create Template");
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.switch_chartsettings:
                if (switch_chartsettings.isChecked())
                    RLchartsettings.setVisibility(View.GONE);
                else
                    RLchartsettings.setVisibility(View.VISIBLE);
                break;

            case R.id.RLchartsettings:
                break;

        }

    }
}
