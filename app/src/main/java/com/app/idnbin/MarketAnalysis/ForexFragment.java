package com.app.idnbin.MarketAnalysis;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.app.idnbin.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ForexFragment extends Fragment {
    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forex, container, false);

        webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setBackgroundColor(Color.BLACK);
        webView.loadUrl("file:///android_asset/forex.html");
        webView.getSettings().setLoadWithOverviewMode(true);

        return view;
    }

}
