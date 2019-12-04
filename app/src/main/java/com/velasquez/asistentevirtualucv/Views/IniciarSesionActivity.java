package com.velasquez.asistentevirtualucv.Views;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.velasquez.asistentevirtualucv.Models.Clases.Docente;
import com.velasquez.asistentevirtualucv.Models.Interfaces.IInicioSesion;
import com.velasquez.asistentevirtualucv.Presenters.IniciarSesion_Presentor;
import com.velasquez.asistentevirtualucv.R;
import com.velasquez.asistentevirtualucv.Utils.Verificador;

import es.dmoral.toasty.Toasty;


public class IniciarSesionActivity extends AppCompatActivity implements View.OnClickListener, IInicioSesion.IIniciarSesion_View {

    private LinearLayout btn_crearCuenta, btn_IniciarSesion;
    private TextInputLayout txt_correoElectronico, txt_contraseña;
    private IInicioSesion.IIniciarSesion_Presentor iIniciarSesion_presentor;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        getSupportActionBar().hide();
        obtenerDatosxml();
    }

    @Override
    protected void onStart() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.isEmailVerified()) {
                Intent intent = new Intent(IniciarSesionActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                txt_correoElectronico.setError("Falta verificar su cuenta");
            }

        }
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_crearCuenta:
                Intent intent = new Intent(this, CrearCuentaActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_IniciarSesion:
                guardarDatos();
                break;
        }
    }

    @Override
    public void obtenerDatosxml() {
        txt_correoElectronico = findViewById(R.id.txt_correoElectronico);
        txt_contraseña = findViewById(R.id.txt_contraseña);
        btn_crearCuenta = findViewById(R.id.btn_crearCuenta);
        btn_IniciarSesion = findViewById(R.id.btn_IniciarSesion);
        iIniciarSesion_presentor = new IniciarSesion_Presentor(this);
        mAuth = FirebaseAuth.getInstance();
        btn_crearCuenta.setOnClickListener(this);
        btn_IniciarSesion.setOnClickListener(this);
    }

    @Override
    public void mostrarErrorRed() {

    }

    @Override
    public void habiltarBoton() {
        btn_IniciarSesion.setEnabled(true);
        btn_crearCuenta.setEnabled(true);
    }

    @Override
    public void inabilitarBoton() {
        btn_IniciarSesion.setEnabled(false);
        btn_crearCuenta.setEnabled(false);
    }

    @Override
    public void llenarDatos() {

    }

    @Override
    public void guardarDatos() {
        if (verificarDatos()) {
            iIniciarSesion_presentor.iniciarSesion((Docente) obtenerDatos());
        }

    }

    @Override
    public Object obtenerDatos() {
        Docente docente = new Docente();
        docente.setCorreoElectronico(txt_correoElectronico.getEditText().getText().toString());
        docente.setContraseña(txt_contraseña.getEditText().getText().toString());
        return docente;
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
    public void operacionCorrecta(String mensaje) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void operacionIncorrecta(String mensaje) {
        Toasty.error(this, mensaje, Toasty.LENGTH_LONG).show();
    }
}
