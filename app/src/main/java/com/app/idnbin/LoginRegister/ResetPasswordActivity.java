package com.app.idnbin.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends BaseActivity {
    private EditText Et_Email;
    private FirebaseAuth firebaseAuth;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        Et_Email = findViewById(R.id.Et_Email);
        Button BtnSubmit = findViewById(R.id.BtnSubmit);
        relativeLayout = findViewById(R.id.relativeLayout);

        firebaseAuth = FirebaseAuth.getInstance();

        BtnSubmit.setOnClickListener(v -> {

            String email = Et_Email.getText().toString();

            if (TextUtils.isEmpty(email)) {
                snackBar(relativeLayout, "All fields are required!");
            } else {
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                       snackBar(relativeLayout, "Please check you Email");
                        Intent resetIntent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                        startActivity(resetIntent);
                    } else {
                        if (task.getException() != null) {
                            Toast.makeText(ResetPasswordActivity.this, "Please enter your registered email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
