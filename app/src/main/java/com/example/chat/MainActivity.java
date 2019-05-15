package com.example.chat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static int MAX_MESSAGE_LENGTH = 150;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("messages");

    EditText mEditTextMessage;
    Button mSendButton;
    ArrayList<String> messages = new ArrayList<>();
    RecyclerView mMessagesRecycler;

    DataAdapter dataAdapter = new DataAdapter(this, messages);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendButton = findViewById(R.id.send_message_b);
        mEditTextMessage = findViewById(R.id.message_input);
        mMessagesRecycler = findViewById(R.id.message_recycler);

        mMessagesRecycler.setLayoutManager(new LinearLayoutManager(this));
        mMessagesRecycler.setAdapter(dataAdapter);

        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msg = mEditTextMessage.getText().toString();
                if(msg.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter your message...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(msg.length()== MAX_MESSAGE_LENGTH){
                    Toast.makeText(getApplicationContext(),"Character limit is high", Toast.LENGTH_SHORT).show();
                    return;
                }

                myRef.push().setValue(msg);
                mEditTextMessage.setText("");

            }
        });
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String msg = dataSnapshot.getValue(String.class);
                messages.add(msg);
                dataAdapter.notifyDataSetChanged();
                mMessagesRecycler.smoothScrollToPosition(messages.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}

