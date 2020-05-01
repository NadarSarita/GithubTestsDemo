package com.app.idnbin.Profile.Support;

import android.content.Context;
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

import java.util.ArrayList;

public class SupportChatAdapter extends RecyclerView.Adapter<SupportChatAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BotChat> messages;
    private String currentuser;

    private static  final int MSG_TYPE_LEFT = 0;
    private static  final int MSG_TYPE_RIGHT = 1;

    public SupportChatAdapter(Context context, ArrayList<BotChat> messages, String currentuser){
        this.context = context;
        this.messages = messages;
        this.currentuser=currentuser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_right, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.chat_item_left, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BotChat item =messages.get(position);
        holder.user_msg.setText(item.getMessage());

        Log.i("TAG", "onBindViewHolder : "+currentuser);
        Log.i("TAG", "onBindViewHolder Sender : "+item.getSender());
        if(item.getSender().equals(currentuser))
        {
            holder.username.setVisibility(View.GONE);
        }
        else {
            holder.username.setVisibility(View.VISIBLE);
            holder.username.setText(item.getSender());
        }

        holder.msg_time.setVisibility(View.GONE);

       /* if (messages.get(position).getEmail().equals(AllMethods.name)){
            holder.username.setText("You :"+messages.get(position).getMessage());

            Glide.with(context).load(messages.get(position).getImageURL()).into(holder.profile_image);
        }else {
            holder.username.setText(messages.get(position).getEmail() +":"+ messages.get(position).getMessage());
            Glide.with(context).load(messages.get(position).getImageURL()).into(holder.profile_image);
        }
        Log.d("","datadatada"+messages.get(position).getImageURL());*/

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username, user_msg, msg_time;
        ImageView profile_image;
        CardView card_view;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            msg_time=itemView.findViewById(R.id.msg_time);
            user_msg=itemView.findViewById(R.id.user_msg);
            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).getSender().equals(currentuser)){
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }

    }
}
