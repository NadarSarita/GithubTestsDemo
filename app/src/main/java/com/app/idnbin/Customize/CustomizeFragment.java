package com.app.idnbin.Customize;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomizeFragment extends Fragment implements StartDragListener {

    @BindView(R.id.customizeRecyclerView)
    RecyclerView customizeRecyclerView;
    CustomizeRecyclerViewAdapter customizeRecyclerViewAdapter;
    HomeActivity homeActivity;
    ItemTouchHelper itemTouchHelper;
    ArrayList<CustomizeSymbol> customizeArrayList;

    @BindView(R.id.IvCustomize)
    ImageView IvCustomize;

    public CustomizeFragment(ArrayList<CustomizeSymbol> customizeArrayList) {
        // Required empty public constructor
        this.customizeArrayList=customizeArrayList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if ( getActivity() instanceof HomeActivity){
            homeActivity = (HomeActivity) getActivity();
        }
        View view=inflater.inflate(R.layout.fragment_customize, container, false);
        ButterKnife.bind(this,view);
        IvCustomize.setSelected(true);
        setDataToRecyclerView();
        return view;
    }

    private void setDataToRecyclerView() {
        customizeRecyclerViewAdapter = new CustomizeRecyclerViewAdapter(customizeArrayList,this);

        ItemTouchHelper.Callback callback= new ItemMoveCallback(customizeRecyclerViewAdapter);
        itemTouchHelper=new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(customizeRecyclerView);
        customizeRecyclerView.setAdapter(customizeRecyclerViewAdapter);
    }

    @Override
    public void requestDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }


    @OnClick(R.id.IvCustomize)
    public void customizeClick(View view){
        FragmentTransaction fragmentTransaction = homeActivity.getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        homeActivity.customizeFun(fragmentTransaction);
        homeActivity.setPref(homeActivity, "tabList", new Gson().toJson(customizeArrayList));
        customizeRecyclerViewAdapter.notifyDataSetChanged();
        fragmentTransaction.commit();
    }

}
