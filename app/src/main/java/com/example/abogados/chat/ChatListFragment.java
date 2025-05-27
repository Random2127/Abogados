package com.example.abogados.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abogados.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatListFragment extends Fragment {

    protected RecyclerView listaConversaciones;

    public ChatListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);

        listaConversaciones = view.findViewById(R.id.recyclerViewAdm);

        SharedPreferences preferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String userId = preferences.getString("nombre","anonymous");

        DatabaseReference chatListRef = FirebaseDatabase.getInstance().getReference("chatList").child(userId);


        return view;
    }

}