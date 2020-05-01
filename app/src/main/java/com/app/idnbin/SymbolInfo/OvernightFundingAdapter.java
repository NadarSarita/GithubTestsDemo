package com.app.idnbin.SymbolInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

import java.util.ArrayList;

public class OvernightFundingAdapter extends RecyclerView.Adapter<OvernightFundingAdapter.ScheduleViewHolder> {
    private Context context;
    private ArrayList<OvernightFundingData> overnightFundingDataArrayList;

    public OvernightFundingAdapter(Context context, ArrayList<OvernightFundingData> overnightFundingDataArrayList){
        this.context = context;
        this.overnightFundingDataArrayList = overnightFundingDataArrayList;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_overnight_funding_list, parent, false);
            return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        OvernightFundingData item =overnightFundingDataArrayList.get(position);
        holder.tv_of_time.setText(item.getOvernightFundingTime());

            holder.tv_of_sell.setText(item.getOvernightFundingSell());
            holder.tv_of_buy.setText(item.getOvernightFundingBuy());
            holder.tv_of_date.setText(item.getOvernightFundingDay());


    }

    @Override
    public int getItemCount() {
        return overnightFundingDataArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView tv_of_time, tv_of_date, tv_of_sell,tv_of_buy;
        ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_of_time=itemView.findViewById(R.id.tv_of_time);
            tv_of_date=itemView.findViewById(R.id.tv_of_date);
            tv_of_sell = itemView.findViewById(R.id.tv_of_sell);
            tv_of_buy = itemView.findViewById(R.id.tv_of_buy);
        }
    }

}
