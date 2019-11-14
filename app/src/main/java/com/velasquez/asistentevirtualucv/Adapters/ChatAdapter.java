package com.velasquez.asistentevirtualucv.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velasquez.asistentevirtualucv.Models.Chat;
import com.velasquez.asistentevirtualucv.R;

import java.util.ArrayList;
import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    private List<Chat> listaChat = new ArrayList<>();

    public ChatAdapter() {
    }


    public void agregar(Chat chat) {
        listaChat.add(chat);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat, parent, false);
        return new ChatHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ChatHolder h, int i) {
        Chat chat = listaChat.get(i);

        if (chat.getTipo().equals(Chat.TYPE_BOTH)) {
            h.LineaLayout_chatUser.setVisibility(View.GONE);
            h.LineaLayout_chatBoth.setVisibility(View.VISIBLE);
            h.lbl_chatBoth.setText(chat.getDescripcion());
        } else if (chat.getTipo().equals(Chat.TYPE_USER)) {
            h.LineaLayout_chatUser.setVisibility(View.VISIBLE);
            h.LineaLayout_chatBoth.setVisibility(View.GONE);
            h.lbl_chatUser.setText(chat.getDescripcion());
        }


    }


    @Override
    public int getItemCount() {
        return listaChat.size();
    }


    public class ChatHolder extends RecyclerView.ViewHolder {
        private TextView lbl_chatBoth, lbl_chatUser;
        private LinearLayout LineaLayout_chatUser, LineaLayout_chatBoth;


        public ChatHolder(@NonNull View itemView) {
            super(itemView);

            LineaLayout_chatBoth = itemView.findViewById(R.id.LineaLayout_chatBoth);
            LineaLayout_chatUser = itemView.findViewById(R.id.LineaLayout_chatUser);

            lbl_chatBoth = itemView.findViewById(R.id.lbl_chatBoth);
            lbl_chatUser = itemView.findViewById(R.id.lbl_chatUser);
        }
    }
}
