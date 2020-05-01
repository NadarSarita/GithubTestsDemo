package com.app.idnbin.Assets.Digital;

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

import com.app.idnbin.Assets.Base.Digital;
import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.R;

import java.util.ArrayList;

public class DigitalSymbolFragment extends Fragment implements SearchView.OnQueryTextListener {
    DigitalAdapter digitalAdapter;
    TextView Tv_Asset, Tv_Hour, Tv_Profit;
    private Digital symbols = new Digital();
    boolean status = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_digital_symbol, container, false);
        RecyclerView Rv_Digital = v.findViewById(R.id.Rv_Digital);
        SearchView searchView = v.findViewById(R.id.SearchView);
        int options = searchView.getImeOptions();
        searchView.setImeOptions(options| EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        Tv_Asset = v.findViewById(R.id.Tv_Asset);
        Tv_Hour = v.findViewById(R.id.Tv_Hour);
        Tv_Profit = v.findViewById(R.id.Tv_Profit);

        LinearLayout first = (LinearLayout) searchView.getChildAt(0);
        LinearLayout second = (LinearLayout) first.getChildAt(2);
        LinearLayout third = (LinearLayout) second.getChildAt(1);
        SearchView.SearchAutoComplete autoComplete = (SearchView.SearchAutoComplete) third.getChildAt(0);
        autoComplete.setPadding(5,5,5,5);
        autoComplete.setTextColor(Color.WHITE);
        autoComplete.setTextSize(12);

        ArrayList<SymbolsData> symlist = symbols.getDigitallist();

        Rv_Digital.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Rv_Digital.setItemAnimator(new DefaultItemAnimator());
        digitalAdapter = new DigitalAdapter(getContext(), symlist);
        Rv_Digital.setAdapter(digitalAdapter);

        searchView.setOnQueryTextListener(this);

        Tv_Asset.setOnClickListener(v1 -> {
            if (status) {
                Tv_Asset.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_arrow_up, 0, 0, 0);
                digitalAdapter.sort("asc");
                status = false;
            } else {
                Tv_Asset.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_arrow_drop_down, 0, 0, 0);
                digitalAdapter.sort("des");
                status = true;
            }
        });
        return v;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        digitalAdapter.filter(newText);
        return false;
    }
}
