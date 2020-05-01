package com.app.idnbin.Profile.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;

public class TraderoomSettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private Switch Switch_ExpirationPanel, Switch_TraderSentiments, Switch_LiveDeals, Switch_SharemyLiveDeals, Switch_ClosedDealsonChartOptions, Switch_ClosedDealsonChartFCC, Switch_Sound, Switch_InvestmentAmount, Switch_ShowHighLowonChart, Switch_BuyinOneClickOptions, Switch_BuywithconfirmationForex, Switch_UseBalancetokeepposition, Switch_trailingStop, Switch_ShwoNotificationaboutexecution, Switch_ShowPriceMovements;

    private AlertDialog alert11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traderoom_setting);

        Toolbar tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tb_App.setTitle("Trade Settings");

        Switch_ExpirationPanel = findViewById(R.id.Switch_ExpirationPanel);
        Switch_TraderSentiments = findViewById(R.id.Switch_TraderSentiments);
        Switch_LiveDeals = findViewById(R.id.Switch_LiveDeals);
        Switch_SharemyLiveDeals = findViewById(R.id.Switch_SharemyLiveDeals);
        Switch_ClosedDealsonChartOptions = findViewById(R.id.Switch_ClosedDealsonChartOptions);
        Switch_ClosedDealsonChartFCC = findViewById(R.id.Switch_ClosedDealsonChartFCC);
        Switch_Sound = findViewById(R.id.Switch_Sound);
        Switch_InvestmentAmount = findViewById(R.id.Switch_InvestmentAmount);
        Switch_ShowHighLowonChart = findViewById(R.id.Switch_ShowHighLowonChart);
        Switch_BuyinOneClickOptions = findViewById(R.id.Switch_BuyinOneClickOptions);
        Switch_BuywithconfirmationForex = findViewById(R.id.Switch_BuywithconfirmationForex);
        Switch_UseBalancetokeepposition = findViewById(R.id.Switch_UseBalancetokeepposition);
        Switch_trailingStop = findViewById(R.id.Switch_trailingStop);
        Switch_ShwoNotificationaboutexecution = findViewById(R.id.Switch_ShwoNotificationaboutexecution);
        Switch_ShowPriceMovements = findViewById(R.id.Switch_ShowPriceMovements);

        Switch_ExpirationPanel.setOnCheckedChangeListener(this);
        Switch_TraderSentiments.setOnCheckedChangeListener(this);
        Switch_LiveDeals.setOnCheckedChangeListener(this);
        Switch_SharemyLiveDeals.setOnCheckedChangeListener(this);
        Switch_ClosedDealsonChartOptions.setOnCheckedChangeListener(this);
        Switch_ClosedDealsonChartFCC.setOnCheckedChangeListener(this);
        Switch_Sound.setOnCheckedChangeListener(this);
        Switch_InvestmentAmount.setOnCheckedChangeListener(this);
        Switch_ShowHighLowonChart.setOnCheckedChangeListener(this);
        Switch_BuyinOneClickOptions.setOnCheckedChangeListener(this);
        Switch_BuywithconfirmationForex.setOnCheckedChangeListener(this);
        Switch_UseBalancetokeepposition.setOnCheckedChangeListener(this);
        Switch_trailingStop.setOnCheckedChangeListener(this);
        Switch_ShwoNotificationaboutexecution.setOnCheckedChangeListener(this);
        Switch_ShowPriceMovements.setOnCheckedChangeListener(this);
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
        switch (buttonView.getId()) {
            case R.id.Switch_ExpirationPanel:
                if (isChecked) {
                    setPref(this, "Switch_ExpirationPanel", "1");
                } else {
                    setPref(this, "Switch_ExpirationPanel", "0");
                }
                break;
            case R.id.Switch_TraderSentiments:
                if (isChecked) {
                    setPref(this, "Switch_TraderSentiments", "1");
                } else {
                    setPref(this, "Switch_TraderSentiments", "0");
                }
                break;
            case R.id.Switch_LiveDeals:
                if (isChecked) {
                    setPref(this, "Switch_LiveDeals", "1");
                } else {
                    setPref(this, "Switch_LiveDeals", "0");
                }
                break;
            case R.id.Switch_SharemyLiveDeals:
                if (isChecked) {
                    setPref(this, "Switch_SharemyLiveDeals", "1");
                } else {
                    setPref(this, "Switch_SharemyLiveDeals", "0");
                }
                break;
            case R.id.Switch_ClosedDealsonChartOptions:
                if (isChecked) {
                    setPref(this, "Switch_ClosedDealsonChartOptions", "1");
                } else {
                    setPref(this, "Switch_ClosedDealsonChartOptions", "0");
                }
                break;
            case R.id.Switch_ClosedDealsonChartFCC:
                if (isChecked) {
                    setPref(this, "Switch_ClosedDealsonChartFCC", "1");
                } else {
                    setPref(this, "Switch_ClosedDealsonChartFCC", "0");
                }
                break;
            case R.id.Switch_Sound:
                AlertDialog.Builder soundalert = new AlertDialog.Builder(this,R.style.AlertDialog);
                soundalert.setTitle("Application Sound");
                soundalert.setMessage("Turn On / Off System Tap Sounds from Settings");
                soundalert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_SOUND_SETTINGS);
                                startActivity(intent);
                            }
                        });
                soundalert.setNegativeButton(android.R.string.no, null);
                soundalert.setIcon(android.R.drawable.ic_dialog_alert);
                alert11 = soundalert.create();
                alert11.show();

                checkSound();

                break;
            case R.id.Switch_InvestmentAmount:
                if (isChecked) {
                    setPref(this, "Switch_InvestmentAmount", "1");
                } else {
                    setPref(this, "Switch_InvestmentAmount", "0");
                }
                break;
            case R.id.Switch_ShowHighLowonChart:
                if (isChecked) {
                    setPref(this, "Switch_ShowHighLowonChart", "1");
                } else {
                    setPref(this, "Switch_ShowHighLowonChart", "0");
                }
                break;
            case R.id.Switch_BuyinOneClickOptions:
                if (isChecked) {
                    setPref(this, "Switch_BuyinOneClickOptions", "1");
                } else {
                    setPref(this, "Switch_BuyinOneClickOptions", "0");
                }
                break;
            case R.id.Switch_BuywithconfirmationForex:
                if (isChecked) {
                    setPref(this, "Switch_BuywithconfirmationForex", "1");
                } else {
                    setPref(this, "Switch_BuywithconfirmationForex", "0");
                }
                break;
            case R.id.Switch_UseBalancetokeepposition:
                if (isChecked) {
                    setPref(this, "Switch_UseBalancetokeepposition", "1");
                } else {
                    setPref(this, "Switch_UseBalancetokeepposition", "0");
                }
                break;
            case R.id.Switch_trailingStop:
                if (isChecked) {
                    setPref(this, "Switch_trailingStop", "1");
                } else {
                    setPref(this, "Switch_trailingStop", "0");
                }
                break;
            case R.id.Switch_ShwoNotificationaboutexecution:
                if (isChecked) {
                    setPref(this, "Switch_ShwoNotificationaboutexecution", "1");
                } else {
                    setPref(this, "Switch_ShwoNotificationaboutexecution", "0");
                }
                break;
            case R.id.Switch_ShowPriceMovements:
                if (isChecked) {
                    setPref(this, "Switch_ShowPriceMovements", "1");
                } else {
                    setPref(this, "Switch_ShowPriceMovements", "0");
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkSetting();

        if (alert11 != null && alert11.isShowing()){
            alert11.dismiss();
        }
    }

    private void checkSetting() {
        if (getPref(this, "Switch_ExpirationPanel").equalsIgnoreCase("1")) {
            Switch_ExpirationPanel.setChecked(true);
        }

        if (getPref(this, "Switch_TraderSentiments").equalsIgnoreCase("1")) {
            Switch_TraderSentiments.setChecked(true);
        }

        if (getPref(this, "Switch_LiveDeals").equalsIgnoreCase("1")) {
            Switch_LiveDeals.setChecked(true);
        }

        if (getPref(this, "Switch_SharemyLiveDeals").equalsIgnoreCase("1")) {
            Switch_SharemyLiveDeals.setChecked(true);
        }

        if (getPref(this, "Switch_ClosedDealsonChartOptions").equalsIgnoreCase("1")) {
            Switch_ClosedDealsonChartOptions.setChecked(true);
        }

        if (getPref(this, "Switch_ClosedDealsonChartFCC").equalsIgnoreCase("1")) {
            Switch_ClosedDealsonChartFCC.setChecked(true);
        }

        checkSound();

        if (getPref(this, "Switch_InvestmentAmount").equalsIgnoreCase("1")) {
            Switch_InvestmentAmount.setChecked(true);
        }

        if (getPref(this, "Switch_ShowHighLowonChart").equalsIgnoreCase("1")) {
            Switch_ShowHighLowonChart.setChecked(true);
        }

        if (getPref(this, "Switch_BuyinOneClickOptions").equalsIgnoreCase("1")) {
            Switch_BuyinOneClickOptions.setChecked(true);
        }

        if (getPref(this, "Switch_BuywithconfirmationForex").equalsIgnoreCase("1")) {
            Switch_BuywithconfirmationForex.setChecked(true);
        }

        if (getPref(this, "Switch_UseBalancetokeepposition").equalsIgnoreCase("1")) {
            Switch_UseBalancetokeepposition.setChecked(true);
        }

        if (getPref(this, "Switch_trailingStop").equalsIgnoreCase("1")) {
            Switch_trailingStop.setChecked(true);
        }

        if (getPref(this, "Switch_ShwoNotificationaboutexecution").equalsIgnoreCase("1")) {
            Switch_ShwoNotificationaboutexecution.setChecked(true);
        }

        if (getPref(this, "Switch_ShowPriceMovements").equalsIgnoreCase("1")) {
            Switch_ShowPriceMovements.setChecked(true);
        }
    }

    private void checkSound(){
        boolean isTouchSoundsEnabled = Settings.System.getInt(getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED, 1) != 0;
        if (isTouchSoundsEnabled) {
            Switch_Sound.setChecked(true);
        } else {
            Switch_Sound.setChecked(false);
        }
    }
}