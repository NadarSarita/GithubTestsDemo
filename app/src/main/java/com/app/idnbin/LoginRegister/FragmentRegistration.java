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
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.app.idnbin.R;
import com.app.idnbin.WebView.ReadMeActivity;
import com.app.idnbin.util.ApiCall;
import com.app.idnbin.util.ApiIterface;
import com.app.idnbin.util.BaseFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.app.idnbin.util.GlobalConstants.DeviceDetailsInstance;
import static com.app.idnbin.util.GlobalConstants.LoginDetailsInstance;
import static com.app.idnbin.util.GlobalConstants.UsersInstance;

public class FragmentRegistration extends BaseFragment implements View.OnClickListener {
    private EditText EtEmail, EtPassword;
    private CheckBox CbTandC;
    private RelativeLayout relativeLayout;
    private FirebaseAuth mAuth;
    private String AndroidId, DeviceId, IMEI, meid, serialNumber, networkCountryIso, simCountryIso, networkOperatorName,
            simOperatorName, line1Number, model, device, brand_Id, manufacturer, product, type, host,
            hardware, base_OS, codename, release, security_Patch, incrimental, email;
    private SendMessage SM;
    private static final String TAG = "FragmentRegistration";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        TextView TvTermsAndCondition = view.findViewById(R.id.TvTermsAndCondition);
        TextView TvPrivacyPolicy = view.findViewById(R.id.TvPrivacyPolicy);
        EtEmail = view.findViewById(R.id.EtEmail);
        EtPassword = view.findViewById(R.id.EtPassword);
        CbTandC = view.findViewById(R.id.CbTandC);
        Button BtnRegister = view.findViewById(R.id.BtnRegister);
        relativeLayout = view.findViewById(R.id.relativeLayout);
        Spinner SpCurrency = view.findViewById(R.id.SpCurrency);
        CheckBox CbShowPwd = view.findViewById(R.id.CbShowPwd);

        mAuth = FirebaseAuth.getInstance();

        BtnRegister.setOnClickListener(this);
        TvTermsAndCondition.setOnClickListener(this);
        TvPrivacyPolicy.setOnClickListener(this);

