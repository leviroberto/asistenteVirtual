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
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.JsonElement;
import com.velasquez.asistentevirtualucv.Adapters.ChatAdapter;
import com.velasquez.asistentevirtualucv.Models.Clases.Chat;
import com.velasquez.asistentevirtualucv.Models.Clases.Curso;
import com.velasquez.asistentevirtualucv.Models.Clases.Tarea;
import com.velasquez.asistentevirtualucv.Models.Interfaces.IMain;
import com.velasquez.asistentevirtualucv.Presenters.IMain_Presentor;
import com.velasquez.asistentevirtualucv.R;
import com.velasquez.asistentevirtualucv.Utils.DescompilarInformacionIA;
import com.velasquez.asistentevirtualucv.Utils.Verificador;

import java.util.List;
import java.util.Map;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import es.dmoral.toasty.Toasty;


public class MainActivity extends AppCompatActivity implements IMain.IMain_View, AIListener, View.OnClickListener {
    private ImageButton btn_Escvuchar, btn_menu;
    private AIService aiService;
    private TextToSpeech textToSpeech;
    private RecyclerView recicler_chat;
    private ChatAdapter chatAdapter;
    private DescompilarInformacionIA descompilarInformacionIA;
    private IMain.IMain_Presentor iMain_presentor;
    private Curso cursoSeleccionado = null;
    private Tarea tareaSeleccionada = null;
    private ImageButton btnSalir;
    private LottieAnimationView animation_view;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        obtenerDatosxml();
        inicializadores();
    }


    @Override
    public void inicializadores() {
        chatAdapter = new ChatAdapter(this);
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

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        chatAdapter.eliminarCurso();
        iMain_presentor.obtenerCursos();
    }

    @Override
    public void obtenerCursosCorrecto(List<Curso> listaCurso) {
        chatAdapter.setListaCurso(listaCurso);
    }

    @Override
    public void mostrarCursosChat() {
        chatAdapter.mostrarCursosChat();
    }

    @Override
    public void crearTarea(Tarea tarea) {
        this.tareaSeleccionada = tarea;
        cursoSeleccionado = chatAdapter.buscarCurso(tarea.getCurso().getNombre());
        if (cursoSeleccionado == null) {

        } else {
            tareaSeleccionada.setCurso(cursoSeleccionado);
            iMain_presentor.crearTarea(tareaSeleccionada);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void responder(String descripcion) {
        textToSpeech.speak(descripcion, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    @Override
    public void cursoSeleccionado(String descripcion) {
        cursoSeleccionado = chatAdapter.buscarCurso(descripcion);
        if (tareaSeleccionada != null) {
            tareaSeleccionada.setCurso(cursoSeleccionado);
            iMain_presentor.crearTarea(tareaSeleccionada);
        }
    }

    @Override
    public void buscarTareaPorFecha(String date) {
        iMain_presentor.buscarTareaPorFecha(date);
    }

    @Override
    public void buscarTareaPorFechaCorrecto(List<Tarea> listTarea) {
        chatAdapter.buscarTareaPorFechaCorrecto(listTarea);
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECORD_AUDIO},
                101);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResult(AIResponse response) {
        btn_Escvuchar.setVisibility(View.VISIBLE);
        animation_view.setVisibility(View.GONE);
        Result result = response.getResult();
        //get parameters
        String parameterString = "";
        if (result.getParameters() != null && !result.getParameters().isEmpty()) {
            for (final Map.Entry<String, JsonElement> entry : result.getParameters().entrySet()) {
                parameterString += "(" + entry.getKey() + ", " + entry.getValue();
            }
        }

        Chat chatUser = new Chat(result.getResolvedQuery(), Chat.TYPE_USER_USER, false, Chat.TYPE_DEFECTO);
        chatAdapter.agregar(chatUser);

        Chat chatBoth = new Chat(result.getFulfillment().getSpeech(), Chat.TYPE_USER_BOTH, false, Chat.TYPE_DEFECTO);
        chatAdapter.agregar(chatBoth);

        descompilarInformacionIA.verificarAccion(result);


        //       txt_mensaje.setText("Query: " + result1.getResolvedQuery() +
        //         "\nAction : " + result1.getAction() +
        //          "\nParamenters " + parameterString + "\nContexto  " + result1.getContexts());


        // if(result.getAction().equals("Descripción")){
        //     String peliducasd=result.getParameters().get("film").toString();
//
        //     txt_mensaje.setText(peliducasd);

        //   }

        responder(result.getFulfillment().getSpeech().toString());
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
                btn_Escvuchar.setVisibility(View.GONE);
                animation_view.setVisibility(View.VISIBLE);
                aiService.startListening();
                break;
            case R.id.btn_menu:
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                break;
            case R.id.animation_view:
                btn_Escvuchar.setVisibility(View.VISIBLE);
                animation_view.setVisibility(View.GONE);
                aiService.stopListening();
                break;
            case R.id.btnSalir:
                mAuth.signOut();
                Intent intents = new Intent(this, IniciarSesionActivity.class);
                intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intents);
                break;
        }
    }


    @Override
    public void obtenerDatosxml() {
        btn_Escvuchar = findViewById(R.id.btn_Escvuchar);
        recicler_chat = findViewById(R.id.recicler_chat);
        btn_menu = findViewById(R.id.btn_menu);
        animation_view = findViewById(R.id.animation_view);
        iMain_presentor = new IMain_Presentor(this);
        descompilarInformacionIA = new DescompilarInformacionIA(this);
        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(this);
        btn_Escvuchar.setOnClickListener(this);
        btn_menu.setOnClickListener(this);
        animation_view.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void mostrarErrorRed() {
        Toasty.error(this, "Error de red", Toasty.LENGTH_LONG).show();
    }

    @Override
    public void habiltarBoton() {

    }

    @Override
    public void inabilitarBoton() {

    }

    @Override
    public void llenarDatos() {

    }

    @Override
    public void guardarDatos() {

    }

    @Override
    public Object obtenerDatos() {
        return null;
    }

    @Override
    public boolean verificarDatos() {
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void operacionCorrecta(String mensaje) {
        String formatoTexto = "Vale tu tarea te recordare el " +
                Verificador.formarFechaHumano(tareaSeleccionada.getFecha()) + " a las " + Verificador.getHora(tareaSeleccionada.getHora());
        Chat chat = new Chat(formatoTexto, Chat.TYPE_USER_BOTH, false, Chat.TYPE_DEFECTO);
        chatAdapter.agregar(chat);
        responder(formatoTexto);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void operacionIncorrecta(String mensaje) {
        Chat chat = new Chat(mensaje, Chat.TYPE_USER_USER, false, Chat.TYPE_ERROR);
        chatAdapter.agregar(chat);
        responder(mensaje);
    }
}
