package com.app.idnbin.Portfolio.Closed;


import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.idnbin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClosedFragment extends Fragment implements View.OnClickListener {

    RelativeLayout layoutclosed,forexlayout,cryptolayout;
    TextView TV_icclosed;
    AlertDialog balancedialog,forexdialog,cryptodialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_closed, container, false);

        layoutclosed = v.findViewById(R.id.layoutclosed);
        forexlayout = v.findViewById(R.id.forexlayout);
        cryptolayout = v.findViewById(R.id.cryptolayout);



        layoutclosed.setOnClickListener(this);
        forexlayout.setOnClickListener(this);
        cryptolayout.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layoutclosed:
                 balancedialog = new AlertDialog.Builder(getContext()).create();
                balancedialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                balancedialog.setView(getLayoutInflater().inflate(R.layout.layout_result_dialog, null));
                balancedialog.show();
                TV_icclosed = balancedialog.findViewById(R.id.TV_icclosed);
                TV_icclosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        balancedialog.dismiss();
                    }
                });
                break;
            case R.id.forexlayout:
                forexdialog = new AlertDialog.Builder(getContext()).create();
                forexdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                forexdialog.setView(getLayoutInflater().inflate(R.layout.layout_digitalclosed_dialog, null));
                forexdialog.show();
                TV_icclosed = forexdialog.findViewById(R.id.TV_icclosed);
                TV_icclosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        forexdialog.dismiss();
                    }
                });

                break;
            case R.id.cryptolayout:
                cryptodialog = new AlertDialog.Builder(getContext()).create();
                cryptodialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                cryptodialog.setView(getLayoutInflater().inflate(R.layout.layout_result_dialog,null));
                cryptodialog.show();
                TV_icclosed = cryptodialog.findViewById(R.id.TV_icclosed);
                TV_icclosed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cryptodialog.dismiss();
                    }
                });
                break;

        }
    }
}
