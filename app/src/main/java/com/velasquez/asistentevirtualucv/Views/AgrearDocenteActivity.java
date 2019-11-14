package com.velasquez.asistentevirtualucv.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_Presenter;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_View;
import com.velasquez.asistentevirtualucv.Models.Docente;
import com.velasquez.asistentevirtualucv.Presenters.Docente_Presenter;
import com.velasquez.asistentevirtualucv.R;
import com.velasquez.asistentevirtualucv.Utils.Mensajes;
import com.velasquez.asistentevirtualucv.Utils.Verificador;

import es.dmoral.toasty.Toasty;





public class AgrearDocenteActivity extends AppCompatActivity implements IDocente_View, View.OnClickListener {
    private TextInputLayout txt_Nombre, txt_apellidos, txt_dni;
    private LinearLayout btn_Guardar;
    private Docente docente;
    private Button btn_Error;
    private IDocente_Presenter iDocente_presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrear_docente);
        getSupportActionBar().hide();
        obtenerDatosxml();
    }

    @Override
    public void obtenerDatosxml() {
        txt_Nombre = findViewById(R.id.txt_nombre);
        txt_apellidos = findViewById(R.id.txt_apellidos);
        txt_dni = findViewById(R.id.txt_dni);
        btn_Guardar = findViewById(R.id.btn_Siguiente);
        btn_Error = findViewById(R.id.btn_Error);

        iDocente_presenter = new Docente_Presenter(this);

        btn_Guardar.setOnClickListener(this);
    }


    @Override
    public void mostrarErrorRed() {

    }

    @Override
    public void habiltarBoton() {
        btn_Guardar.setEnabled(true);
    }

    @Override
    public void inabilitarBoton() {
        btn_Guardar.setEnabled(false);

    }


    @Override
    public void llenarDatos() {
        txt_apellidos.getEditText().setText(docente.getApellidos());
        txt_Nombre.getEditText().setText(docente.getNombre());
        txt_dni.getEditText().setText(docente.getDni());
    }

    @Override
    public void guardarDatos() {
        if (verificarDatos()) {
            iDocente_presenter.agregar((Docente) obtenerDatos());
        }
    }

    @Override
    public Object obtenerDatos() {
        Docente docente = new Docente();
        docente.setApellidos(txt_apellidos.getEditText().getText().toString());
        docente.setDni(txt_dni.getEditText().getText().toString());
        docente.setNombre(txt_Nombre.getEditText().getText().toString());
        return docente;
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        Toasty.success(this, mensaje, Toast.LENGTH_SHORT, true).show();

    }





    @Override
    public boolean verificarDatos() {
        int contador = 0, suma = 0;
        contador = Verificador.veriricarTextoVacio(txt_dni);
        suma = contador + suma;
        contador = Verificador.veriricarTextoVacio(txt_Nombre);
        suma = contador + suma;
        contador = Verificador.veriricarTextoVacio(txt_apellidos);
        suma = contador + suma;
        return suma == 3;
    }

    @Override
    public void operacionIncorrecta(String mensae) {
        Toasty.error(this, mensae, Toast.LENGTH_SHORT, true).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        unregisterReceiver(networkStateReceiver);
        super.onPause();
    }


    //verificar red
    private BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = manager.getActiveNetworkInfo();
            onNetworkChange(ni);
        }
    };

    private void onNetworkChange(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                btn_Error.setVisibility(View.GONE);
                btn_Guardar.setEnabled(true);

            } else {
                mostrarErrorRed();
            }
        } else {
            mostrarErrorRed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Siguiente:
                guardarDatos();
                break;
        }
    }
}
