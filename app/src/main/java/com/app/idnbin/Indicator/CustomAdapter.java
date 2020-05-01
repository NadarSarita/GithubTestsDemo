package com.app.idnbin.Indicator;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.app.idnbin.R;
import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<String> indicatorlist;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> personNames) {
        this.context = context;
        this.indicatorlist = personNames;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_allindicator, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.TvADX.setText(indicatorlist.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (context instanceof IndiActivity) {
                    ((IndiActivity)context).alertMethod();
                }
            }
        });




    }


    @Override
    public int getItemCount() {
        return indicatorlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TvADX;// init the item view's

        public MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            TvADX = (TextView) itemView.findViewById(R.id.TvADX);

        }
    }



}
