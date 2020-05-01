package com.app.idnbin.Portfolio;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.app.idnbin.Portfolio.Closed.ClosedFragment;
import com.app.idnbin.Portfolio.Open.OpenFragment;
import com.app.idnbin.Portfolio.Pending.PendingFragment;
import com.app.idnbin.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class PortfolioFragment extends Fragment implements View.OnClickListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    TextView TvPortfolioPopup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_portfolio, container, false);

        tabLayout = v.findViewById(R.id.tl_portfolio);
        viewPager = v.findViewById(R.id.vp_fragment);

        TvPortfolioPopup = v.findViewById(R.id.TvPortfolioPopup);

        TvPortfolioPopup.setOnClickListener(this);

        TabLayout.Tab open = tabLayout.newTab();
        open.setText("Open");
        tabLayout.addTab(open);

        TabLayout.Tab pending = tabLayout.newTab();
        pending.setText("Pending");
        tabLayout.addTab(pending);

        TabLayout.Tab closed = tabLayout.newTab();
        closed.setText("Closed");
        tabLayout.addTab(closed);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

    @Override
    public void onClick(View v) {
        int[] location = new int[2];
        TvPortfolioPopup.getLocationOnScreen(location);
        final View mView = getLayoutInflater().inflate(R.layout.layout_portfolio_allassets, null, false);
        final PopupWindow popUp = new PopupWindow(mView, 350, 430, false);
        popUp.setTouchable(true);
        popUp.setFocusable(true);
        popUp.setOutsideTouchable(true);
        popUp.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1]);
        popUp.setOutsideTouchable(true);

        TextView TV_noexpiration, TV_allassets, TV_cfd, TV_forex, TV_crypto, TV_expiration, TV_digital;
        String noexpiration, allassets, cfd, forex, crpto, expiration, digital;


        TV_noexpiration = mView.findViewById(R.id.TV_noexpiration);
        TV_allassets = mView.findViewById(R.id.TV_allassets);
        TV_cfd = mView.findViewById(R.id.TV_cfd);
        TV_forex = mView.findViewById(R.id.TV_forex);
        TV_crypto = mView.findViewById(R.id.TV_crypto);
        TV_expiration = mView.findViewById(R.id.TV_expiration);
        TV_digital = mView.findViewById(R.id.TV_digital);

        noexpiration = TV_noexpiration.getText().toString();
        allassets = TV_allassets.getText().toString();
        cfd = TV_cfd.getText().toString();
        forex = TV_forex.getText().toString();
        crpto = TV_crypto.getText().toString();
        expiration = TV_expiration.getText().toString();
        digital = TV_digital.getText().toString();


        TV_noexpiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvPortfolioPopup.setText(noexpiration);
                popUp.dismiss();

            }
        });
        TV_allassets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvPortfolioPopup.setText(allassets);
                popUp.dismiss();

            }
        });
        TV_cfd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvPortfolioPopup.setText(cfd);
                popUp.dismiss();

            }
        });
        TV_forex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvPortfolioPopup.setText(forex);
                popUp.dismiss();

            }
        });
        TV_crypto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvPortfolioPopup.setText(crpto);
                popUp.dismiss();

            }
        });
        TV_expiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvPortfolioPopup.setText(expiration);
                popUp.dismiss();

            }
        });
        TV_digital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvPortfolioPopup.setText(digital);
                popUp.dismiss();

            }
        });
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new OpenFragment();
            switch (position) {
                case 0:
                    fragment = new OpenFragment();
                    break;
                case 1:
                    fragment = new PendingFragment();
                    break;
                case 2:
                    fragment = new ClosedFragment();
                    break;
            }
            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "Tabs";
            switch (position) {
                case 0:
                    title = "Open";
                    break;
                case 1:
                    title = "Pending";
                    break;
                case 2:
                    title = "Closed";
                    break;

            }
            return title;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
