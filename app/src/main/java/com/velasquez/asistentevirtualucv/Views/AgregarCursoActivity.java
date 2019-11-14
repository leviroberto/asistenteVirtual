package com.velasquez.asistentevirtualucv.Views;

import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.velasquez.asistentevirtualucv.R;
import com.velasquez.asistentevirtualucv.Utils.Verificador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;


public class AgregarCursoActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout btn_Guardar;
    private Button btn_Error;
    private TextInputLayout txt_horaInicio;
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_curso);
        txt_horaInicio = findViewById(R.id.txt_horaInicio);

        txt_horaInicio.getEditText().setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_nombre:

                break;
        }
    }

    public void obtenerHora(View view) {
        //Variables para obtener la hora hora
        int hora = 0;
        int minuto = 0;
        String horaInicio = txt_horaInicio.getEditText().getText().toString();
        if (!horaInicio.isEmpty()) {
            String arreglo[] = horaInicio.split(" ");
            hora = Integer.parseInt(arreglo[0].split(":")[0]);
            hora=Verificador.ponerEnFormato24(hora);
            minuto = Integer.parseInt(arreglo[0].split(":")[1]);
        } else {
            hora = c.get(Calendar.HOUR_OF_DAY);
            minuto = c.get(Calendar.MINUTE);
        }


        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada = (hourOfDay < 10) ? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                horaFormateada = String.valueOf(Verificador.ponerEnFormato12(Integer.parseInt(horaFormateada)));
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10) ? String.valueOf(CERO + minute) : String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if (hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                txt_horaInicio.getEditText().setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }


}
