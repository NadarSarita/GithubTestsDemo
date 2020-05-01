package com.app.idnbin.Indicator;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.idnbin.R;

import java.util.ArrayList;
import java.util.Arrays;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Trend extends Fragment {
    ArrayList indicatorlist = new ArrayList<>(Arrays.asList("  ADX", "  ATR", "  ATR Bands", "  ATR Trailing Stops", "  Accumulation/Distribution", "  Accumulative Swing Index", "  Alligator", "  Arnaud Legoux Moving Average(ALMA)", "  Aroon","  Aroon Oscillator", "  Balance of Power"));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//      View v =   inflater.inflate(R.layout.fragment_trend, container, false);
        View v = inflater.inflate(R.layout.fragment_all_indicator, container, false);

        RecyclerView recyclerview_allindicator = (RecyclerView) v.findViewById(R.id.recyclerview_allindicator);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview_allindicator.setLayoutManager(linearLayoutManager);
        CustomAdapter customAdapter = new CustomAdapter(getContext(), indicatorlist);
        recyclerview_allindicator.setAdapter(customAdapter);
      return v;
    }
}
