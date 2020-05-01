package com.app.idnbin.Tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.idnbin.R;

public class VideoActivity extends AppCompatActivity {

    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String title = getIntent().getStringExtra("Header");

        if(title.equalsIgnoreCase("Basics")){
            Tb_App.setTitle(title);
        } else if (title.equalsIgnoreCase("CFD Trading")){
            Tb_App.setTitle(title);
        } else if (title.equalsIgnoreCase("Technical Analysis")){
            Tb_App.setTitle(title);
        } else if (title.equalsIgnoreCase("Fundamental Analysis")){
            Tb_App.setTitle(title);
        } else if (title.equalsIgnoreCase("Market News")){
            Tb_App.setTitle(title);
        } else if (title.equalsIgnoreCase("Crypto Digest")){
            Tb_App.setTitle(title);
        } else if (title.equalsIgnoreCase("About Us")){
            Tb_App.setTitle(title);
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
