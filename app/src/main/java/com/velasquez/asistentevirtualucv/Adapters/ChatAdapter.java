package com.velasquez.asistentevirtualucv.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.velasquez.asistentevirtualucv.Models.Clases.Chat;
import com.velasquez.asistentevirtualucv.Models.Clases.Curso;
import com.velasquez.asistentevirtualucv.Models.Clases.Tarea;
import com.velasquez.asistentevirtualucv.Models.Interfaces.IMain;
import com.velasquez.asistentevirtualucv.R;
import com.velasquez.asistentevirtualucv.Utils.Verificador;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatHolder> {
    private List<Chat> listaChat = new ArrayList<>();
    private List<Curso> listaCurso = new ArrayList<>();
    private IMain.IMain_View iMain_view;

    public ChatAdapter(IMain.IMain_View iMain_view) {
        this.iMain_view = iMain_view;
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

        if (chat.getTipo_user().equals(Chat.TYPE_USER_BOTH)) {
            h.LineaLayout_chatUser.setVisibility(View.GONE);
            h.LineaLayout_chatBoth.setVisibility(View.VISIBLE);
            h.lbl_chatBoth.setText(chat.getDescripcion());
        } else if (chat.getTipo_user().equals(Chat.TYPE_USER_USER)) {
            h.LineaLayout_chatUser.setVisibility(View.VISIBLE);
            h.LineaLayout_chatBoth.setVisibility(View.GONE);
            h.lbl_chatUser.setText(chat.getDescripcion());
            if (chat.isClickeable()) {
                h.LineaLayout_chatUser.setOnClickListener(new eventoChat(chat));
            }
        }


    }


    public void setListaCurso(List<Curso> listaCurso) {
        for (Curso curso : listaCurso) {
            this.listaCurso.add(curso);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaChat.size();
    }

    public void eliminarCurso() {
        listaCurso.removeAll(listaCurso);
    }

    public void mostrarCursosChat() {
        Chat chat = null;
        chat = new Chat("Elegir cursos ", Chat.TYPE_USER_USER, false, Chat.TYPE_DEFECTO);
        listaChat.add(chat);
        for (Curso curso : listaCurso) {
            chat = new Chat(curso.getNombre(), Chat.TYPE_USER_USER, true, Chat.TYPE_CURSO);
            listaChat.add(chat);
        }
    }

    public Curso verificarSiExisteCurso(String texto) {
        Curso curso = Verificador.obtenerPorcentajeCoincidencia(texto, listaCurso);
        if (curso == null) {
            Chat chat = new Chat("Curso incorrecto ", Chat.TYPE_USER_USER, false, Chat.TYPE_DEFECTO);
            iMain_view.responder(chat.getDescripcion());
            this.notifyDataSetChanged();
        }
        return curso;

    }

    public Curso buscarCurso(String descripcion) {
        descripcion = normalizar(descripcion);
        Curso curso = null;
        for (Curso curso1 : listaCurso) {
            String comparar = normalizar(curso1.getNombre());
            boolean estado = comparar.equals(descripcion);
            if (estado) {
                curso = curso1;
            }

        }
        return curso;
    }

    private String normalizar(String text) {

        String normalizarCadena = Normalizer.normalize(text, Normalizer.Form.NFD);
        String sintildeCadena = normalizarCadena.replaceAll("[^\\p{ASCII}]", "");
        sintildeCadena = sintildeCadena.toLowerCase().toString();
        return sintildeCadena;
    }

    public void buscarTareaPorFechaCorrecto(List<Tarea> listTarea) {
        Chat chat = null;
        chat = new Chat("Tareas encontrada ", Chat.TYPE_USER_USER, false, Chat.TYPE_DEFECTO);
        listaChat.add(chat);
        iMain_view.responder("Vale estas son las tareas que encontre ");
        for (Tarea tarea : listTarea) {
            chat = new Chat(tarea.getTitulo(), Chat.TYPE_USER_USER, false, Chat.TYPE_DEFECTO);
            listaChat.add(chat);
        }
        this.notifyDataSetChanged();
    }

    class eventoChat implements View.OnClickListener {
        private Chat chat;

        public eventoChat(Chat chat) {
            this.chat = chat;

        }

        @Override
        public void onClick(View v) {
            if (chat.getTipe().equals(Chat.TYPE_CURSO)) {
                iMain_view.cursoSeleccionado(chat.getDescripcion());
            }

        }
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
