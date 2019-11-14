package com.velasquez.asistentevirtualucv.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.velasquez.asistentevirtualucv.Interfaces.ICrearCuenta_Presentor;
import com.velasquez.asistentevirtualucv.Interfaces.ICrearCuenta_View;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_Presenter;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_View;
import com.velasquez.asistentevirtualucv.Models.Docente;
import com.velasquez.asistentevirtualucv.Presenters.CrearCuenta_Presenter;
import com.velasquez.asistentevirtualucv.Presenters.Docente_Presenter;
import com.velasquez.asistentevirtualucv.R;
import com.velasquez.asistentevirtualucv.Utils.Mensajes;
import com.velasquez.asistentevirtualucv.Utils.Verificador;

import es.dmoral.toasty.Toasty;


public class CrearCuentaActivity extends AppCompatActivity implements ICrearCuenta_View, View.OnClickListener {

    private TextInputLayout txt_correoElectronico, txt_contraseña;
    private LinearLayout btn_crearCuenta;
    private ICrearCuenta_Presentor iCrearCuenta_presentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        getSupportActionBar().hide();
        obtenerDatosxml();
    }

    @Override
    public void obtenerDatosxml() {
        txt_correoElectronico = findViewById(R.id.txt_correoElectronico);
        txt_contraseña = findViewById(R.id.txt_contraseña);
        btn_crearCuenta = findViewById(R.id.btn_crearCuenta);
        iCrearCuenta_presentor = new CrearCuenta_Presenter(this);
        btn_crearCuenta.setOnClickListener(this);
    }

    @Override
    public boolean verificarDatos() {

        int contador = 0, suma = 0;
        contador = Verificador.veriricarCorreoElectronico(txt_correoElectronico, "Correo electronico invalido");
        suma = contador + suma;
        contador = Verificador.veriricarTextoVacio(txt_contraseña, "Contraseña incorrecta");
        suma = contador + suma;

        return suma == 2;
    }


    @Override
    public void mostrarErrorRed() {

    }

    @Override
    public void habiltarBoton() {
        btn_crearCuenta.setEnabled(true);
    }

    @Override
    public void inabilitarBoton() {
        btn_crearCuenta.setEnabled(false);

    }


    @Override
    public void llenarDatos() {

    }

    @Override
    public void guardarDatos() {
        if (verificarDatos()) {
            String email = txt_correoElectronico.getEditText().getText().toString();
            String password = txt_contraseña.getEditText().getText().toString();
            iCrearCuenta_presentor.crearCuenta(email, password);
        }
    }

    @Override
    public Object obtenerDatos() {
        return null;
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        Intent intent = new Intent(this, CrearCuenta1Activity.class);
        startActivity(intent);
    }

    @Override
    public void operacionIncorrecta(String mensaje) {
        Toasty.warning(this, mensaje, Toast.LENGTH_SHORT, true).show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_crearCuenta:
                guardarDatos();
                break;
        }
    }
}
