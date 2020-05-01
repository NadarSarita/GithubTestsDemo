package com.app.idnbin.Assets.Crypto;

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

import com.app.idnbin.Assets.Base.Crypto;
import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.R;

import java.util.ArrayList;

public class CryptoSymbolFragment extends Fragment implements SearchView.OnQueryTextListener {
    TextView Tv_Volume, Tv_Today, Tv_Spread, Tv_Leverage;
    private CryptoAdapter cryptoAdapter;
    private boolean status = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crypto_symbol, container, false);
        RecyclerView Rv_Crypto = view.findViewById(R.id.Rv_Crypto);
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

        Crypto symbols = new Crypto();
        ArrayList<SymbolsData> symlist = symbols.getCryptolist();

        Rv_Crypto.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Rv_Crypto.setItemAnimator(new DefaultItemAnimator());
        cryptoAdapter = new CryptoAdapter(getContext(), symlist);
        Rv_Crypto.setAdapter(cryptoAdapter);

        searchView.setOnQueryTextListener(this);

        Tv_Volume.setOnClickListener(v1 -> {
            if (status) {
                Tv_Volume.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_arrow_up, 0, 0, 0);
                cryptoAdapter.sort("asc");
                status = false;
            } else {
                Tv_Volume.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_arrow_drop_down, 0, 0, 0);
                cryptoAdapter.sort("des");
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
        cryptoAdapter.filter(newText);
        return false;
    }

}
