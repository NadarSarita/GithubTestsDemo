package com.app.idnbin.Chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.app.idnbin.LoginRegister.UserDetails;
import com.app.idnbin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GroupChatActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    MessageAdapter messageAdapter;
    UserDetails user;
    ArrayList<Message> messages = new ArrayList<>();
    RecyclerView recyclerView;
    ImageButton btn_send;
    EditText text_send;
    FirebaseUser currentuser;
    Toolbar Tb_App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        recyclerView = findViewById(R.id.message_view);
        recyclerView.setHasFixedSize(true);

        Tb_App = findViewById(R.id.Tb_App);
        setSupportActionBar(Tb_App);

        btn_send = findViewById(R.id.btn_send);
        Intent i = getIntent();
        String chat = i.getStringExtra("Chat");

        setSupportActionBar(Tb_App);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (chat.equalsIgnoreCase("Digital") || chat.equalsIgnoreCase("Forex"))
            Tb_App.setTitle(chat.concat(" Chat"));
        else
            Tb_App.setTitle(chat);

        getMessage(chat);
    }

    private void getMessage(String chat){

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = new UserDetails();

        btn_send.setOnClickListener(this);

        text_send = findViewById(R.id.text_send);


        currentuser = firebaseAuth.getCurrentUser();
        user.setId(currentuser.getUid());
        user.setGmail(currentuser.getEmail());


        firebaseDatabase.getReference("Userdetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(UserDetails.class);
                if (user != null) {
                    user.setId(currentuser.getUid());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        reference = firebaseDatabase.getReference(chat);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                messages.add(message);
                recyclerView.setLayoutManager(new LinearLayoutManager(GroupChatActivity.this));
                messageAdapter = new MessageAdapter(GroupChatActivity.this,messages,reference);
                recyclerView.setAdapter(messageAdapter);
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Message message = dataSnapshot.getValue(Message.class);
                message.setKey(dataSnapshot.getKey());
                recyclerView.setLayoutManager(new LinearLayoutManager(GroupChatActivity.this));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                messageAdapter = new MessageAdapter(GroupChatActivity.this,messages,reference);
                recyclerView.setAdapter(messageAdapter);
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String str_time = dateFormat.format(new Date());

        if (!TextUtils.isEmpty(text_send.getText().toString())){
            Message message = new Message(text_send.getText().toString(),currentuser.getEmail(),"",str_time);
            text_send.setText("");
            reference.child("").push().setValue(message);
        }else {
            Toast.makeText(this, " didn't empty send message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
