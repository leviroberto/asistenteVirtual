package com.velasquez.asistentevirtualucv.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.velasquez.asistentevirtualucv.R;

public class CrearCuenta2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta2);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerUniversidad);
        String[] universidades = {"UNT", "UPAO", "UCV", "UPD", "UPN"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, universidades));
    }
}
