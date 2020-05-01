package com.app.idnbin.Profile.Settings.Security;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

public class ActiveAdapter extends RecyclerView.Adapter<ActiveAdapter.itemViewHolder> {

    Context context;
    public ActiveAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public itemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_active_session,parent,false);
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull itemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class itemViewHolder extends RecyclerView.ViewHolder {
        public itemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
