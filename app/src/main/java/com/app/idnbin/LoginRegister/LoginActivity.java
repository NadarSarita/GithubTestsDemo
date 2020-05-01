package com.app.idnbin.LoginRegister;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.R;
import com.app.idnbin.util.BaseActivity;
import com.app.idnbin.util.ContactWork;
import com.app.idnbin.util.GlobalConstants;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends BaseActivity implements FragmentRegistration.SendMessage, View.OnClickListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    RelativeLayout relativeLayout;
    LoginButton fblogin;

    private String TAG = "AKAKAKA";
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private CallbackManager mCallbackManager;
    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    private static final int RC_SIGN_IN = 9001;
    private String AndroidId, DeviceId, IMEI, meid, serialNumber, networkCountryIso, simCountryIso, networkOperatorName, simOperatorName, line1Number, model,
            device, brand_Id, manufacturer, product, type, host, hardware, base_OS, codename, release, security_Patch, incrimental;
    private String[] permissions = new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        relativeLayout = findViewById(R.id.relativeLayout);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fblogin = findViewById(R.id.fblogin);

        TextView TvGoogle = findViewById(R.id.TvGoogle);
        TextView TvFb = findViewById(R.id.TvFb);
        TextView TvPhone = findViewById(R.id.TvPhone);

        TvGoogle.setOnClickListener(this);
        TvFb.setOnClickListener(this);
        TvPhone.setOnClickListener(this);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        if (getPref(this,"LoginSuccess").equalsIgnoreCase("true")){
            viewPager.setCurrentItem(1);
        }

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        mCallbackManager = CallbackManager.Factory.create();
        fblogin.setReadPermissions("email", "public_profile");
        fblogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("Cancel", "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    @Override
    public void sendData(String email, String password) {
        String tag = "android:switcher:" + R.id.viewPager + ":" + 1;
        FragmentLogin fragmentLogin = (FragmentLogin) getSupportFragmentManager().findFragmentByTag(tag);
        fragmentLogin.displayReceivedData(email, password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TvGoogle:
                retrieveDeviceData();
                if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                        && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)) {
                    signIn();
                } else {
                    checkPermissions();
                }
                break;
            case R.id.TvFb:
                fbLogin();
                break;

            case R.id.TvPhone:
                Intent intent1 = new Intent(this, PhoneActivity.class);
                startActivity(intent1);
                break;
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new FragmentRegistration();
            switch (position) {
                case 0:
                    fragment = new FragmentRegistration();
                    break;
                case 1:
                    fragment = new FragmentLogin();
                    break;
            }
            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "Tabs";
            switch (position) {
                case 0:
                    title = "Registration";
                    break;
                case 1:
                    title = "Login";
                    break;
            }
            return title;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


    /*TODO Google Login*/
    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /*TODO Reterive UserDetails NotifyData*/
    @SuppressLint({"MissingPermission", "HardwareIds"})
    void retrieveDeviceData() {
        if (isPermissionGranted(this, Manifest.permission.READ_PHONE_STATE)) {

            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            if (telephonyManager != null) {

                if (Build.VERSION.SDK_INT >= 26) {
                    AndroidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
                } else {
                    AndroidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
                    DeviceId = telephonyManager.getDeviceId();
                }

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        meid = String.valueOf(telephonyManager.getMeid());
                    }
                }
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                    IMEI = String.valueOf(telephonyManager.getDeviceId());
                }

                line1Number = "";
                networkCountryIso = telephonyManager.getNetworkCountryIso();
                simCountryIso = telephonyManager.getSimCountryIso();
                networkOperatorName = telephonyManager.getNetworkOperatorName();
                simOperatorName = telephonyManager.getSimOperatorName();

            } else {
                networkCountryIso = "";
                simCountryIso = "";
            }

            model = Build.MODEL;
            device = Build.DEVICE;
            brand_Id = Build.ID;
            manufacturer = Build.MANUFACTURER;
            product = Build.PRODUCT;
            type = Build.TYPE;
            host = Build.HOST;
            hardware = Build.HARDWARE;
            serialNumber = "";
            base_OS = "";
            codename = Build.VERSION.CODENAME;
            release = Build.VERSION.RELEASE;
            security_Patch = "";
            incrimental = Build.VERSION.INCREMENTAL;
        } else {
            int READ_PHONE_STATE_PERMISSION_CODE = 123;
            requestForPermission(this, Manifest.permission.READ_PHONE_STATE, READ_PHONE_STATE_PERMISSION_CODE);
        }
    }

    public boolean isPermissionGranted(Context context, String strValue) {

        return ContextCompat.checkSelfPermission(context, strValue) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestForPermission(Activity activity, String strValue, int permissionCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, strValue)) {
            permissionDialog(activity, strValue, permissionCode);
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{strValue}, permissionCode);
        }
    }

    private void permissionDialog(final Activity activity, final String strValue, final int permissionCode) {
        new AlertDialog.Builder(activity).setTitle("Permission")
                .setMessage("This permission are needed")
                .setPositiveButton("ok", (dialog, which) -> ActivityCompat.requestPermissions(activity, new String[]{strValue}, permissionCode))
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create().show();
    }

    /*TODO Login with Email*/
    public void emailLogin(final String txt_email, final String txt_password) {
        mAuth.signInWithEmailAndPassword(txt_email, txt_password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        updateUI(mAuth.getCurrentUser());
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (Objects.requireNonNull(firebaseUser).isEmailVerified()) {
                            UserDetails user = new UserDetails(firebaseUser.getUid(), firebaseUser.getEmail(), "", "", currentDateTimeString, "", "", "","");
                            GlobalConstants.UsersInstance.child(firebaseUser.getUid()).setValue(user);
                            setPref(this,"LoginSuccess","true");
                            setPref(this,"NormalLogin","true");
                            startActivity(new Intent(this, HomeActivity.class));
                        } else {
                            Snackbar snackbar = Snackbar.make(relativeLayout, "Please verify your email address.", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                        setPref(this, "username", firebaseUser.getEmail());
                    } else {
                        if (task.getException() != null) {
                            snackBar(relativeLayout, "" + task.getException().getMessage());
                        } else {
                            snackBar(relativeLayout, "Login Failed");
                        }
                    }
                });
    }

    /*TODO Gmail Sign In*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    firebaseAuthWithGoogle(account);
                    saveContacts(account.getDisplayName());
                } else {
                    snackBar(relativeLayout, "Google sign in Failed");
                }

            } catch (ApiException e) {
                snackBar(relativeLayout, "Google sign in failed");
                Log.w(TAG, "Google sign in failed", e);
            }
        }

        if (requestCode == REQUEST_PERMISSION_SETTING) {
            if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    && PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)) {
                Toast.makeText(this, "Thank You For Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void fbLogin() {
        fblogin.performClick();
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Log.d("Tiger", "" + credential);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    Log.d("Tiger", "handleFacebookAccessToken:" + task.isSuccessful());
                    if (task.isSuccessful()) {
                        FirebaseUser fuser = mAuth.getCurrentUser();
                        if (fuser != null) {
                            UserDetails user = new UserDetails(fuser.getUid(), fuser.getEmail(), "", "", currentDateTimeString, "", "", "","");
                            GlobalConstants.UsersInstance.child(fuser.getUid()).setValue(user);
                            LoginDetails loginDetails = new LoginDetails(getTime(), "");
                            GlobalConstants.LoginDetailsInstance.child(fuser.getUid()).setValue(loginDetails);
                            DeviceDetails deviceDetails = new DeviceDetails(AndroidId, DeviceId, IMEI, meid, serialNumber, networkCountryIso, simCountryIso, networkOperatorName, simOperatorName, line1Number, model, device, brand_Id, manufacturer, product, type, host, hardware, base_OS, codename, release, security_Patch, incrimental);
                            GlobalConstants.DeviceDetailsInstance.child(fuser.getUid()).setValue(deviceDetails);
                            updateUI(fuser);
                            setPref(this, "username", fuser.getEmail());
                            setPref(this,"LoginSuccess","true");
                            setPref(this,"NormalLogin","false");
                            startActivity(new Intent(this, HomeActivity.class));
                            this.finish();
                        }
                    } else {
                        LoginManager.getInstance().logOut();
                        snackBar(relativeLayout, "" + Objects.requireNonNull(task.getException()).getMessage());
                    }
                });
    }

    /*TODO Gmail Login*/
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser fuser = mAuth.getCurrentUser();
                        if (fuser != null) {
                            UserDetails user = new UserDetails(fuser.getUid(), fuser.getEmail(), "", "", currentDateTimeString, "", "", "","");
                            GlobalConstants.UsersInstance.child(fuser.getUid()).setValue(user);
                            LoginDetails loginDetails = new LoginDetails(getTime(), "");
                            GlobalConstants.LoginDetailsInstance.child(fuser.getUid()).setValue(loginDetails);
                            DeviceDetails deviceDetails = new DeviceDetails(AndroidId, DeviceId, IMEI, meid, serialNumber, networkCountryIso, simCountryIso, networkOperatorName, simOperatorName, line1Number, model, device, brand_Id, manufacturer, product, type, host, hardware, base_OS, codename, release, security_Patch, incrimental);
                            GlobalConstants.DeviceDetailsInstance.child(fuser.getUid()).setValue(deviceDetails);
                            updateUI(fuser);
                            setPref(this, "username", fuser.getEmail());
                            setPref(this,"LoginSuccess","true");
                            setPref(this,"NormalLogin","false");
                            startActivity(new Intent(this, HomeActivity.class));
                            this.finish();
                        }

                    } else {
                        snackBar(relativeLayout, "Sign In Failed");
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                    }

                });
    }

    /*TODO Save UserDetails Contacts to Firebase*/
    private void saveContacts(String user) {
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(ContactWork.class)
                .setInputData(createInputData(user))
                .setInitialDelay(2, TimeUnit.SECONDS)
                .build();
        WorkManager.getInstance(this).enqueue(workRequest);
    }

    private Data createInputData(String value) {
        return new Data.Builder().putString("keyUserName", value).build();
    }

    private final int MULTIPLE_PERMISSIONS = 10;

    public void checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (listPermissionsNeeded.size() != 0) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[0]), MULTIPLE_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == MULTIPLE_PERMISSIONS) {
            if (grantResults.length > 0) {
                ArrayList<String> permissionsDenied = new ArrayList<>();
                for (String per : permissions) {
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        permissionsDenied.add(per);
                    }
                }

                if (permissionsDenied.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) || shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)) {
                            alertDialogPermission(true);
                        } else {
                            alertDialogPermission(false);
                        }
                    }
                }
            }

        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void alertDialogPermission(boolean check) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Need Permission");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);

        if (check) {
            builder.setMessage("Please Allow Permission,\nWhich will help us to Improve your App Experience");
            builder.setPositiveButton("Grant", (dialog, id) -> {
                checkPermissions();
                dialog.cancel();
            });
        } else {
            builder.setMessage("App Need Contact Permission,\nGrant Permission in Setting➟Permissions");
            builder.setPositiveButton("Grant", (dialog, id) -> {
                sendToSetting();
                dialog.cancel();
            });
        }

        builder.setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

    int REQUEST_PERMISSION_SETTING = 27;

    private void sendToSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
    }
}
