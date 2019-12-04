package com.velasquez.asistentevirtualucv.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;


import com.velasquez.asistentevirtualucv.Models.Interfaces.IError_View;
import com.velasquez.asistentevirtualucv.R;




public class MenuActivity extends AppCompatActivity implements IError_View, View.OnClickListener {

    private LinearLayout btn_agregarCurso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        obtenerDatosxml();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_agregarCurso:
                Intent intent = new Intent(this, AgregarCursoActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void obtenerDatosxml() {
        btn_agregarCurso = findViewById(R.id.btn_agregarCurso);
        btn_agregarCurso.setOnClickListener(this);
    }

    @Override
    public void mostrarErrorRed() {

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

    @Override
    public void operacionCorrecta(String mensaje) {

    }

    @Override
    public void operacionIncorrecta(String mensaje) {

    }
}
