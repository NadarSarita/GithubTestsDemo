package com.app.idnbin.Profile.MyProfile;

import androidx.annotation.NonNull;
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

public class UserProfileActivity extends BaseActivity implements View.OnClickListener {

    Toolbar Tb_App;
    TextView Tv_Edit,tv_email;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Tb_App = findViewById(R.id.Tb_App);
        tv_email = findViewById(R.id.tv_email);
        Tv_Edit = findViewById(R.id.Tv_Edit);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("My Profile");

        email = getPref(this, "username");

        tv_email.setText(email);

        Tv_Edit.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.Tv_Edit:
                startActivity(new Intent(this,EditProfileActivity.class));
                break;
        }
    }
}
