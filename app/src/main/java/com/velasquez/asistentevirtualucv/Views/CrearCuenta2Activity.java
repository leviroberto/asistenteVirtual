package com.velasquez.asistentevirtualucv.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.velasquez.asistentevirtualucv.Models.Clases.Docente;
import com.velasquez.asistentevirtualucv.Models.Interfaces.ICrearCuenta;
import com.velasquez.asistentevirtualucv.Presenters.CrearCuenta_Presentor;
import com.velasquez.asistentevirtualucv.R;

import es.dmoral.toasty.Toasty;

public class CrearCuenta2Activity extends AppCompatActivity implements View.OnClickListener, ICrearCuenta.ICrearCuenta_View {

    private Docente docente;
    private TextView lbl_provincia, lbl_despartamento, lbl_universidad;
    private String departamento = "La Libertad";
    private String provincia = "Trujillo";
    private String universidad = "Universidad Cesar Vallejo";
    private ICrearCuenta.ICrearCuenta_Presentor iCrearCuenta_presentor;
    private LinearLayout btn_Siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta2);
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

        llenarDatos();
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
        lbl_provincia = findViewById(R.id.lbl_provincia);
        lbl_despartamento = findViewById(R.id.lbl_despartamento);
        lbl_universidad = findViewById(R.id.lbl_universidad);
        iCrearCuenta_presentor = new CrearCuenta_Presentor(this);
        btn_Siguiente = findViewById(R.id.btn_Siguiente);
        btn_Siguiente.setOnClickListener(this);

    }

    @Override
    public void mostrarErrorRed() {
        Toasty.success(this, "Error de red", Toasty.LENGTH_LONG).show();
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
        lbl_despartamento.setText(departamento);
        lbl_provincia.setText(provincia);
        lbl_universidad.setText(universidad);
    }

    @Override
    public void guardarDatos() {
        if (verificarDatos()) {
            obtenerDatos();
            iCrearCuenta_presentor.crearCuenta(docente);
        }
    }

    @Override
    public Object obtenerDatos() {
        docente.setDepartamento(departamento);
        docente.setProvincia(provincia);
        docente.setUniversidad(universidad);
        return docente;
    }

    @Override
    public boolean verificarDatos() {
        return true;
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        Toasty.success(this, mensaje, Toasty.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void operacionIncorrecta(String mensaje) {
        Toasty.error(this, mensaje, Toasty.LENGTH_LONG).show();
    }
}
