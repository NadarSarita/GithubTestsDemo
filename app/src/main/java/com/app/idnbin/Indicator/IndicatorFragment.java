package com.app.idnbin.Indicator;

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
import android.widget.SearchView;

import com.app.idnbin.Portfolio.Open.OpenFragment;
import com.app.idnbin.R;
import com.google.android.material.tabs.TabLayout;


public class IndicatorFragment extends Fragment {

    TabLayout tl_indicator;
    ViewPager vp_fragment;
    SearchView SVIndicator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_indicator, container, false);


        tl_indicator = v.findViewById(R.id.tl_indicator);
        vp_fragment = v.findViewById(R.id.vp_fragment);
        SVIndicator =v.findViewById(R.id.SVIndicator);

        TabLayout.Tab open = tl_indicator.newTab();
        open.setText("ALL INDICATORS");
        tl_indicator.addTab(open);

        TabLayout.Tab favorites = tl_indicator.newTab();
        favorites.setText("FAVORITES");
        tl_indicator.addTab(favorites);

        TabLayout.Tab popular = tl_indicator.newTab();
        popular.setText("POPULAR");
        tl_indicator.addTab(popular);

        TabLayout.Tab momentum = tl_indicator.newTab();
        momentum.setText("MOMENTUM");
        tl_indicator.addTab(momentum);

        TabLayout.Tab trend = tl_indicator.newTab();
        trend.setText("TREND");
        tl_indicator.addTab(trend);

        TabLayout.Tab volatility = tl_indicator.newTab();
        volatility.setText("VOLATILITY");
        tl_indicator.addTab(volatility);

        TabLayout.Tab  movingavg= tl_indicator.newTab();
        movingavg.setText("MOVING AVERAGES");
        tl_indicator.addTab(movingavg);


        TabLayout.Tab  volume= tl_indicator.newTab();
        volume.setText("VOLUME");
        tl_indicator.addTab(volume);

        TabLayout.Tab  other= tl_indicator.newTab();
        other.setText("OTHER");
        tl_indicator.addTab(other);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        vp_fragment.setAdapter(adapter);
        tl_indicator.setupWithViewPager(vp_fragment);

        return v;
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new OpenFragment();
            switch (position){
                case 0 : fragment = new AllIndicator(); break;
                case 1 : fragment = new Favourites(); break;
                case 2 : fragment = new Popular(); break;
                case 3 : fragment = new Momentum(); break;
                case 4 : fragment = new Trend(); break;
                case 5 : fragment = new Volatility(); break;
                case 6 : fragment = new MovingAverages(); break;
                case 7 : fragment = new Volume();break;
                case 8 : fragment = new Other();break;
  }
            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "Tabs";
            switch (position){
                case 0:title = "ALL INDICATORS"; break;
                case 1:title = "FAVORITES"; break;
                case 2:title = "POPULAR"; break;
                case 3:title = "MOMENTUM";break;
                case 4:title = "TREND";break;
                case 5:title = "VOLATILITY";break;
                case 6:title = "MOVING AVERAGES";break;
                case 7:title = "VOLUME";break;
                case 8:title ="OTHER";break;

            }
            return title;
        }

        @Override
        public int getCount() {
            return 8;
        }
    }

}
