package com.velasquez.asistentevirtualucv.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.velasquez.asistentevirtualucv.Models.Clases.Docente;
import com.velasquez.asistentevirtualucv.Models.Interfaces.ICrearCuenta;
import com.velasquez.asistentevirtualucv.Presenters.CrearCuenta_Presentor;
import com.velasquez.asistentevirtualucv.R;
import com.velasquez.asistentevirtualucv.Utils.Verificador;

import es.dmoral.toasty.Toasty;





public class CrearCuentaActivity extends AppCompatActivity implements View.OnClickListener, ICrearCuenta.ICrearCuenta_View {

    private TextInputLayout txt_correoElectronico, txt_contraseña;
    private LinearLayout btn_crearCuenta;
    private ICrearCuenta.ICrearCuenta_Presentor iCrearCuenta_presentor;
    private Docente docente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        getSupportActionBar().hide();
        obtenerDatosxml();
        docente = new Docente();
    }

    @Override
    public void obtenerDatosxml() {
        txt_correoElectronico = findViewById(R.id.txt_correoElectronico);
        txt_contraseña = findViewById(R.id.txt_contraseña);
        btn_crearCuenta = findViewById(R.id.btn_crearCuenta);
        iCrearCuenta_presentor = new CrearCuenta_Presentor(this);
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
        Toasty.error(this, "Error de red", Toasty.LENGTH_LONG).show();
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
            iCrearCuenta_presentor.VerificarEmail(txt_correoElectronico.getEditText().getText().toString());
        }
    }

    @Override
    public Object obtenerDatos() {
        docente.setCorreoElectronico(txt_correoElectronico.getEditText().getText().toString());
        docente.setContraseña(txt_contraseña.getEditText().getText().toString());
        return docente;
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        obtenerDatos();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Docente", docente);
        Intent intent = new Intent(this, CrearCuenta1Activity.class);
        intent.putExtras(bundle);
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
