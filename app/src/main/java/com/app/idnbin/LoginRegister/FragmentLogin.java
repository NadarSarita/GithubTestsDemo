package com.app.idnbin.LoginRegister;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.ContextCompat;

import com.app.idnbin.R;
import com.app.idnbin.util.BaseFragment;
import java.util.Objects;

public class FragmentLogin extends BaseFragment implements View.OnClickListener {
    String email, password;
    private RelativeLayout relativeLayout;
    private EditText EtEmail, EtPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView TvForgotPwd = view.findViewById(R.id.TvForgotPwd);
        EtEmail = view.findViewById(R.id.EtEmail);
        EtPassword = view.findViewById(R.id.EtPassword);
        Button BtnLogin = view.findViewById(R.id.BtnLogin);
        relativeLayout = view.findViewById(R.id.relativeLayout);
        CheckBox CbShowPwd = view.findViewById(R.id.CbShowPwd);


        BtnLogin.setOnClickListener(this);
        TvForgotPwd.setOnClickListener(this);

        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS)
                && PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE)) {
            ((LoginActivity)getActivity()).checkPermissions();
        }

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
        switch (v.getId()){
            case R.id.BtnLogin:

                email = EtEmail.getText().toString();
                password = EtPassword.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                    Log.d("dadadad", ""+email+" "+password);
                } else if (!password.matches("[a-zA-Z0-9.? ]*")){
                    Toast.makeText(getContext(), "Special characters are not allowed", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getContext(), "please enter valid email address", Toast.LENGTH_SHORT).show();
                    //snackBar(relativeLayout,"please enter valid email address");
                } else if (password.length() < 6) {
                    Toast.makeText(getContext(), "password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    //snackBar(relativeLayout,"password must be at least 6 characters");
                } else{
                    ((LoginActivity) (getActivity())).emailLogin(email, password);
                }
                break;
            case R.id.TvForgotPwd :
                Intent intent = new Intent(getContext(), ResetPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    void displayReceivedData(String sEmail, String sPassword)
    {
        EtEmail.setText(sEmail);
        EtPassword.setText(sPassword);
    }
}
