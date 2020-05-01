package com.app.idnbin.MovesAlerts;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.LoginRegister.UserDetails;
import com.app.idnbin.R;
import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.Viewholder> {
    Context context;
    ArrayList<UserDetails> alertList ;

    private DatabaseReference mDatabase;

    public  AlertAdapter (Context context , ArrayList<UserDetails> alertList){
        this.context =context;
        this.alertList = alertList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alerts_list,parent,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM, HH:mm");
        String currentDateandTime = sdf.format(new Date());
        holder.tv_cncy.setText(alertList.get(position).getAlertPrice());
        holder.tv_prnt.setText(currentDateandTime);
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }

    public  class Viewholder extends RecyclerView.ViewHolder{

        TextView tv_cncy,tv_prnt;

        public Viewholder(@NonNull View itemView) {

            super(itemView);
            tv_cncy = itemView.findViewById(R.id.tv_cncy);
            tv_prnt = itemView.findViewById(R.id.tv_prnt);
        }
    }

}

