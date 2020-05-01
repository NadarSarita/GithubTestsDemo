package com.app.idnbin.SymbolInfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.R;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;


public class SymbolInfoFragment extends Fragment {

    TabLayout TlInfo;
    ViewPager VpInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_symbol_info, container, false);

        TlInfo = v.findViewById(R.id.TlInfo);
        VpInfo = v.findViewById(R.id.VpInfo);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        VpInfo.setAdapter(adapter);
        TlInfo.setupWithViewPager(VpInfo);

        return v;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new InfoFragment();
            switch (position){
                case 0 : fragment = new InfoFragment(); break;
                case 1 : fragment = new ConditionsFragment(); break;
//                case 2 : fragment = new EarningFragment(); break;
            }
            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "Tabs";
            switch (position){
                case 0:title = "INFO"; break;
                case 1:title = "CONDITIONS"; break;
//                case 2:title = "Earning"; break;

            }
            return title;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
