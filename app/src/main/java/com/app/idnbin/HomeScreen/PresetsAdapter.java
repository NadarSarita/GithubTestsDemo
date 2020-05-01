package com.app.idnbin.HomeScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

import java.util.ArrayList;
import java.util.List;

public class PresetsAdapter extends RecyclerView.Adapter<PresetsAdapter.ItemViewHolder> {

    List<String> a;
    private Context context;
    private onItemClickListner onItemClickListner;

    public PresetsAdapter(Context context, ArrayList<String> a) {
        this.context = context;
        this.a = a;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_presets, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.TvPresets.setText(a.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListner.onClick(a,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return a.size();
    }

    public void onItemClickListner(PresetsAdapter.onItemClickListner onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public interface onItemClickListner{
        void onClick(List<String> list, int potition);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView TvPresets;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            TvPresets = itemView.findViewById(R.id.TvPresets);
        }
    }
}
