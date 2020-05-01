package com.app.idnbin.Assets.Crypto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.Assets.Base.SymbolsData;
import com.app.idnbin.HomeScreen.HomeActivity;
import com.app.idnbin.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<SymbolsData> symbolsDataList;
    private ArrayList<SymbolsData> symbolsDataArrayList = new ArrayList<>();

    CryptoAdapter(Context context, ArrayList<SymbolsData> symbolsDataList) {
        this.context = context;
        this.symbolsDataList = symbolsDataList;
        this.symbolsDataArrayList.addAll(symbolsDataList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_forex_symbol, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.Tv_Volume.setText(symbolsDataList.get(position).getSymbolName());
        holder.Iv_Info.setOnClickListener(v -> {
            String bundle = "info_click";
            Intent intent = new Intent(context, HomeActivity.class);
            intent.putExtra("info", bundle);
            context.startActivity(intent);
        });
        holder.Iv_Alert.setOnClickListener(v -> {
            String bundle1 = "alert_click";
            Intent intent = new Intent(context, HomeActivity.class);
            intent.putExtra("alert", bundle1);
            context.startActivity(intent);
        });
        holder.Iv_Fav.setOnClickListener(v -> {
            swapeItem(position, 0);
        });
        Glide.with(context)
                .load(symbolsDataList.get(position).getImgaeUrl())
                .into(holder.Cv_Symbol);
    }

    @Override
    public int getItemCount() {
        return symbolsDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Tv_Volume, Tv_Today, Tv_Spread, Tv_Leverage;
        ImageView Iv_Info, Iv_Alert, Iv_Fav;
        CircleImageView Cv_Symbol;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Tv_Volume = itemView.findViewById(R.id.Tv_Volume);
            Tv_Today = itemView.findViewById(R.id.Tv_Today);
            Tv_Spread = itemView.findViewById(R.id.Tv_Spread);
            Tv_Leverage = itemView.findViewById(R.id.Tv_Leverage);
            Iv_Info = itemView.findViewById(R.id.Iv_Info);
            Iv_Alert = itemView.findViewById(R.id.Iv_Alert);
            Iv_Fav = itemView.findViewById(R.id.Iv_Fav);
            Cv_Symbol = itemView.findViewById(R.id.Cv_Symbol);
        }
    }

    private void swapeItem(int fromPosition, int toPosition){
        Collections.swap(symbolsDataList, fromPosition, 0);
        notifyItemMoved(fromPosition, toPosition);
    }

    void filter(String s) {
        s = s.toLowerCase();
        symbolsDataList.clear();
        if (s.length() == 0) {
            symbolsDataList.addAll(symbolsDataArrayList);
        } else {
            for (SymbolsData symbolsData : symbolsDataArrayList) {
                if (symbolsData.getSymbolName().toLowerCase().contains(s)) {
                    symbolsDataList.add(symbolsData);
                }
            }
        }
        notifyDataSetChanged();
    }

    void sort(String type) {
        if (type.equalsIgnoreCase("asc")) {
            Collections.sort(symbolsDataList, (o1, o2) -> {
                String symbol1 = o1.getSymbolName();
                String symbol2 = o2.getSymbolName();
                return symbol1.compareTo(symbol2);
            });
        } else {
            Collections.sort(symbolsDataList, (o1, o2) -> {
                String symbol1 = o1.getSymbolName();
                String symbol2 = o2.getSymbolName();
                return symbol2.compareTo(symbol1);
            });
        }
        notifyDataSetChanged();
    }
}
