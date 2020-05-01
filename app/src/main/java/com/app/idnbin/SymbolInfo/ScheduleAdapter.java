package com.app.idnbin.SymbolInfo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private Context context;
    private ArrayList<ScheduleData> scheduleDataArrayList;

    public ScheduleAdapter(Context context, ArrayList<ScheduleData> scheduleDataArrayList){
        this.context = context;
        this.scheduleDataArrayList = scheduleDataArrayList;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_schedule_list, parent, false);
            return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        ScheduleData item =scheduleDataArrayList.get(position);
        holder.tv_schedule_time.setText(item.getScheduleTime());

            holder.tv_schedule_day.setText(item.getScheduleDay());
            holder.tv_schedule_date.setText(item.getScheduleDate());

    }

    @Override
    public int getItemCount() {
        return scheduleDataArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView tv_schedule_day, tv_schedule_date, tv_schedule_time;
        ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_schedule_date=itemView.findViewById(R.id.tv_schedule_date);
            tv_schedule_day=itemView.findViewById(R.id.tv_schedule_day);
            tv_schedule_time = itemView.findViewById(R.id.tv_schedule_time);
        }
    }

}
