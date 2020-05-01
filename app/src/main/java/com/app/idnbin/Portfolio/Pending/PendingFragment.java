package com.app.idnbin.Portfolio.Pending;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.app.idnbin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PendingFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout relative_layout,relativeLayout_porfolio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending, container, false);

        relative_layout = view.findViewById(R.id.relative_layout);
        relativeLayout_porfolio = view.findViewById(R.id.relativeLayout_porfolio);
        relative_layout.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (relativeLayout_porfolio.getVisibility()==View.VISIBLE){
            relativeLayout_porfolio.setVisibility(View.GONE);
        }else {
            relativeLayout_porfolio.setVisibility(View.VISIBLE);
        }
    }
}
