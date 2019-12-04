package com.velasquez.asistentevirtualucv.Views;

import android.app.TimePickerDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.velasquez.asistentevirtualucv.Models.Clases.Curso;
import com.velasquez.asistentevirtualucv.Models.Interfaces.ICurso;
import com.velasquez.asistentevirtualucv.Presenters.Curso_Presentor;
import com.velasquez.asistentevirtualucv.R;
import com.velasquez.asistentevirtualucv.Utils.Verificador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

import es.dmoral.toasty.Toasty;


public class AgregarCursoActivity extends AppCompatActivity implements ICurso.ICurso_View, View.OnClickListener {
    private LinearLayout btn_Crear;
    private Button btn_Error;
    private Spinner spinnerDias;
    private TextInputLayout txt_horaInicio, txt_horaFin, txt_nombre, txt_creditos, txt_ciclo, txt_seccion;
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    private RadioButton radio_Activo, radio_Terminado;
    //Calendario para obtener fecha & hora

    private ImageButton btn_abrirHoraInicio, btn_abrirHoraFin;
    public final Calendar c = Calendar.getInstance();

    private ICurso.ICurso_Presentor iCurso_presentor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_curso);
        getSupportActionBar().hide();


        obtenerDatosxml();

    }

    @Override
    public void obtenerDatosxml() {

        txt_horaInicio = findViewById(R.id.txt_horaInicio);
        btn_abrirHoraFin = findViewById(R.id.btn_abrirHoraFin);
        btn_abrirHoraInicio = findViewById(R.id.btn_abrirHoraInicio);
        txt_horaFin = findViewById(R.id.txt_horaFin);

        btn_Crear = findViewById(R.id.btn_Crear);
        btn_Error = findViewById(R.id.btn_Error);
        txt_nombre = findViewById(R.id.txt_nombre);
        txt_creditos = findViewById(R.id.txt_creditos);

        radio_Terminado = findViewById(R.id.radio_Terminado);
        radio_Activo = findViewById(R.id.radio_Activo);
        txt_ciclo = findViewById(R.id.txt_ciclo);
        txt_seccion = findViewById(R.id.txt_seccion);

        iCurso_presentor = new Curso_Presentor(this);
        //datos
        spinnerDias = (Spinner) findViewById(R.id.spinnerDias);
        String[] universidades = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        spinnerDias.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, universidades));

        txt_horaInicio.setEnabled(false);
        txt_horaFin.setEnabled(false);
        btn_abrirHoraInicio.setOnClickListener(this);
        btn_abrirHoraFin.setOnClickListener(this);
        btn_Crear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_abrirHoraFin:
                obtenerHora(txt_horaFin);
                break;
            case R.id.btn_abrirHoraInicio:
                obtenerHora(txt_horaInicio);
                break;
            case R.id.btn_Crear:
                guardarDatos();
                break;
        }
    }

    public void obtenerHora(final TextInputLayout textInputLayout) {
        //Variables para obtener la hora hora
        int hora = 0;
        int minuto = 0;
        String horaInicio = textInputLayout.getEditText().getText().toString();
        if (!horaInicio.isEmpty()) {
            String arreglo[] = horaInicio.split(" ");
            hora = Integer.parseInt(arreglo[0].split(":")[0]);
            hora = Verificador.ponerEnFormato24(hora);
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
                textInputLayout.getEditText().setText(horaFormateada + DOS_PUNTOS + minutoFormateado + " " + AM_PM);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }


    @Override
    public void mostrarErrorRed() {
        Toasty.success(this, "Error de red", Toasty.LENGTH_LONG).show();
    }

    @Override
    public void habiltarBoton() {
        btn_Crear.setEnabled(true);
    }

    @Override
    public void inabilitarBoton() {
        btn_Crear.setEnabled(false);
        txt_horaInicio.setEnabled(false);
        txt_horaFin.setEnabled(false);
    }

    @Override
    public void llenarDatos() {


    }

    @Override
    public void guardarDatos() {
        if (verificarDatos()) {
            iCurso_presentor.verificarCurso((Curso) obtenerDatos());
        }
    }

    @Override
    public Object obtenerDatos() {
        Curso curso = new Curso();
        curso.setCiclo(txt_ciclo.getEditText().getText().toString());
        curso.setCreditos(txt_creditos.getEditText().getText().toString());
        curso.setHoraFin(txt_horaFin.getEditText().getText().toString());
        curso.setHoraInicio(txt_horaInicio.getEditText().getText().toString());
        curso.setNombre(txt_nombre.getEditText().getText().toString());
        curso.setSeccion(txt_seccion.getEditText().getText().toString());

        if (radio_Activo.isChecked()) {
            curso.setEstado(Curso.ESTADO_ACTIVO);
        } else {
            curso.setEstado(Curso.ESTADO_TERMINADO);

        }
        return curso;
    }

    @Override
    public boolean verificarDatos() {
        int contador = 0, suma = 0;
        contador = Verificador.veriricarTextoVacio(txt_ciclo);
        suma = contador + suma;

        contador = Verificador.veriricarTextoVacio(txt_creditos);
        suma = contador + suma;

        contador = Verificador.veriricarTextoVacio(txt_horaFin);
        suma = contador + suma;

        contador = Verificador.veriricarTextoVacio(txt_horaInicio);
        suma = contador + suma;

        contador = Verificador.veriricarTextoVacio(txt_nombre);
        suma = contador + suma;

        contador = Verificador.veriricarTextoVacio(txt_seccion);
        suma = contador + suma;

        return suma == 6;
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        Toasty.success(this, mensaje, Toasty.LENGTH_LONG).show();
        this.finish();
    }

    @Override
    public void operacionIncorrecta(String mensaje) {
        Toasty.error(this, mensaje, Toasty.LENGTH_LONG).show();

    }
}
