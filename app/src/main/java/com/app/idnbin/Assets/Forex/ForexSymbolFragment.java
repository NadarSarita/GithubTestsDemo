package com.app.idnbin.Assets.Forex;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.idnbin.Assets.Base.Forex;
import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.R;

import java.util.ArrayList;

public class ForexSymbolFragment extends Fragment implements SearchView.OnQueryTextListener {
    TextView Tv_Volume, Tv_Today, Tv_Spread, Tv_Leverage;
    private ForexAdapter forexAdapter;
    private boolean status = false;
    private Forex symbols = new Forex();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forex_symbol, container, false);
        RecyclerView Rv_Forex = view.findViewById(R.id.Rv_Forex);
        Tv_Volume = view.findViewById(R.id.Tv_Volume);
        SearchView searchView = view.findViewById(R.id.SearchView);
        int options = searchView.getImeOptions();
        searchView.setImeOptions(options| EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        Tv_Volume = view.findViewById(R.id.Tv_Volume);
        Tv_Today = view.findViewById(R.id.Tv_Today);
        Tv_Spread = view.findViewById(R.id.Tv_Spread);
        Tv_Leverage = view.findViewById(R.id.Tv_Leverage);

        LinearLayout first = (LinearLayout) searchView.getChildAt(0);
        LinearLayout second = (LinearLayout) first.getChildAt(2);
        LinearLayout third = (LinearLayout) second.getChildAt(1);
        SearchView.SearchAutoComplete autoComplete = (SearchView.SearchAutoComplete) third.getChildAt(0);
        autoComplete.setTextColor(Color.WHITE);
        autoComplete.setTextSize(12);

        ArrayList<SymbolsData> symlist = symbols.getForexlist();
        Rv_Forex.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Rv_Forex.setItemAnimator(new DefaultItemAnimator());
        forexAdapter = new ForexAdapter(getContext(), symlist);
        Rv_Forex.setAdapter(forexAdapter);

        searchView.setOnQueryTextListener(this);

        Tv_Volume.setOnClickListener(v1 -> {
            if (status) {
                Tv_Volume.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_arrow_up, 0, 0, 0);
                forexAdapter.sort("asc");
                Rv_Forex.setAdapter(forexAdapter);
                status = false;
            } else {
                Tv_Volume.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_arrow_drop_down, 0, 0, 0);
                forexAdapter.sort("des");
                status = true;
            }
        });
        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        forexAdapter.filter(newText);
        return false;
    }
}
