package com.app.idnbin.Profile.Settings.Security;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeActivity extends BaseActivity {

    Toolbar Tb_App;
    @BindView(R.id.EtCode)
    EditText EtCode;
    @BindView(R.id.rl_layout)
    RelativeLayout rl_layout;
    @BindView(R.id.TvPhoneNumber)
    TextView TvPhoneNumber;

    private FirebaseAuth mAuth;
    private String mVerificationId;
    private String mobile, mobileCode,activityName;
    @BindView(R.id.resend_counter) TextView resend_counter;
    @BindView(R.id.Tv_ResendCode) TextView Tv_ResendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        Tb_App = findViewById(R.id.Tb_App);

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Tb_App.setTitle("Code");
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        mobileCode = intent.getStringExtra("mobileCode");
        activityName = intent.getStringExtra("activityName");


        TvPhoneNumber.setText(mobileCode+""+mobile);
        sendVerificationCode(mobile, mobileCode,mCallbacks);
        startCounter();
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
    private void startCounter() {
        new CountDownTimer(51000, 1000) {

            public void onTick(long millisUntilFinished) {
                resend_counter.setVisibility(View.VISIBLE);
                Tv_ResendCode.setVisibility(View.GONE);
                resend_counter.setText("00:" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                resend_counter.setVisibility(View.GONE);
                Tv_ResendCode.setVisibility(View.VISIBLE);
            }

        }.start();


    }

    @OnClick({R.id.BtnRedeem,R.id.Tv_ResendCode})
    public void CodeVerify(View view){
        switch (view.getId())
        {
            case R.id.BtnRedeem:
                String etCode=EtCode.getText().toString();
                if (etCode.isEmpty() || etCode.length() < 6) {
                    EtCode.setError("Enter valid code");
                    EtCode.requestFocus();
                    return;
                } else {
                    verifyVerificationCode(mAuth,mVerificationId,etCode,mobileCode,mobile,rl_layout,"Codeactivity");
                    if(activityName.equalsIgnoreCase("Splash"))
                    {
                        startActivity(new Intent(this, HomeActivity.class));
                    }
                    else {
                        startActivity(new Intent(this, AuthenticationActivity.class));
                    }
                    finish();
                }
                break;

            case R.id.Tv_ResendCode:
                sendVerificationCode(mobile, mobileCode,mCallbacks);
                startCounter();
                break;
        }
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                EtCode.setText(code);
                //verifying the code
                verifyVerificationCode(mAuth,mVerificationId,code,mobileCode,mobile,rl_layout,"Codeactivity");
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.d("KALAALA", "" + e.getMessage());
            Toast.makeText(CodeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            mVerificationId = s;
        }
    };

}
