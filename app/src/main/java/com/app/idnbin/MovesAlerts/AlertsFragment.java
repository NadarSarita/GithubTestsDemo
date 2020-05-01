package com.app.idnbin.MovesAlerts;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.LoginRegister.UserDetails;
import com.app.idnbin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AlertsFragment extends DialogFragment implements View.OnClickListener {

    ImageView IVAddAlert;
    RecyclerView recyclerview_alerts;
    ArrayList<UserDetails> userDetails = new ArrayList<UserDetails>();
    AlertAdapter alertAdapter;
    HomeActivity homeActivity;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (getActivity() instanceof HomeActivity) {
            homeActivity = (HomeActivity) getActivity();
        }
        View view = inflater.inflate(R.layout.fragment_alerts, container, false);

        IVAddAlert = view.findViewById(R.id.IVAddAlert);
        recyclerview_alerts = view.findViewById(R.id.recyclerview_alerts);

        IVAddAlert.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserDetails");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    UserDetails ud = dataSnapshot1.getValue(UserDetails.class);
                    userDetails.add(ud);
                }
                alertAdapter = new AlertAdapter(getActivity(), userDetails);
                recyclerview_alerts.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerview_alerts.setAdapter(alertAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;

    }

    @Override
    public void onClick(View v) {
        homeActivity.setAlertVisibility();
    }
}



