package com.app.idnbin.Profile.Deposit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.R;

public class WebMoneyWmeActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_webMoney_Deposit;
    TextView TvAmt ,TvAmt1,TvAmt2,TvAmt3,TvAmt4,TvAmt5;
    EditText ETAmount;
    Spinner SpAmount_WebMoney;
    String spinnertext, first;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_money_wme);

        TvAmt=findViewById(R.id.TvAmt);
        TvAmt1=findViewById(R.id.TvAmt1);
        TvAmt2=findViewById(R.id.TvAmt2);
        TvAmt3=findViewById(R.id.TvAmt3);
        TvAmt4=findViewById(R.id.TvAmt4);
        TvAmt5=findViewById(R.id.TvAmt5);
        ETAmount=findViewById(R.id.ETAmount);
        SpAmount_WebMoney=findViewById(R.id.SpAmount_WebMoney);
        btn_webMoney_Deposit=findViewById(R.id.btn_webMoney_Deposit);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("WebMoney WME");


        TvAmt.setOnClickListener(this);
        TvAmt1.setOnClickListener(this);
        TvAmt2.setOnClickListener(this);
        TvAmt3.setOnClickListener(this);
        TvAmt4.setOnClickListener(this);
        TvAmt5.setOnClickListener(this);
        ETAmount.setOnClickListener(this);
        btn_webMoney_Deposit.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_text, getResources().getStringArray(R.array.currency));
        adapter.setDropDownViewResource(R.layout.spinner_text);
        SpAmount_WebMoney.setAdapter(adapter);

        SpAmount_WebMoney.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinnertext = SpAmount_WebMoney.getSelectedItem().toString();
                first = spinnertext.substring(0, spinnertext.length() / 2);
                {
                    switch (position) {
                        case 0: // for item 1
                            TvAmt1.setText(first+"250");
                            TvAmt2.setText(first+"500");
                            TvAmt3.setText(first+"1000");
                            TvAmt4.setText(first+"3000");
                            TvAmt5.setText(first+"5000");
                            btn_webMoney_Deposit.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                            break;

                        case 1: // for item 1
                            TvAmt1.setText(first+"250");
                            TvAmt2.setText(first+"500");
                            TvAmt3.setText(first+"1000");
                            TvAmt4.setText(first+"3000");
                            TvAmt5.setText(first+"5000");
                            btn_webMoney_Deposit.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                            break;

                        case 2:
                            TvAmt1.setText(first+"250");
                            TvAmt2.setText(first+"500");
                            TvAmt3.setText(first+"1000");
                            TvAmt4.setText(first+"3000");
                            TvAmt5.setText(first+"5000");
                            btn_webMoney_Deposit.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                            break;
                        case 3:
                            TvAmt1.setText("250" +first);
                            TvAmt2.setText("500" +first);
                            TvAmt3.setText("1000" +first);
                            TvAmt4.setText("3000" +first);
                            TvAmt5.setText("5000" +first);
                            btn_webMoney_Deposit.setText("DEPOSIT " +ETAmount.getText().toString()+" "+first);
                            break;



                    }
                }
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
                btn_webMoney_Deposit.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                break;
            case R.id.TvAmt2:
                ETAmount.setText("500");
                btn_webMoney_Deposit.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                break;
            case R.id.TvAmt3:
                ETAmount.setText("1000");
                btn_webMoney_Deposit.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                break;
            case R.id.TvAmt4:
                ETAmount.setText("3000");
                btn_webMoney_Deposit.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                break;
            case R.id.TvAmt5:
                ETAmount.setText("5000");
                btn_webMoney_Deposit.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                break;
            case R.id.btn_webMoney_Deposit:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://login.wmtransfer.com/GateKeeper/Password/be59ef07-23d3-4299-8eb8-edc647b2d2f2.aspx"));
                startActivity(intent);
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
