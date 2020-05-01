package com.app.idnbin.Chat;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import com.app.idnbin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    ArrayList<String> name;
    int[] image;
    Intent i;
    Context context;
    String theLastMessage;
    private boolean ischat;

  public ChatAdapter(Context context, ArrayList<String> name, int[] image, boolean ischat){
      this.name = name;
        this.image = image;
       this.context = context;
       this.ischat = ischat;
  }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_groupchat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.IMG_ChatLogo.setImageResource(image[position]);
        holder.TV_chat.setText(name.get(position));

        holder.RLchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.get(position).contentEquals("Stocks Chat")) {
                    i = new Intent(context, GroupChatActivity.class);
                    i.putExtra("Chat", "Stocks Chat");
                    context.startActivity(i);
                }else if (name.get(position).contentEquals("Digital Chat")){
                    i = new Intent(context,GroupChatActivity.class);
                    i.putExtra("Chat","Digital Chat");
                    context.startActivity(i);
                }else if (name.get(position).contentEquals("ETF Chat")){
                    i = new Intent(context,GroupChatActivity.class);
                    i.putExtra("Chat","ETF Chat");
                    context.startActivity(i);
                }else if (name.get(position).contentEquals("Commodity Chat")){
                    i = new Intent(context,GroupChatActivity.class);
                    i.putExtra("Chat","Commodity Chat");
                    context.startActivity(i);
                }else if (name.get(position).contentEquals("Crypto Chat")){
                    i = new Intent(context,GroupChatActivity.class);
                    i.putExtra("Chat","Crypto Chat");
                    context.startActivity(i);
                }else if (name.get(position).contentEquals("Forex Chat")){
                    i = new Intent(context,GroupChatActivity.class);
                    i.putExtra("Chat","Forex Chat");
                    context.startActivity(i);
                }
            }
        });

        if (ischat){
            lastseen(name.get(position), holder.TV_lastseen,holder.TV_lasttime);
        } else {
            holder.TV_lastseen.setVisibility(View.GONE);
        }



    }

    @Override
    public int getItemCount() {

        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      ImageView IMG_ChatLogo;
      RelativeLayout RLchat;
        TextView TV_chat,TV_lastseen,TV_lasttime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            IMG_ChatLogo =  itemView.findViewById(R.id.IMG_ChatLogo);
            TV_chat =  itemView.findViewById(R.id.TV_chat);
            RLchat = itemView.findViewById(R.id.RLchat);
            TV_lastseen = itemView.findViewById(R.id.TV_lastseen);
            TV_lasttime = itemView.findViewById(R.id.TV_lasttime);
        }
    }
    private void lastseen(String userid, final TextView TV_lastseen,TextView TV_lasttime){
        theLastMessage = "default";
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(userid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Message message = snapshot.getValue(Message.class);
                    Log.d("","messssASASSA"+Message.class);
                        theLastMessage = message.getMessage();
                    Log.d("","DATDdddddddddddddddddd"+message.getMessage());
                    /*DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                    String str_time = dateFormat.format(new Date());
                    TV_lasttime.setText(str_time);*/
                }

                switch (theLastMessage){
                    case  "default":
                        TV_lastseen.setText("no message");
                        break;

                    default:
                        TV_lastseen.setText(theLastMessage);
                        break;
                }

                theLastMessage = "default";

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}