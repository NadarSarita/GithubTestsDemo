package com.app.idnbin.SymbolInfo;

import android.os.Bundle;

import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.R;

import java.util.Objects;

public class InfoFragment extends Fragment implements View.OnClickListener {

    public InfoFragment() {
        // Required empty public constructor
    }

    SeekBar SbTrader, SbMinutes, SbHour, SbDay, SbWeek, SbMonth;
    TextView TvMorePeriods;
    Group group;

    ImageView Tvstar, Tvbell;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        Tvstar = view.findViewById(R.id.Tvstar);
        Tvbell = view.findViewById(R.id.Tvbell);
        SbTrader = view.findViewById(R.id.SbTrader);
        SbMinutes = view.findViewById(R.id.SbMinutes);
        SbHour = view.findViewById(R.id.SbHour);
        SbDay = view.findViewById(R.id.SbDay);
        SbWeek = view.findViewById(R.id.SbWeek);
        SbMonth = view.findViewById(R.id.SbMonth);
        TvMorePeriods=view.findViewById(R.id.TvMorePeriods);
        group=view.findViewById(R.id.group);
        TvMorePeriods.setOnClickListener(this);

        Tvbell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) Objects.requireNonNull(getActivity())).setAlertVisibility();
            }
        });

        /*Tvstar.setOnClickListener(new View.OnClickListener() {
            private boolean stateChanged;
            @Override
            public void onClick(View v) {
                if(stateChanged) {
                    // reset background to default;
                    Tvstar.setBackgroundResource(R.drawable.ic_star_grey);
                } else {
                    Tvstar.setBackgroundResource(R.drawable.ic_star);
                }
                stateChanged = !stateChanged;
            }
        });*/

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.TvMorePeriods:
                TvMorePeriods.setVisibility(View.GONE);
                group.setVisibility(View.VISIBLE);
                break;
        }
    }
}
