package com.app.idnbin.MovesAlerts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.app.idnbin.MovesAlerts.MyAdapter;
import com.app.idnbin.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovesAlertsFragment extends Fragment implements View.OnClickListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    TextView TvMovesAlertFilter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_moves_alerts, container, false);

        tabLayout = v.findViewById(R.id.tabLayout);
        viewPager = v.findViewById(R.id.viewPager);
        TvMovesAlertFilter = v.findViewById(R.id.TvMovesAlertFilter);

        TvMovesAlertFilter.setOnClickListener(this);

        tabLayout.addTab(tabLayout.newTab().setText("Moves"));
        tabLayout.addTab(tabLayout.newTab().setText("Alerts"));

        final MyAdapter adapter = new MyAdapter(getContext(),getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                Log.d("VALALALA",""+tab.getPosition());
                if (tab.getPosition() == 1)
                    TvMovesAlertFilter.setVisibility(View.INVISIBLE);
                else
                    TvMovesAlertFilter.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        int[] location = new int[2];
        TvMovesAlertFilter.getLocationOnScreen(location);
        final View mView = getLayoutInflater().inflate(R.layout.layout_pricemovement, null, false);
        final PopupWindow popUp = new PopupWindow(mView, 330, 250, false);
        popUp.setTouchable(true);
        popUp.setFocusable(true);
        popUp.setOutsideTouchable(true);
        popUp.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1]);



        TextView tv_allpricemovements,tv_pricemovements,TV_gap,TV_sharpjump;
        String pricemovement,allpricemovements,gap,sharpjump;

        tv_allpricemovements = mView.findViewById(R.id.tv_allpricemovements);
        tv_pricemovements =mView.findViewById(R.id.tv_pricemovements);
        TV_gap = mView.findViewById(R.id.TV_gap);
        TV_sharpjump = mView.findViewById(R.id.TV_sharpjump);

        pricemovement = tv_allpricemovements.getText().toString();
        allpricemovements =tv_allpricemovements.getText().toString();
        gap =  TV_gap.getText().toString();
        sharpjump =TV_sharpjump.getText().toString();

        TV_gap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvMovesAlertFilter.setText(gap);
                popUp.dismiss();
            }
        });

        tv_allpricemovements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvMovesAlertFilter.setText(allpricemovements);
                popUp.dismiss();
            }
        });

        TV_sharpjump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvMovesAlertFilter.setText(sharpjump);
                popUp.dismiss();
            }
        });

        tv_pricemovements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TvMovesAlertFilter.setText(pricemovement);
                popUp.dismiss();
            }
        });
    }
}
