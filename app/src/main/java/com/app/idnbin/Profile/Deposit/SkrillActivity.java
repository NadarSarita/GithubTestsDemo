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

public class SkrillActivity extends AppCompatActivity implements View.OnClickListener {

    TextView TvAmt1,TvAmt2,TvAmt3,TvAmt4,TvAmt5,TvAmount;
    Button BtnContinue;
    Spinner SpAmount;

    EditText ETAmount;
    String spinnertext;
    String first;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skrill);


        Tb_App = findViewById(R.id.Tb_App);
        TvAmt1 = findViewById(R.id.TvAmt1);
        TvAmt2 = findViewById(R.id.TvAmt2);
        TvAmt3 = findViewById(R.id.TvAmt3);
        TvAmt4 = findViewById(R.id.TvAmt4);
        TvAmt5 = findViewById(R.id.TvAmt5);
        ETAmount = findViewById(R.id.ETAmount);
        BtnContinue = findViewById(R.id.BtnContinue);
        SpAmount = findViewById(R.id.SpAmount);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Skrill");

        TvAmt1.setOnClickListener(this);
        TvAmt2.setOnClickListener(this);
        TvAmt3.setOnClickListener(this);
        TvAmt4.setOnClickListener(this);
        TvAmt5.setOnClickListener(this);
        BtnContinue.setOnClickListener(this);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_text, getResources().getStringArray(R.array.currency));
        adapter.setDropDownViewResource(R.layout.spinner_text);
        SpAmount.setAdapter(adapter);
        SpAmount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                spinnertext = SpAmount.getSelectedItem().toString();
                 first = spinnertext.substring(0, spinnertext.length() / 2);
                {

                    switch (position) {
                        case 0: // for item 1
                            TvAmt1.setText(first+"250");
                            TvAmt2.setText(first+"500");
                            TvAmt3.setText(first+"1000");
                            TvAmt4.setText(first+"3000");
                            TvAmt5.setText(first+"5000");
                            BtnContinue.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                            break;

                        case 1: // for item 1
                            TvAmt1.setText(first+"250");
                            TvAmt2.setText(first+"500");
                            TvAmt3.setText(first+"1000");
                            TvAmt4.setText(first+"3000");
                            TvAmt5.setText(first+"5000");
                            BtnContinue.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                            break;

                        case 2:
                            TvAmt1.setText(first+"250");
                            TvAmt2.setText(first+"500");
                            TvAmt3.setText(first+"1000");
                            TvAmt4.setText(first+"3000");
                            TvAmt5.setText(first+"5000");
                            BtnContinue.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                            break;
                        case 3:
                            TvAmt1.setText("250" +first);
                            TvAmt2.setText("500" +first);
                            TvAmt3.setText("1000" +first);
                            TvAmt4.setText("3000" +first);
                            TvAmt5.setText("5000" +first);
                            BtnContinue.setText("DEPOSIT " +ETAmount.getText().toString()+""+first);
                            break;



                    }
                }
                // your code here
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(SkrillActivity.this, "", Toast.LENGTH_SHORT).show();
                // your code here
            }

        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.TvAmt1:
                ETAmount.setText("250");
                BtnContinue.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                break;
            case R.id.TvAmt2:
                ETAmount.setText("500");
                BtnContinue.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                break;
            case R.id.TvAmt3:
                ETAmount.setText("1000");
                BtnContinue.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                break;
            case R.id.TvAmt4:
                ETAmount.setText("3000");
                BtnContinue.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                break;
            case R.id.TvAmt5:
                ETAmount.setText("5000");
                BtnContinue.setText("DEPOSIT " +""+first+ "" +ETAmount.getText().toString());
                break;
            case  R.id.BtnContinue:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://account.skrill.com/wallet/account/login?locale=en"));
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
