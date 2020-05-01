package com.app.idnbin.Customize;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomizeRecyclerViewAdapter extends RecyclerView.Adapter<CustomizeRecyclerViewAdapter.CustomizeViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {
    ArrayList<CustomizeSymbol> customizeArrayList;
    StartDragListener startDragListener;
    private static final String TAG = "CustomizeRecyclerViewAd";

    public CustomizeRecyclerViewAdapter(ArrayList<CustomizeSymbol> customizeArrayList, StartDragListener startDragListener) {
        this.startDragListener = startDragListener;
        this.customizeArrayList = customizeArrayList;
    }


    @NonNull
    @Override
    public CustomizeRecyclerViewAdapter.CustomizeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_customize_item, parent, false);
        return new CustomizeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomizeRecyclerViewAdapter.CustomizeViewHolder holder, int position) {
        holder.txtCustomize.setText(customizeArrayList.get(position).customizeName);
        holder.txtCustomize.setCompoundDrawablesWithIntrinsicBounds(customizeArrayList.get(position).customizeDrawable, 0, 0, 0);

        if(customizeArrayList.get(position).getCustomizeView()==1)
        {
            holder.imgVisiblity.setSelected(false);
        }
        else {
            holder.imgVisiblity.setSelected(true);
        }

        holder.imgVisiblity.setOnClickListener((v) -> {
            if (holder.imgVisiblity.isSelected()) {
                holder.imgVisiblity.setSelected(false);
                customizeArrayList.get(position).setCustomizeView(1);
            } else {
                holder.imgVisiblity.setSelected(true);
                customizeArrayList.get(position).setCustomizeView(0);
            }
        });
        holder.imgHandler.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startDragListener.requestDrag(holder);
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return customizeArrayList.size();
    }

    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
      /*  customizeArrayList.add(toPosition,customizeArrayList.get(fromPosition));
        customizeArrayList.remove(fromPosition);*/

        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(customizeArrayList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(customizeArrayList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public class CustomizeViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtCustomize)
        TextView txtCustomize;
        @BindView(R.id.imgHandler)
        ImageView imgHandler;
        @BindView(R.id.imgVisiblity)
        ImageView imgVisiblity;
        View view;

        public CustomizeViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);

        }
    }
}
