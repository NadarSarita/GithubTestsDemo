package com.app.idnbin.HomeScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DigitaltimeAdapter extends RecyclerView.Adapter<DigitaltimeAdapter.ViewHolder> {
    Context context;
    //    private data[] movieList;
    private ArrayList<String> digitaltimedata;

    public DigitaltimeAdapter(Context applicationContext, ArrayList<String> digitaltimedata) {
        this.digitaltimedata = digitaltimedata;
    }
    @NonNull
    @Override
    public DigitaltimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_digitaltime,parent,false);
       return new DigitaltimeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DigitaltimeAdapter.ViewHolder holder, int position) {

        holder.s1.setText(digitaltimedata.get(position));

    }

    @Override
    public int getItemCount() {
        return digitaltimedata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView s1,s2,t1,t2;
        ImageButton i;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            s1=itemView.findViewById(R.id.strike1);
            s2=itemView.findViewById(R.id.strike2);
            t1=itemView.findViewById(R.id.totalprofit1);
            t2=itemView.findViewById(R.id.totalprofit2);
        }
    }
}
