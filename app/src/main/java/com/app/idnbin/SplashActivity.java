package com.app.idnbin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.LoginRegister.LoginActivity;
import com.app.idnbin.Profile.Settings.Security.CodeActivity;
import com.app.idnbin.Profile.Settings.Security.PasscodeCheckActivity;
import com.app.idnbin.util.BaseActivity;
import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends BaseActivity {
    private String securityType, phone, phoneCode;
    private static final String TAG = "SplashActivity";
    private Handler handler;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ImageView IvBackground = findViewById(R.id.IvBackground);
        ImageView IvLogo = findViewById(R.id.IvLogo);
        TextView TvLoading = findViewById(R.id.TvLoading);
        TextView TvDescription = findViewById(R.id.TvDescription);
        SpinKitView loader = findViewById(R.id.spin_kit);

        Glide.with(this).load(R.drawable.bg).into(IvBackground);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Log.d("VALLALA", "" + currentUser);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(2000);
        Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);

        IvLogo.startAnimation(slide_up);


        SharedPreferences shared = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        securityType = shared.getString("securityType", "");
        phone = shared.getString("Phone", "");
        phoneCode = shared.getString("phoneCode", "");

        Log.d(TAG, "onCreate: " + securityType);

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (count < 3) {
                    Log.d("VALALLA", "" + count);
                    if (count == 1) {
                        loader.setVisibility(View.VISIBLE);
                    } else if (count == 2) {
                        TvLoading.setText("Loading");
                        TvLoading.setAnimation(alphaAnimation);
                        TvDescription.setText("One of the Best Trading Application");
                    }
                    handler.postDelayed(this, 1500);
                } else {
                    if (currentUser != null) {
                        if (securityType.equalsIgnoreCase("passcode")) {
                            Intent myIntent = new Intent(SplashActivity.this, PasscodeCheckActivity.class);
                            myIntent.putExtra("passCodeFun", "Passcode"); //Optional parameters
                            startActivity(myIntent);
                            finish();

                        } else if (securityType.equalsIgnoreCase("2Auth")) {
                            sendDataToClass(phone, phoneCode, CodeActivity.class, "Splash");
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                                    finish();
                                }
                            }, 1000);

                        }

                    } else {

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                finish();
                            }
                        }, 1500);
                    }
                }
                count++;
            }
        }, 1000);
    }
}
