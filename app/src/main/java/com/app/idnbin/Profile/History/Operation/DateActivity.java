package com.app.idnbin.Profile.History.Operation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateActivity extends BaseActivity implements View.OnClickListener{
    Toolbar Tb_App;
    RadioButton RB_Custom,RB_alltime,RB_day,RB_day1,RB_day2,RB_day3,RB_day4;
    LinearLayout LL_CustomDate;
    LinearLayout to_date_layout, from_date_layout;
    TextView display_from_date,display_to_date;
    static final int DATE_DIALOG_ID = 1;
    static final int TO_DIALOG_ID = 2;
    ScrollView scrollView;
    private int year,month,day;
    String strDate="All Time";
    private static final String TAG = "DateActivity";
    String sourceActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        Tb_App = findViewById(R.id.Tb_App);
        RB_Custom =findViewById(R.id.RB_Custom);
        RB_alltime =findViewById(R.id.RB_alltime);
        RB_day =findViewById(R.id.RB_day);
        RB_day1 =findViewById(R.id.RB_day1);
        RB_day2 =findViewById(R.id.RB_day2);
        RB_day3 =findViewById(R.id.RB_day3);
        RB_day4 =findViewById(R.id.RB_day4);
        scrollView =findViewById(R.id.scrollView);

        LL_CustomDate = findViewById(R.id.LL_CustomDate);
        to_date_layout=findViewById(R.id.to_date_layout);
        from_date_layout=findViewById(R.id.from_date_layout);
        display_from_date=findViewById(R.id.display_from_date);
        display_to_date=findViewById(R.id.display_to_date);

        to_date_layout.setOnClickListener(this);
        from_date_layout.setOnClickListener(this);
        to_date_layout.setOnClickListener(this);
        from_date_layout.setOnClickListener(this);
        RB_Custom.setOnClickListener(this);
        RB_alltime.setOnClickListener(this);
        RB_day.setOnClickListener(this);
        RB_day1.setOnClickListener(this);
        RB_day2.setOnClickListener(this);
        RB_day3.setOnClickListener(this);
        RB_day4.setOnClickListener(this);

        sourceActivity = getIntent().getStringExtra("SourceActivity");

        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String dateToStr = format.format(today);
        display_from_date.setText(dateToStr);
        display_to_date.setText(dateToStr);

        Boolean RadioButtonState = RB_Custom.isChecked();
        Log.i(TAG, "onCreate: "+RadioButtonState);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Date");

        if(sourceActivity.equalsIgnoreCase("TradingHistoryActivity"))
        {
            if (!getPref(this, "TradingHistoryDate").equalsIgnoreCase("null")) {

                strDate = getPref(this, "TradingHistoryDate");
                if(strDate.contains("-"))
                {
                    String[] separated = strDate.split(" - ");
                    display_from_date.setText(separated[0].trim());
                    display_to_date.setText(separated[1].trim());
                    setDateChecked("Custom");
                }
                else {
                    setDateChecked(strDate);

                }
            }
            else {
                RB_alltime.setChecked(true);
            }

        }
        else {

            if (!getPref(this, "TransactionHistoryDate").equalsIgnoreCase("null")) {
                strDate = getPref(this, "TransactionHistoryDate");
                if(strDate.contains("-"))
                {
                    String[] separated = strDate.split(" - ");
                    display_from_date.setText(separated[0].trim());
                    display_to_date.setText(separated[1].trim());
                    setDateChecked("Custom");
                }
                else {
                    setDateChecked(strDate);

                }
            }
            else {
                RB_alltime.setChecked(true);
            }
        }



    }

    private void setDateChecked(String strDate) {
        switch (strDate)
        {
            case "All Time":
                RB_alltime.setChecked(true);
                break;
            case "Today":
                RB_day.setChecked(true);
                break;
            case "Yesterday":
                RB_day1.setChecked(true);
                break;
            case "Last 7 days":
                RB_day2.setChecked(true);
                break;
            case "1 month":
                RB_day3.setChecked(true);
                break;
            case "3 Months":
                RB_day4.setChecked(true);
                break;
            case "Custom":
                RB_Custom.setChecked(true);
                break;
        }

        if(RB_Custom.isChecked())
        {
            LL_CustomDate.setVisibility(View.VISIBLE);
            scrollView.post(new Runnable() {
                @Override
                public void run() {
                    scrollView.fullScroll(View.FOCUS_DOWN);
                }
            });
        }
        else {
            LL_CustomDate.setVisibility(View.GONE);
        }


    }

    private void passIntentData() {
        if(strDate.equalsIgnoreCase("Custom"))
        {
            try {
                Date tvDateFrom = new SimpleDateFormat("MM/dd/yyyy").parse(display_from_date.getText().toString());
                Date tvDateTo = new SimpleDateFormat("MM/dd/yyyy").parse(display_to_date.getText().toString());

                Log.i(TAG, "passIntentData: "+tvDateFrom+" - "+tvDateTo);
                if (!tvDateFrom.after(tvDateTo))
                {
                    strDate=display_from_date.getText().toString()+" - "+display_to_date.getText().toString();
                    if(sourceActivity!=null && sourceActivity.equalsIgnoreCase("TradingHistoryActivity"))
                    {
                        setPref(this, "TradingHistoryDate", strDate);
                        Intent intent=new Intent();
                        intent.putExtra("TradingHistoryDate",strDate);
                        setResult(3,intent);
                    }
                    else {
                        setPref(this,"TransactionHistoryDate",strDate);
                        Intent intent=new Intent();
                        intent.putExtra("TransactionHistoryDate",strDate);
                        setResult(3,intent);
                    }
                    onBackPressed();

                }
                else {
                    Toast.makeText(DateActivity.this, "Enter proper date", Toast.LENGTH_SHORT).show();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else {

                    if(sourceActivity!=null && sourceActivity.equalsIgnoreCase("TradingHistoryActivity"))
                    {
                        setPref(this, "TradingHistoryDate", strDate);
                        Intent intent=new Intent();
                        intent.putExtra("TradingHistoryDate",strDate);
                        setResult(3,intent);
                    }
                    else {
                        setPref(this,"TransactionHistoryDate",strDate);
                        Intent intent=new Intent();
                        intent.putExtra("TransactionHistoryDate",strDate);
                        setResult(3,intent);
                    }
                    onBackPressed();


        }




    }



    @Override
    public boolean onSupportNavigateUp() {
        passIntentData();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.from_date_layout:
                showDialog(DATE_DIALOG_ID);
                break;
            case R.id.to_date_layout:
                showDialog(TO_DIALOG_ID);
                break;
            case R.id.RB_Custom:
                LL_CustomDate.setVisibility(View.VISIBLE);
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
                strDate="Custom";
                break;
            case R.id.RB_alltime:
                LL_CustomDate.setVisibility(View.GONE);
                strDate="All time";
                break;
            case R.id.RB_day:
                LL_CustomDate.setVisibility(View.GONE);
                strDate="Today";
                break;
            case R.id.RB_day1:
                LL_CustomDate.setVisibility(View.GONE);
                strDate="Yesterday";
                break;
            case R.id.RB_day2:
                LL_CustomDate.setVisibility(View.GONE);
                strDate="Last 7 days";
                break;
            case R.id.RB_day3:
                LL_CustomDate.setVisibility(View.GONE);
                strDate="1 month";
                break;
            case R.id.RB_day4:
                LL_CustomDate.setVisibility(View.GONE);
                strDate="3 Months";
                break;
        }

    }
    @Override
    protected Dialog onCreateDialog(int id) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        int mYear = c.get(java.util.Calendar.YEAR);
        int mMonth = c.get(java.util.Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        switch (id) {
            case DATE_DIALOG_ID:
                DatePickerDialog fromdatePicker=new DatePickerDialog(this, datePickerListener, mYear, mMonth,mDay);
                fromdatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
               //fromdatePicker.updateDate(mYear, mMonth, mDay);
                return fromdatePicker;
            case TO_DIALOG_ID:
                DatePickerDialog todatePicker=new DatePickerDialog(this, datePickerListener1, mYear, mMonth,mDay);
                todatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
              //  todatePicker.updateDate(mYear, mMonth, mDay);
                return todatePicker;
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth+1;
            day = selectedDay;

            Log.i(TAG, "onDateSet: "+month+" ==> "+day);
            display_from_date.setText(new StringBuilder().append(prependZero(month))
                    .append("/").append(prependZero(day)).append("/").append(year)
                    .append(" "));

        }
    };
    private DatePickerDialog.OnDateSetListener datePickerListener1
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

            year = selectedYear;
            month = selectedMonth+1;
            day = selectedDay;

            Log.i(TAG, "onDateSet: "+month+" ==> "+day);
            display_to_date.setText(new StringBuilder().append(prependZero(month))
             .append("/").append(prependZero(day)).append("/").append(year)
                    .append(" "));



        }
    };

    private String prependZero(int number) {
        if(number<9)
        {
            return "0"+number;
        }
        else {
           return ""+number;
        }
    }

}
