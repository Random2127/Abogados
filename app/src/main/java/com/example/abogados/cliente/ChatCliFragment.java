package com.example.abogados.cliente;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.abogados.ChatAdapter;
import com.example.abogados.ChatMessage;
import com.example.abogados.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatCliFragment extends Fragment {

    private RecyclerView chat;
    private EditText sendText;
    private Button send;
    private final List<ChatMessage> messageList = new ArrayList<>();

    public ChatCliFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cli_chat, container, false);

        chat = view.findViewById(R.id.recyclerView);
        sendText = view.findViewById(R.id.sendText);
        send = view.findViewById(R.id.sendButton);

        ChatAdapter chatAdapter = new ChatAdapter(messageList);
        chat.setLayoutManager(new LinearLayoutManager(requireContext()));
        chat.setAdapter(chatAdapter);

        DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("chats");

        SharedPreferences preferences = requireContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String senderId = preferences.getString("nombre", "Anonymous");

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ChatMessage msg = snapshot.getValue(ChatMessage.class);
                if (msg != null) {
                    messageList.add(msg);
                    chatAdapter.notifyItemInserted(messageList.size() - 1);
                    chat.scrollToPosition(messageList.size() - 1);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        send.setOnClickListener(v -> {
            String text = sendText.getText().toString().trim();
            if (!text.isEmpty()) {
                ChatMessage msg = new ChatMessage(senderId, text, System.currentTimeMillis());
                chatRef.push().setValue(msg);
                sendText.setText("");
            }
        });




        return view;
    }

}