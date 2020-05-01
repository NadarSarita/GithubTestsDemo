package com.app.idnbin.Profile.Deposit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.R;

public class BitCoinActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ETAmount;
    Spinner SpAmount_bitcoin;
    TextView TvAmount2 , TV_bitcoin_rate;
    TextView TvAmt1,TvAmt2,TvAmt3,TvAmt4,TvAmt5;
    String first,spinnertext;
    Button btn_bitcoin_Next;
    SeekBar SbTrader;
    TextView TV_rate;
    TextView TV_bitcoin;
    String t;

    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bit_coin);


        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Bitcoin");

        TvAmt1=findViewById(R.id.TvAmt1);
        TvAmt2=findViewById(R.id.TvAmt2);
        TvAmt3=findViewById(R.id.TvAmt3);
        TvAmt4=findViewById(R.id.TvAmt4);
        TvAmt5=findViewById(R.id.TvAmt5);
        ETAmount=findViewById(R.id.ETAmount);
        TvAmount2=findViewById(R.id.TvAmount2);
        btn_bitcoin_Next=findViewById(R.id.btn_bitcoin_Next);
        TV_bitcoin_rate=findViewById(R.id.TV_bitcoin_rate);
        SpAmount_bitcoin=findViewById(R.id.SpAmount_bitcoin);
        SbTrader = findViewById(R.id.SbTrader);
        TV_rate=findViewById(R.id.TV_rate);
        TV_bitcoin=findViewById(R.id.TV_bitcoin);


        TvAmt1.setOnClickListener(this);
        TvAmt2.setOnClickListener(this);
        TvAmt3.setOnClickListener(this);
        TvAmt4.setOnClickListener(this);
        TvAmt5.setOnClickListener(this);
        btn_bitcoin_Next.setOnClickListener(this);
        ETAmount.setOnClickListener(this);
        TV_bitcoin_rate.setOnClickListener(this);
        SbTrader.setOnClickListener(this);
        TV_rate.setOnClickListener(this);
        TV_bitcoin.setOnClickListener(this);



        String one = "to make a deposit more than ";
        String two = "<font color='#FF5722'> Verify account </font>";



        try {
            ETAmount.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {

                    try {
                        int i = Integer.parseInt(s.toString());

                        if (i >= 0 && i <= 5000) {
                            SbTrader.setProgress( i - 70); // This ensures 0-120 value for seekbar
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                public void onTextChanged(CharSequence s, int start, int before, int count) {}
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        SbTrader.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_text, getResources().getStringArray(R.array.currency));
        adapter.setDropDownViewResource(R.layout.spinner_text);
        SpAmount_bitcoin.setAdapter(adapter);


        SpAmount_bitcoin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinnertext = SpAmount_bitcoin.getSelectedItem().toString();
                first = spinnertext.substring(0, spinnertext.length() / 2);
                {
                    switch (position) {
                        case 0: // for item 1
                            TvAmt1.setText(first + "250");
                            TvAmt2.setText(first + "500");
                            TvAmt3.setText(first + "1000");
                            TvAmt4.setText(first + "3000");
                            TvAmt5.setText(first + "5000");
                            TV_bitcoin_rate.setText("1 BTC ≈ "+first+"7106.84");
                            TV_bitcoin.setText(first+ETAmount.getText()+" / "+first+"2000");
                            TV_rate.setText(Html.fromHtml( two + "" +one+""+first+"2000"));
                            break;

                        case 1: // for item 1
                            TvAmt1.setText(first + "250");
                            TvAmt2.setText(first + "500");
                            TvAmt3.setText(first + "1000");
                            TvAmt4.setText(first + "3000");
                            TvAmt5.setText(first + "5000");
                            TV_bitcoin_rate.setText("1 BTC ≈ "+first+"5489.71");
                            TV_bitcoin.setText(first+ETAmount.getText()+" / "+first+"2000");
                            TV_rate.setText(Html.fromHtml( two + "" +one+""+first+"2000"));
                            break;

                        case 2:
                            TvAmt1.setText(first + "250");
                            TvAmt2.setText(first + "500");
                            TvAmt3.setText(first + "1000");
                            TvAmt4.setText(first + "3000");
                            TvAmt5.setText(first + "5000");
                            TV_bitcoin_rate.setText("1 BTC ≈ "+first+"6242.41");
                            TV_bitcoin.setText(first+ETAmount.getText()+" / "+first+"2000");
                            TV_rate.setText(Html.fromHtml( two + "" +one+""+first+"2000"));
                            break;
                        case 3:
                            TvAmt1.setText("250" + first);
                            TvAmt2.setText("500" + first);
                            TvAmt3.setText("1000" + first);
                            TvAmt4.setText("3000" + first);
                            TvAmt5.setText("5000" + first);
                            TV_bitcoin_rate.setText("1 BTC ≈ 508621.33 "+first);
                            TV_bitcoin.setText(ETAmount.getText() +first+" / "+"2000 "+first);
                            TV_rate.setText(Html.fromHtml( two + "" +one+ 2000 +first));
                            break;


                    }}
                    }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.TvAmt1:
                ETAmount.setText("250");
                TV_bitcoin.setText(first+ETAmount.getText()+" / "+first+"2000");

                break;
            case R.id.TvAmt2:
                ETAmount.setText("500");
                TV_bitcoin.setText(first+ETAmount.getText()+" / "+first+"2000");

                break;
            case R.id.TvAmt3:
                ETAmount.setText("1000");
                TV_bitcoin.setText(first+ETAmount.getText()+" / "+first+"2000");

                break;
            case R.id.TvAmt4:
                ETAmount.setText("3000");
                TV_bitcoin.setText(first+ETAmount.getText()+" / "+first+"2000");

                break;
            case R.id.TvAmt5:
                ETAmount.setText("5000");
                TV_bitcoin.setText(first+ETAmount.getText()+" / "+first+"2000");
                break;
            case R.id.btn_bitcoin_Next:
                startActivity(new Intent(this,BitCoinNextActivity.class));
                break;
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
}
