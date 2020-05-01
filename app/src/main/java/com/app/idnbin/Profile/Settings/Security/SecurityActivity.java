package com.app.idnbin.Profile.Settings.Security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;

public class SecurityActivity extends BaseActivity implements View.OnClickListener {

    TextView TvAuthentication, TvPasscode, TvActivesessions, TvChangePassword;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Security");

        TvAuthentication = findViewById(R.id.TvAuthentication);
        TvPasscode = findViewById(R.id.TvPasscode);
        TvActivesessions = findViewById(R.id.TvActivesessions);
        TvChangePassword = findViewById(R.id.TvChangePassword);

        if (getPref(this,"NormalLogin").equalsIgnoreCase("true")){
            TvChangePassword.setVisibility(View.VISIBLE);
        }

        TvAuthentication.setOnClickListener(this);
        TvPasscode.setOnClickListener(this);
        TvActivesessions.setOnClickListener(this);
        TvChangePassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.TvAuthentication:
                Intent intent = new Intent(this, AuthenticationActivity.class);
                startActivity(intent);
                break;
            case R.id.TvPasscode:
                Intent intentPasscode = new Intent(this, PasscodeActivity.class);
                startActivity(intentPasscode);
                break;
            case R.id.TvActivesessions:
                Intent intentActive = new Intent(this, ActiveSessionsActivity.class);
                startActivity(intentActive);
                break;
            case R.id.TvChangePassword:
                Intent intentChangePass = new Intent(this, ChangePasswordActivity.class);
                startActivity(intentChangePass);
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