        List<String> currency = new ArrayList<>();
        currency.add("IDR");
        currency.add("USD");
        currency.add("EUR");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.spinner_text, currency);
        adapter.setDropDownViewResource(R.layout.spinner_text);
        SpCurrency.setAdapter(adapter);

        CbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    EtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    EtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BtnRegister:
                email = EtEmail.getText().toString();
                String password = EtPassword.getText().toString();
                retrieveDeviceData();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                    //snackBar(relativeLayout, "All fields are required");
                } else if (!password.matches("[a-zA-Z0-9.? ]*")) {
                    Toast.makeText(getContext(), "Special characters are not allowed", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getContext(), "please enter valid email address", Toast.LENGTH_SHORT).show();
                    //snackBar(relativeLayout, "please enter valid email address");
                } else if (password.length() < 6) {
                    Toast.makeText(getContext(), "password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    //snackBar(relativeLayout, "password must be at least 6 characters");
                } else if (!CbTandC.isChecked()) {
                    Toast.makeText(getContext(), "Please accept Terms & Conditions", Toast.LENGTH_SHORT).show();
                    //snackBar(relativeLayout, "Please accept Terms & Conditions");
                } else {
                    registration(email, password);
                }
                break;
            case R.id.TvTermsAndCondition:
                Intent userguide = new Intent(getContext(), ReadMeActivity.class);
                userguide.putExtra("linkurl", "http://betdigital.club/terms-and-conditions.html");
                startActivity(userguide);
                break;
            case R.id.TvPrivacyPolicy:
                Uri uriPrivacyPolicy = Uri.parse("http://betdigital.club/privacy-policy.html");
                Intent intentPrivacyPolicy = new Intent(Intent.ACTION_VIEW, uriPrivacyPolicy);
                startActivity(intentPrivacyPolicy);
                break;
        }
    }

    interface SendMessage {
        void sendData(String sEmail, String sPassword);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }

    /*TODO Reterive UserDetails NotifyData*/
    @SuppressLint({"MissingPermission", "HardwareIds"})
    private void retrieveDeviceData() {
        if (isPermissionGranted(getContext(), Manifest.permission.READ_PHONE_STATE)) {

            TelephonyManager telephonyManager = (TelephonyManager) Objects.requireNonNull(getActivity()).getSystemService(Context.TELEPHONY_SERVICE);

            if (telephonyManager != null) {

                if (Build.VERSION.SDK_INT >= 26) {
                    AndroidId = Settings.Secure.getString(Objects.requireNonNull(getContext()).getContentResolver(), Settings.Secure.ANDROID_ID);
                } else {
                    AndroidId = Settings.Secure.getString(Objects.requireNonNull(getContext()).getContentResolver(), Settings.Secure.ANDROID_ID);
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
            requestForPermission(getContext(), READ_PHONE_STATE_PERMISSION_CODE);
        }
    }

    public boolean isPermissionGranted(Context context, String strValue) {

        if (ContextCompat.checkSelfPermission(context, strValue) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestForPermission(Context context, int permissionCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(getActivity()), Manifest.permission.READ_PHONE_STATE)) {
            permissionDialog(getActivity(), permissionCode);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, permissionCode);
        }
    }

    private void permissionDialog(final Activity activity, final int permissionCode) {
        new AlertDialog.Builder(activity).setTitle("Permission")
                .setMessage("This permission are needed")
                .setPositiveButton("ok", (dialog, which) -> ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, permissionCode))
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create().show();
    }

    private void registration(String mail, String pwd) {
        ApiIterface apiIterface = ApiCall.subscriberApi().create(ApiIterface.class);
      //  Call<ResMemberBody> call = apiIterface.basicLogin(new ReqMemberBody("binarybrics@gmail.com","subscribed", new MergeFields("Binary","Brics")));
        Call<ResMemberBody> call = apiIterface.basicLogin(new ReqMemberBody(mail,"subscribed"));
        call.enqueue(new Callback<ResMemberBody>() {
            @Override
            public void onResponse(@NonNull Call<ResMemberBody> call, @NonNull Response<ResMemberBody> response) {
                Log.d(TAG, "onResponse: "+response.isSuccessful());
                if (response.isSuccessful()) {
                    // user object available
                    ResMemberBody resMemberBody=response.body();
                    Log.d(TAG, "onResponse: "+response.body().toString());
                    Log.d(TAG, "onResponse: "+resMemberBody.getEmailAddress());
                } else {
                    Log.e(TAG, "onResponse: "+response.message());
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResMemberBody> call, @NonNull Throwable t) {
                Log.d("Error", t.getMessage());


            }
        });


      mAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(Objects.requireNonNull(getActivity()), task -> {
            if (task.isSuccessful()) {

                Objects.requireNonNull(mAuth.getCurrentUser()).sendEmailVerification().addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()) {
                        setPref(getContext(),"LoginSuccess","true");
                        snackBar(relativeLayout, "Register Successfully.");
                        alertDialog(getContext());
                        SM.sendData(mail, pwd);
                    } else {
                        snackBar(relativeLayout, " " + Objects.requireNonNull(task1.getException()).getMessage());
                        SM.sendData("", "");
                    }
                });

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                if (firebaseUser != null) {
                    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                    String userid = firebaseUser.getUid();

                    UserDetails user = new UserDetails(userid, email, "", " ", currentDateTimeString,"","","","");
                    UsersInstance.child(userid).setValue(user);
                    LoginDetails loginDetails = new LoginDetails(currentDateTimeString, "");
                    LoginDetailsInstance.child(userid).setValue(loginDetails);
                    DeviceDetails deviceDetails = new DeviceDetails(AndroidId, DeviceId, IMEI, meid, serialNumber, networkCountryIso, simCountryIso, networkOperatorName, simOperatorName, line1Number, model, device, brand_Id, manufacturer, product, type, host, hardware, base_OS, codename, release, security_Patch, incrimental);
                    DeviceDetailsInstance.child(userid).setValue(deviceDetails);
                }
            } else {
                if (task.getException() != null) {
                    Snackbar snackbar = Snackbar.make(relativeLayout, " " + task.getException().getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar.make(relativeLayout, "Registeration Failed", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                SM.sendData("", "");
            }
        });
    }

    /*TODO Show Dialog*/
    private void alertDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialog);
        builder.setMessage("Please check your email for verification link.");
        builder.setCancelable(false);

        builder.setPositiveButton("OKAY", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();

        EtEmail.setText("");
        EtPassword.setText("");
    }
}
