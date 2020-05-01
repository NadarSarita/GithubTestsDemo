package com.app.idnbin.Profile.Settings.Security;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasscodeCheckActivity extends AppCompatActivity {
    @BindView(R.id.txtTitle) TextView txtTitle;
    @BindView(R.id.txt1) TextView txt1;
    @BindView(R.id.txt2) TextView txt2;
    @BindView(R.id.txt3) TextView txt3;
    @BindView(R.id.txt4) TextView txt4;
    @BindView(R.id.txt5) TextView txt5;
    @BindView(R.id.txt6) TextView txt6;
    @BindView(R.id.txt7) TextView txt7;
    @BindView(R.id.txt8) TextView txt8;
    @BindView(R.id.txt9) TextView txt9;
    @BindView(R.id.txt0) TextView txt0;
    @BindView(R.id.txtExit) TextView txtExit;
    @BindView(R.id.txtBack)
    ImageView txtBack;
    @BindView(R.id.img1) ImageView img1;
    @BindView(R.id.img2) ImageView img2;
    @BindView(R.id.img3) ImageView img3;
    @BindView(R.id.img4) ImageView img4;
    @BindView(R.id.imgDone) ImageView imgDone;

    StringBuilder str = new StringBuilder(4);
    String strPrev,strCurr,strConfirm,passCodeFun;
    String securityCode;
    private static final String TAG = "PasscodeCheckActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode_check);
        ButterKnife.bind(this);

        SharedPreferences shared = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        securityCode = shared.getString("securityCode", "");

        Intent intent = getIntent();
        passCodeFun = intent.getStringExtra("passCodeFun");
        Log.i(TAG, "onCreate: "+passCodeFun);
        if(!passCodeFun.equalsIgnoreCase("Set passcode"))
        {
            txtTitle.setText("Current passcode");
        }
        else {
            txtTitle.setText(passCodeFun);
        }

    }

    @OnClick({R.id.txt1,R.id.txt2,R.id.txt3,R.id.txt4,R.id.txt5,R.id.txt6,R.id.txt7,R.id.txt8,R.id.txt9,R.id.txt0,R.id.txtBack,R.id.txtExit,R.id.imgDone})
    public void btnValue(View v) {
        txtBack.setVisibility(View.VISIBLE);
        if (str.capacity() > str.length()) {
            switch (v.getId()) {
                case R.id.txt1:
                    str.append(1);
                    Log.d(TAG, "btnValue: " + str);
                    break;
                case R.id.txt2:
                    str.append(2);
                    Log.d(TAG, "btnValue: " + str);
                    break;
                case R.id.txt3:
                    str.append(3);
                    Log.d(TAG, "btnValue: " + str);
                    break;
                case R.id.txt4:
                    str.append(4);
                    Log.d(TAG, "btnValue: " + str);
                    break;
                case R.id.txt5:
                    str.append(5);
                    Log.d(TAG, "btnValue: " + str);
                    break;
                case R.id.txt6:
                    str.append(6);
                    Log.d(TAG, "btnValue: " + str);
                    break;
                case R.id.txt7:
                    str.append(7);
                    Log.d(TAG, "btnValue: " + str);
                    break;
                case R.id.txt8:
                    str.append(8);
                    Log.d(TAG, "btnValue: " + str);
                    break;
                case R.id.txt9:
                    str.append(9);
                    Log.d(TAG, "btnValue: " + str);
                    break;
                case R.id.txt0:
                    str.append(0);
                    Log.d(TAG, "btnValue: " + str);
                    break;

            }
            Log.d(TAG, "btnValue: " + str.length());
            setActiveImg(str.length());
        }

        switch (v.getId()) {

            case R.id.txtExit:
                Log.d(TAG, "btnValue: " + str);
                sendResult("Exit","");
                break;
            case R.id.txtBack:
                Log.d(TAG, "btnValue: " + str);
                Log.d(TAG, "btnValue: " + str.length());
                if (str.length() > 0)
                    str.deleteCharAt(str.length() - 1);
                else
                    sendResult("Exit","");
                Log.d(TAG, "btnValue: " + str.length());
                setActiveImg(str.length());
                break;
            case R.id.imgDone:
                if(passCodeFun.equalsIgnoreCase("Set passcode"))
                {
                    if(strCurr!=null) {
                        strConfirm = str.toString();
                        Log.d(TAG, "btnValue: 1");
                        Log.d(TAG, "btnValue: "+strCurr+" "+strConfirm);
                        if(strCurr.equals(strConfirm))
                        {
                            changeDisplay(true);
                            sendResult("Match",strConfirm);
                        }
                        else {
                            changeDisplay(false);
                        }
                    }
                    else {
                        txtTitle.setText("Confirm passcode");
                        Log.d(TAG, "btnValue: 2");
                        strCurr = str.toString();
                        str.setLength(0);
                        txtExit.setVisibility(View.VISIBLE);
                        imgDone.setVisibility(View.INVISIBLE);
                    }
                }
                else {
                    Log.d(TAG, "btnValue: 3");
                    strPrev=str.toString();
                    str.setLength(0);
                    txtExit.setVisibility(View.VISIBLE);
                    imgDone.setVisibility(View.INVISIBLE);
                    if(passCodeFun.equalsIgnoreCase("Passcode"))
                    {
                        Log.d(TAG, "btnValue: Passcode "+securityCode+" --> "+strPrev);
                        if(securityCode.equalsIgnoreCase(strPrev))
                        {
                            changeDisplay(true);
                            startActivity(new Intent(this, HomeActivity.class));
                            finish();
                        }
                        else{
                            changeDisplay(false);
                        }

                    }
                    else if(passCodeFun.equalsIgnoreCase("Change passcode"))
                    {
                        passCodeFun="Set passcode";
                        txtTitle.setText(passCodeFun);
                    }
                    else if(passCodeFun.equalsIgnoreCase("Remove passcode"))
                    {
                        if(securityCode.equalsIgnoreCase(strPrev))
                        {
                            sendResult("Remove","");
                            changeDisplay(true);
                        }
                        else {
                            changeDisplay(false);
                        }
                    }
                    else {
                        sendResult("Exit","");
                    }
                }

                txtBack.setVisibility(View.INVISIBLE);
                //  setActiveImg(0);
                break;
        }
        Log.d(TAG, "btnValue: dis "+str.length());
        if(str.length()==4)
        {
            imgDone.setVisibility(View.VISIBLE);
            txtExit.setVisibility(View.INVISIBLE);
        }
        else {
            imgDone.setVisibility(View.INVISIBLE);
            txtExit.setVisibility(View.VISIBLE);
        }

    }

    private void activeImg(boolean b) {
        img1.setActivated(b);
        img2.setActivated(b);
        img3.setActivated(b);
        img4.setActivated(b);
    }

    private void changeDisplay(boolean b)    {
        if(b)
        {
            setActiveImg(4);
            activeImg(true);
        }
        else {
            setActiveImg(0);
            activeImg(true);
            delayTask();
        }
    }

    private void sendResult(String strValue,String strCode) {
        Intent intent=new Intent();
        intent.putExtra("strValue",strValue);
        intent.putExtra("strCode",strCode);
        setResult(1,intent);
        finish();
    }

    public void delayTask(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                activeImg(false);
                str.setLength(0);
            }
        },100);

    }
    private void selectedImg(boolean b, boolean b1, boolean b2, boolean b3){
        img1.setSelected(b);
        img2.setSelected(b1);
        img3.setSelected(b2);
        img4.setSelected(b3);
    }
    private void setActiveImg(int i) {
        if(i==4)
        {
            selectedImg(true,true,true,true);
        }
        else if(i==3)
        {
            selectedImg(true,true,true,false);
        }
        else if(i==2)
        {
            selectedImg(true,true,false,false);
        }
        else if(i==1)
        {
            selectedImg(true,false,false,false);
        }
        else {
            selectedImg(false,false,false,false);
        }
    }
}
