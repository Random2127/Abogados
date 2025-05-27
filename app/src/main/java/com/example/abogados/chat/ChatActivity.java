package com.example.abogados.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abogados.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {


    private RecyclerView chat;
    private EditText sendText;
    private Button send;

    private final List<ChatMessage> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chat = findViewById(R.id.recyclerView);
        sendText = findViewById(R.id.sendText);
        send = findViewById(R.id.sendButton);

        ChatAdapter chatAdapter = new ChatAdapter(messageList);
        chat.setLayoutManager(new LinearLayoutManager(this));
        chat.setAdapter(chatAdapter);

        DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("chats");

        // Identificamos usuario
        SharedPreferences preferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String senderId = preferences.getString("nombre", "Anonymous");

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                ChatMessage msg = snapshot.getValue(ChatMessage.class);
                if (msg != null) {
                    messageList.add(msg);
                    chatAdapter.notifyItemInserted(messageList.size() - 1);
                    chat.scrollToPosition(messageList.size() - 1);
                }
            }

            @Override public void onChildChanged(DataSnapshot s, String p) {}
            @Override public void onChildRemoved(DataSnapshot s) {}
            @Override public void onChildMoved(DataSnapshot s, String p) {}
            @Override public void onCancelled(DatabaseError e) {}
        });

        send.setOnClickListener(v -> {
            String text = sendText.getText().toString().trim();
            if (!text.isEmpty()) {
                ChatMessage msg = new ChatMessage(senderId, text, System.currentTimeMillis());
                chatRef.push().setValue(msg);
                sendText.setText("");
            }
        });

    }
}