package com.app.idnbin.Profile.Settings.Security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.idnbin.R;

public class ActiveSessionsActivity extends AppCompatActivity {

    Toolbar Tb_App;

    RecyclerView Rvactivesessions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_sessions);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Active Sessions");

        Rvactivesessions = findViewById(R.id.Rvactivesessions);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(ActiveSessionsActivity.this);
        Rvactivesessions.setLayoutManager(layoutManager);
        ActiveAdapter activeAdapter = new ActiveAdapter(ActiveSessionsActivity.this);
        Rvactivesessions.setAdapter(activeAdapter);
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
