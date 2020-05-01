package com.app.idnbin.Profile.Deposit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.idnbin.R;

public class BitCoinNextActivity extends AppCompatActivity {

    Toolbar Tb_App;
    EditText ETAmount;
    Button btn_Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bit_coin_next);

        Tb_App = findViewById(R.id.Tb_App);
        ETAmount = findViewById(R.id.ETAmount);
        btn_Next = findViewById(R.id.btn_Next);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Withdrawal address");


        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(ETAmount.getText().toString())){
                    ETAmount.setError("Inccorrect Bitcoin wallet address");
                }
                else {
                    Toast.makeText(BitCoinNextActivity.this, "Deposited Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
