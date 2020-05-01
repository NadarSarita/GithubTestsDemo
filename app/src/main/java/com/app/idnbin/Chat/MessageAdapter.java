package com.app.idnbin.Chat;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.idnbin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    Context context;
    List<Message> messages;
    DatabaseReference messagedb;
    FirebaseUser fuser;

    public MessageAdapter(Context context, List<Message> messages, DatabaseReference messagedb) {
        this.context = context;
        this.messagedb = messagedb;
        this.messages = messages;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.user_msg.setText(messages.get(position).getMessage());
        holder.user_msg.setGravity(Gravity.START);

        holder.username.setText(messages.get(position).getEmail());
        holder.msg_time.setText(messages.get(position).getTime());
        Log.d("","ADDAadadaad"+messages.get(position).getEmail());

        String[] email = messages.get(position).getEmail().split("@");
        holder.username.setText(String.valueOf(email[0]));
        holder.profile_image.setVisibility(View.GONE);
//        holder.msg_time.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user_msg, username, msg_time;
        ImageView profile_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_msg = itemView.findViewById(R.id.user_msg);
            username = itemView.findViewById(R.id.username);
            msg_time = itemView.findViewById(R.id.msg_time);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }

    @Override
    public int getItemViewType(int position) {

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        Log.d("VALLLA",""+messages.get(position).getEmail());
        if (messages.get(position).getEmail().equals(fuser.getEmail())) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}
