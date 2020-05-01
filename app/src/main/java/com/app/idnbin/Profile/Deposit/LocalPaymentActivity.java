package com.app.idnbin.Profile.Deposit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.idnbin.R;

public class LocalPaymentActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar Tb_App;
    Button btn_deposit;
    EditText Et_BankName, Et_AccountName, Et_AccountNumber, Et_AccountType, Et_Country, Et_DepositAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_payment);

        Tb_App = findViewById(R.id.Tb_App);
        btn_deposit = findViewById(R.id.btn_deposit);
        Et_BankName = findViewById(R.id.Et_BankName);
        Et_AccountName = findViewById(R.id.Et_AccountName);
        Et_AccountNumber = findViewById(R.id.Et_AccountNumber);
        Et_AccountType = findViewById(R.id.Et_AccountType);
        Et_Country = findViewById(R.id.Et_Country);
        Et_DepositAmount = findViewById(R.id.Et_DepositAmount);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Local Payment Gateway");

        btn_deposit.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.btn_deposit:
                if (TextUtils.isEmpty(Et_BankName.getText().toString())) {
                    Et_BankName.setError("Enter your bank name");
                } else {
                    if (TextUtils.isEmpty(Et_AccountName.getText().toString())) {
                        Et_AccountName.setError("Enter your account name");
                    } else {
                        if (TextUtils.isEmpty(Et_AccountNumber.getText().toString())) {
                            Et_AccountNumber.setError("Enter your account number");
                        } else {
                            if (TextUtils.isEmpty(Et_AccountType.getText().toString())) {
                                Et_AccountType.setError("Enter your account type");
                            } else {
                                if (TextUtils.isEmpty(Et_Country.getText().toString())) {
                                    Et_Country.setError("Enter your country");
                                } else {
                                    if (TextUtils.isEmpty(Et_DepositAmount.getText().toString())) {
                                        Et_DepositAmount.setError("Enter deposit amount");
                                    } else {
                                        Toast.makeText(this, "Deposited successfully", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    }
                }
                break;
        }
    }
}
