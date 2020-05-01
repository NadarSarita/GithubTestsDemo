package com.app.idnbin.Profile.Settings.Security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.idnbin.LoginRegister.LoginActivity;
import com.app.idnbin.R;
import com.app.idnbin.SplashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {

    Toolbar Tb_App;
    EditText Et_CurrentPassword, Et_NewPassword, Et_ConfirmPassword;
    String currentpass, newpass, confpass;
    Button Bth_Save;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Tb_App = findViewById(R.id.Tb_App);

        Et_CurrentPassword = findViewById(R.id.Et_CurrentPassword);
        Et_NewPassword = findViewById(R.id.Et_NewPassword);
        Et_ConfirmPassword= findViewById(R.id.Et_ConfirmPassword);
        Bth_Save = findViewById(R.id.Bth_Save);
        auth = FirebaseAuth.getInstance();

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Change Password");

        Bth_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentpass = Et_CurrentPassword.getText().toString();
                newpass = Et_NewPassword.getText().toString();
                confpass = Et_ConfirmPassword.getText().toString();
                if (TextUtils.isEmpty(Et_CurrentPassword.getText().toString())){
                    Et_CurrentPassword.setError("Enter Current Password");
                } else if(TextUtils.isEmpty(Et_NewPassword.getText().toString())){
                    Et_NewPassword.setError("Enter New Password");
                } else if(TextUtils.isEmpty(Et_ConfirmPassword.getText().toString())){
                    Et_ConfirmPassword.setError("Enter Confirm Password");
                } else if(!Et_NewPassword.getText().toString().equals(Et_ConfirmPassword.getText().toString())){
                    alertDialog("Error");
                } else {
                    change();
                }
            }
        });
    }

    public void alertDialog(String Error){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ChangePasswordActivity.this, R.style.AlertDialog);
        builder.setTitle(Error);
        builder.setMessage("Current and Confirm Password Not Match");
        builder.setCancelable(false);

        builder.setPositiveButton(
                "Okay",
                (dialog, id) -> dialog.cancel());
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    public void change(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            user.updatePassword(Et_NewPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ChangePasswordActivity.this, "Your password has been changed.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ChangePasswordActivity.this, SplashActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "Password could not be changed.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
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
