package com.app.idnbin.Profile.History.Trading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

import java.util.ArrayList;

public class AssetAdapter extends RecyclerView.Adapter<AssetAdapter.ItemViewHolder>{

    private Context context;
    ArrayList<AssetData> assetArray;
    AssetCheckedInterface assetCheckedInterface;

    public AssetAdapter(Context context, ArrayList<AssetData> assetArray, AssetCheckedInterface assetCheckedInterface) {
        this.context = context;
        this.assetArray = assetArray;
        this.assetCheckedInterface=assetCheckedInterface;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_asset,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.CbAsset.setText(assetArray.get(position).getAssetName());
        holder.CbAsset.setChecked(assetArray.get(position).isSelected());
        holder.CbAsset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetCheckedInterface.assetChecked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return assetArray.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CheckBox CbAsset;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            CbAsset = itemView.findViewById(R.id.CbAsset);
        }
    }

    interface AssetCheckedInterface{
        void assetChecked(int pos);
    }

}
