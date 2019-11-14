package com.velasquez.asistentevirtualucv.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.velasquez.asistentevirtualucv.R;


public class IniciarSesionActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout btn_crearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        getSupportActionBar().hide();

        btn_crearCuenta = findViewById(R.id.btn_crearCuenta);
        btn_crearCuenta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_crearCuenta:
                Intent intent = new Intent(this, CrearCuentaActivity.class);
                startActivity(intent);
                break;
        }
    }
}
