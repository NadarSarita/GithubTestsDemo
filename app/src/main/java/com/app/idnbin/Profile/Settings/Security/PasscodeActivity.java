package com.app.idnbin.Profile.Settings.Security;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasscodeActivity extends BaseActivity {

    Toolbar Tb_App;
    @BindView(R.id.SPasscode)
    Switch SPasscode;
    @BindView(R.id.btn_set_pass)
    Button btn_set_pass;
    String securityType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);

        ButterKnife.bind(this);
        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Password");


        SharedPreferences shared = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        securityType = shared.getString("securityType", "");

        if(!securityType.equalsIgnoreCase(""))
        {
            SPasscode.setChecked(true);
            btn_set_pass.setText("Change passcode");
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

    @OnClick({R.id.btn_set_pass, R.id.SPasscode})
    public void setPass(View view){
        Intent myIntent = new Intent(PasscodeActivity.this, PasscodeCheckActivity.class);
        switch (view.getId())
        {
            case R.id.btn_set_pass:
                if(btn_set_pass.getText().toString().equalsIgnoreCase("Set passcode"))
                {
                    myIntent.putExtra("passCodeFun", "Set passcode"); //Optional parameters
                }
                else {
                    myIntent.putExtra("passCodeFun", "Change passcode"); //Optional parameters
                }
                break;
            case R.id.SPasscode:
                if(!SPasscode.isChecked())
                {
                    myIntent.putExtra("passCodeFun", "Remove passcode"); //Optional parameters
                }
                else {
                    myIntent.putExtra("passCodeFun", "Set passcode"); //Optional parameters
                }
                break;
        }
        startActivityForResult(myIntent,1);
    }

    public void updateDb_Local(boolean b, String passcode_value, String securityType,String code){
        SPasscode.setChecked(b);
        btn_set_pass.setText(passcode_value);
        updateNodeInDb("securityType",securityType);
        updateNodeInDb("securityCode",code);

        SharedPreferences shared = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("securityType",securityType);
        editor.putString("securityCode",code);
        editor.apply();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1)
        {
            if(data!=null)
            {
                if(data.hasExtra("strValue"))
                {
                    String strCode=data.getStringExtra("strCode");

                    if(data.getStringExtra("strValue").equalsIgnoreCase("Match"))
                    {
                        updateDb_Local(true,"Change passcode","Passcode",strCode);
                    }
                    else if(data.getStringExtra("strValue").equalsIgnoreCase("Remove"))
                    {
                        updateDb_Local(false,"Set passcode","",strCode);
                    }
                }
                Toast.makeText(this, ""+data.getStringExtra("strValue"), Toast.LENGTH_SHORT).show();
            }

        }

    }

}

