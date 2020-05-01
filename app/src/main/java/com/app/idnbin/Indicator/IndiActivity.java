package com.app.idnbin.Indicator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.BlankFragment;

import com.app.idnbin.R;
import com.facebook.appevents.suggestedevents.ViewOnClickListener;

public class IndiActivity extends AppCompatActivity implements View.OnClickListener {
    TextView TvActivetools, TvIndicators, TvTemplate, TvOtherSettings,TV_ClearAll, TV_Confirm, TV_Cancel, TV_GT,TV_saveTemp;
    RelativeLayout RLFrame;
    ImageView IvTools, IvThin, IvMedium, IvThick, IvSetting;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indi);

        Tb_App = findViewById(R.id.Tb_App);

        TvActivetools = findViewById(R.id.TvActivetools);
        TvIndicators = findViewById(R.id.TvIndicators);
        TvTemplate = findViewById(R.id.TvTemplate);
        TvOtherSettings = findViewById(R.id.TvOtherSettings);
        RLFrame = findViewById(R.id.RLFrame);
        IvTools = findViewById(R.id.IvTools);

        TvActivetools.setOnClickListener(this);
        TvIndicators.setOnClickListener(this);
        TvTemplate.setOnClickListener(this);
        TvOtherSettings.setOnClickListener(this);
        RLFrame.setOnClickListener(this);
        IvTools.setOnClickListener(this);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Indicator");
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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        switch (v.getId()) {
            case R.id.TvActivetools:
                AlertDialog activetoolsdialog = new AlertDialog.Builder(this).create();
                activetoolsdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                activetoolsdialog.setView(getLayoutInflater().inflate(R.layout.layout_activetools, null));
                activetoolsdialog.show();

                TV_ClearAll = activetoolsdialog.findViewById(R.id.TV_ClearAll);
                TV_Confirm = activetoolsdialog.findViewById(R.id.TV_Confirm);
                TV_Cancel = activetoolsdialog.findViewById(R.id.TV_Cancel);
                TV_GT = activetoolsdialog.findViewById(R.id.TV_GT);
                IvSetting = activetoolsdialog.findViewById(R.id.IvSetting);


                IvSetting = activetoolsdialog.findViewById(R.id.IvSetting);


                IvSetting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertMethod();
                    }
                });


                TV_ClearAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        TV_GT.setVisibility(View.GONE);
                        TV_ClearAll.setVisibility(View.GONE);
                        TV_Confirm.setVisibility(View.VISIBLE);
                        TV_Cancel.setVisibility(View.VISIBLE);
                    }
                });
                TV_Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TV_GT.setVisibility(View.VISIBLE);
                        TV_ClearAll.setVisibility(View.VISIBLE);
                        TV_Confirm.setVisibility(View.GONE);
                        TV_Cancel.setVisibility(View.GONE);

                    }
                });

            case R.id.TvIndicators:

                if (RLFrame.getVisibility() == View.VISIBLE) {
                    RLFrame.setVisibility(View.GONE);
                    fragmentTransaction.replace(R.id.frameLayouts, new BlankFragment());
                } else {
                    fragmentTransaction.replace(R.id.frameLayouts, new IndicatorFragment());
                    RLFrame.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.TvTemplate:
                AlertDialog templatesdialog = new AlertDialog.Builder(this).create();
                templatesdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                templatesdialog.setView(getLayoutInflater().inflate(R.layout.layout_templates, null));
                templatesdialog.show();
                TV_saveTemp = templatesdialog.findViewById(R.id.TV_saveTemp);
                TV_saveTemp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(IndiActivity.this, CreateTemplateActivity.class));
                    }
                });
                break;


            case R.id.TvOtherSettings:
                AlertDialog othersettingssdialog = new AlertDialog.Builder(this).create();
                othersettingssdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                othersettingssdialog.setView(getLayoutInflater().inflate(R.layout.layout_othersettings, null));
                othersettingssdialog.show();
                break;


            case R.id.IvTools:
                alertMethod();
                break;
        }
        fragmentTransaction.commit();
    }

    public void alertMethod(){
        AlertDialog linedialog = new AlertDialog.Builder(this).create();
        linedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        linedialog.setView(getLayoutInflater().inflate(R.layout.layout_line, null));
        linedialog.show();

        TextView TV_icclosed = linedialog.findViewById(R.id.TV_icclosed);
        IvThin = linedialog.findViewById(R.id.IvThin);
        IvMedium = linedialog.findViewById(R.id.IvMedium);
        IvThick = linedialog.findViewById(R.id.IvThick);

        TV_icclosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linedialog.dismiss();
            }
        });

        IvThin.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                IvThin.setBackgroundResource(R.drawable.bg_border_white);
                IvMedium.setBackgroundResource(android.R.color.transparent);
                IvThick.setBackgroundResource(android.R.color.transparent);
            }
        });

        IvMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IvMedium.setBackgroundResource(R.drawable.bg_border_white);
                IvThin.setBackgroundResource(android.R.color.transparent);
                IvThick.setBackgroundResource(android.R.color.transparent);
            }
        });

        IvThick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IvThick.setBackgroundResource(R.drawable.bg_border_white);
                IvThin.setBackgroundResource(android.R.color.transparent);
                IvMedium.setBackgroundResource(android.R.color.transparent);
            }
        });
    }










}
