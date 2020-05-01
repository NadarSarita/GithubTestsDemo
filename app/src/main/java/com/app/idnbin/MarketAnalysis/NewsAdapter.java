package com.app.idnbin.MarketAnalysis;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;
import com.app.idnbin.WebView.WebViewActivity;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    Newsfeed newsfeed;
    int count;

    public NewsAdapter(Context context, Newsfeed newsfeed, int count){
        this.context = context;
       this.newsfeed = newsfeed;
       this.count = count;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.TV_title.setText(newsfeed.getItemCounts().get(position).title);
        holder.TV_headlilne.setText(newsfeed.getItemCounts().get(position).description);
            holder.TV_timezone.setText(newsfeed.getItemCounts().get(position).pubDate);




//        if(newsfeed.getItemCounts().get(position).enclosure!=null)
//        {
//            Glide.with(context)
//                    .load(newsfeed.getItemCounts().get(position).enclosure.url)
//                    .apply(new RequestOptions()
//                            .placeholder(R.drawable.ic_launcher_foreground)
//                            .error(R.drawable.ic_launcher_foreground))
//                    .into(holder.IMG_image);
//        }

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url", newsfeed.getItemCounts().get(position).link);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
       Log.d("","aaaaaaa"+count);
        return count;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView IMG_image;
        CardView card_view;
        TextView TV_headlilne,TV_title,TV_timezone,TVweb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card_view = itemView.findViewById(R.id.card_view);
            TV_headlilne = itemView.findViewById(R.id.TV_headlilne);
            TV_title = itemView.findViewById(R.id.TV_title);
            TV_timezone = itemView.findViewById(R.id.TV_timezone);
//            IMG_image = itemView.findViewById(R.id.IMG_image);
        }
    }
}
