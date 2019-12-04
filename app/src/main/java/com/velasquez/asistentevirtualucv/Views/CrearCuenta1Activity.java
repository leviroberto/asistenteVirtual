package com.velasquez.asistentevirtualucv.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.velasquez.asistentevirtualucv.Models.Clases.Docente;
import com.velasquez.asistentevirtualucv.Models.Interfaces.ICrearCuenta;
import com.velasquez.asistentevirtualucv.R;
import com.velasquez.asistentevirtualucv.Utils.Verificador;

import es.dmoral.toasty.Toasty;






public class CrearCuenta1Activity extends AppCompatActivity implements View.OnClickListener, ICrearCuenta.ICrearCuenta_View {

    private TextInputLayout txt_apellidos, txt_nombre, txt_dni;
    private Docente docente;
    private LinearLayout btn_Siguiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta1);
        getSupportActionBar().hide();
        Bundle bundle = getIntent().getExtras();
        docente = (Docente) bundle.getSerializable("Docente");
        obtenerDatosxml();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (docente == null) {
            Intent intent = new Intent(this, IniciarSesionActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
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

    @Override
    public void obtenerDatosxml() {
        txt_apellidos = findViewById(R.id.txt_apellidos);
        txt_nombre = findViewById(R.id.txt_nombre);
        txt_dni = findViewById(R.id.txt_dni);
        btn_Siguiente = findViewById(R.id.btn_Siguiente);


        btn_Siguiente.setOnClickListener(this);
    }

    @Override
    public void mostrarErrorRed() {
        Toasty.error(this, "Error de red", Toasty.LENGTH_LONG).show();

    }

    @Override
    public void habiltarBoton() {
        btn_Siguiente.setEnabled(true);
    }

    @Override
    public void inabilitarBoton() {
        btn_Siguiente.setEnabled(false);
    }

    @Override
    public void llenarDatos() {

    }

    @Override
    public void guardarDatos() {
        if (verificarDatos()) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Docente", (Docente) obtenerDatos());
            Intent intent = new Intent(this, CrearCuenta2Activity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public Object obtenerDatos() {
        docente.setDni(txt_dni.getEditText().getText().toString());
        docente.setApellidos(txt_apellidos.getEditText().getText().toString());
        docente.setNombre(txt_nombre.getEditText().getText().toString());
        return docente;
    }

    @Override
    public boolean verificarDatos() {
        int contador = 0, suma = 0;
        contador = Verificador.veriricarConttadorTexto(txt_dni, 8, "Ingrese un dni valido");
        suma = contador + suma;
        contador = Verificador.veriricarTextoVacio(txt_apellidos, "Ingrese un apellido valido");
        suma = contador + suma;
        contador = Verificador.veriricarTextoVacio(txt_nombre, "Ingrese un nombre valido");
        suma = contador + suma;

        return suma == 3;
    }

    @Override
    public void operacionCorrecta(String mensaje) {

    }

    @Override
    public void operacionIncorrecta(String mensaje) {

    }
}
