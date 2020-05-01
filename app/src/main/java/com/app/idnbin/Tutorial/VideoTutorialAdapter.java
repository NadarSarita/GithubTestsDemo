package com.app.idnbin.Tutorial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;

import java.util.ArrayList;

public class VideoTutorialAdapter extends RecyclerView.Adapter<VideoTutorialAdapter.ViewHolder> {
    ArrayList<String> videolist;
    Context context;
    int[] icon;
    public VideoTutorialAdapter(Context context,ArrayList<String> videolist,int[] icon){
        this.context = context;
        this.videolist = videolist;
        this.icon = icon;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_videotutorial,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.TV_tutorialname.setText(videolist.get(position));
        holder.IV_video.setImageResource(icon[position]);

        holder.RL_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), VideoActivity.class);
                intent.putExtra("Header", videolist.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videolist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView IV_video;
        TextView TV_tutorialname,TV_tutorialcount;
        RelativeLayout RL_Video;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IV_video = itemView.findViewById(R.id.IV_video);
            TV_tutorialcount= itemView.findViewById(R.id.TV_tutorialcount);
            TV_tutorialname = itemView.findViewById(R.id.TV_tutorialname);
            RL_Video = itemView.findViewById(R.id.RL_Video);
        }
    }
}
