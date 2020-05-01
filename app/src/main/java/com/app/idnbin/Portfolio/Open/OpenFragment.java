package com.app.idnbin.Portfolio.Open;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.idnbin.R;

public class OpenFragment extends Fragment implements View.OnClickListener {
    RelativeLayout relative_layout,relativeLayout_digital,relaive_forexlayout,relativeLayout_porfolio,relative_commoditylayout
            ,relative_stocklayout,relativeLayout_porfolio1,relativeLayout_porfolio2;
    private ImageView IV_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_open, container, false);

        relative_layout = v.findViewById(R.id.relative_layout);
        relativeLayout_digital = v.findViewById(R.id.relativeLayout_digital);
        relaive_forexlayout = v.findViewById(R.id.relaive_forexlayout);
        relativeLayout_porfolio = v.findViewById(R.id.relativeLayout_porfolio);
        relative_commoditylayout = v.findViewById(R.id.relative_commoditylayout);
        relative_stocklayout = v.findViewById(R.id.relative_stocklayout);
        relativeLayout_porfolio1= v.findViewById(R.id.relativeLayout_porfolio1);
        relativeLayout_porfolio2= v.findViewById(R.id.relativeLayout_porfolio2);

        IV_setting= v.findViewById(R.id.IV_setting);

        relative_layout.setOnClickListener(this);
        relaive_forexlayout.setOnClickListener(this);
        relative_commoditylayout.setOnClickListener(this);
        relative_stocklayout.setOnClickListener(this);
        IV_setting.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relative_layout:
                if (relativeLayout_digital.getVisibility()==View.VISIBLE){
                    relativeLayout_digital.setVisibility(View.GONE);
                }else {
                    relativeLayout_digital.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.relaive_forexlayout:
                if (relativeLayout_porfolio.getVisibility()==View.VISIBLE){
                    relativeLayout_porfolio.setVisibility(View.GONE);
                }else {
                    relativeLayout_porfolio.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.relative_commoditylayout:
                if (relativeLayout_porfolio2.getVisibility()==View.VISIBLE){
                    relativeLayout_porfolio2.setVisibility(View.GONE);
                }else {
                    relativeLayout_porfolio2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.relative_stocklayout:

                if (relativeLayout_porfolio1.getVisibility()==View.VISIBLE){
                    relativeLayout_porfolio1.setVisibility(View.GONE);
                }else {
                    relativeLayout_porfolio1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.IV_setting:
                startActivity(new Intent(getContext(),PortfolioSettingActivity.class));
        }
    }
}
