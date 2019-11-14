package com.velasquez.asistentevirtualucv.Views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gson.JsonElement;
import com.velasquez.asistentevirtualucv.Adapters.ChatAdapter;
import com.velasquez.asistentevirtualucv.Models.Chat;
import com.velasquez.asistentevirtualucv.R;

import java.util.Map;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;

public class MainActivity extends AppCompatActivity  implements AIListener, View.OnClickListener{
    private ImageButton btn_voice;
    private AIService aiService;
    private TextToSpeech textToSpeech;
    private RecyclerView recicler_chat;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        btn_voice = findViewById(R.id.btn_Escvuchar);
        recicler_chat = findViewById(R.id.recicler_chat);
        chatAdapter = new ChatAdapter();
        LinearLayoutManager linearLayoutManagerEstado = new LinearLayoutManager(this);
        recicler_chat.setLayoutManager(linearLayoutManagerEstado);
        recicler_chat.setAdapter(chatAdapter);



        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED) {

            makeRequest();
        }

        final AIConfiguration configuration = new AIConfiguration(
                "a2e2b8275de24cc7a387718edb136748",
                AIConfiguration.SupportedLanguages.Spanish,
                AIConfiguration.RecognitionEngine.System);
        aiService = AIService.getService(this, configuration);
        aiService.setListener(this);
        btn_voice.setOnClickListener(this);
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });

        Intent intent=new Intent(this,AgrearDocenteActivity.class);
        startActivity(intent);
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                101);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResult(AIResponse response) {
        Result result = response.getResult();

        Result result1 = response.getResult();

        //get parameters
        String parameterString = "";
        if (result1.getParameters() != null && !result1.getParameters().isEmpty()) {
            for (final Map.Entry<String, JsonElement> entry : result1.getParameters().entrySet()) {
                parameterString += "(" + entry.getKey() + ", " + entry.getValue();
            }
        }


        Chat chatUser = new Chat(result1.getResolvedQuery(), Chat.TYPE_USER);
        chatAdapter.agregar(chatUser);

        Chat chatBoth = new Chat(result.getFulfillment().getSpeech(), Chat.TYPE_BOTH);
        chatAdapter.agregar(chatBoth);




        //       txt_mensaje.setText("Query: " + result1.getResolvedQuery() +
        //         "\nAction : " + result1.getAction() +
        //          "\nParamenters " + parameterString + "\nContexto  " + result1.getContexts());


        // if(result.getAction().equals("Descripción")){
        //     String peliducasd=result.getParameters().get("film").toString();
//
        //     txt_mensaje.setText(peliducasd);

        //   }

        textToSpeech.speak(result.getFulfillment().getSpeech(), TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onAudioLevel(float level) {

    }

    @Override
    public void onListeningStarted() {

    }

    @Override
    public void onListeningCanceled() {

    }

    @Override
    public void onListeningFinished() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Escvuchar:
                aiService.startListening();
                break;
        }
    }
}
