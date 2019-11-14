package com.velasquez.asistentevirtualucv.Utils;

import android.content.Context;
import android.util.Log;

import com.google.android.material.textfield.TextInputLayout;

import java.net.NetworkInterface;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class Verificador {
    public static int posicionFragmentCrearUsuario = 0;
    public static Context context;


    public static String opcionCrearUsuario = "SIGUIENTE";
    public static List<String> listaCorreoVerificador = new ArrayList<>();

    public static String fechaActualSistema = "";

    // public  static Prestamo prestamo;

    public static void agregarCorreoElectronicoVericado(String correo) {
        listaCorreoVerificador.add(correo);
    }

    public static boolean verificarCorreoElectronicoVerificado(String correo) {
        boolean estado = false;
        for (String auxCorreo : listaCorreoVerificador) {
            if (correo.equals(auxCorreo)) {
                estado = true;
            }
        }
        return estado;
    }

    public static String agregarCeros(String string) {

        String letra[] = string.split("\\.");


        StringBuffer as = new StringBuffer(string);
        int length = string.length();
        if (letra.length > 1) {
            if (letra[1].length() == 1) {
                as.append("0");
            } else if (letra[1].length() == 2) {
                as.append(".00");
            } else if (letra[1].length() > 2) {
                as.deleteCharAt(length - 1);
            }
        } else {
            as.append(".00");
        }

        return "" + as;
    }

    public static int getPosicionFragmentCrearUsuario() {
        return posicionFragmentCrearUsuario;
    }

    public static void setPosicionFragmentCrearUsuario(int posicionFragmentCrearUsuario) {
        Verificador.posicionFragmentCrearUsuario = posicionFragmentCrearUsuario;
    }

    public static boolean estaVacio(TextInputLayout editText) {
        return editText.getEditText().getText().toString().trim().isEmpty();
    }

    public static int veriricarTextoVacio(TextInputLayout editText) {
        if (estaVacio(editText)) {
            editText.getEditText().requestFocus();
            return 0;
        } else {
            return 1;
        }
    }

    public static void inabilitarCampos(TextInputLayout editText) {
        editText.setEnabled(false);


    }

    public static int veriricarConttadorTexto(TextInputLayout editText, int contador) {
        if (estaVacio(editText)) {
            editText.setErrorEnabled(true);
            editText.getEditText().requestFocus();
            return 0;
        } else if (editText.getEditText().getText().length() == contador) {
            editText.setErrorEnabled(false);
            return 1;
        } else {
            editText.getEditText().requestFocus();
            return 0;
        }

    }


    public static int veriricarFechaAnio(TextInputLayout editText, int anioMin, int anioMax, String texto) {
        if (editText.getEditText().getText().length() == 0) {
            editText.setError(texto);
            editText.getEditText().requestFocus();
            return 0;
        } else {
            int anio = Integer.parseInt(editText.getEditText().getText().toString());
            if (estaVacio(editText)) {
                editText.setError(texto);
                editText.getEditText().requestFocus();
                return 0;
            } else if (anio > anioMin && anio < anioMax) {
                editText.setErrorEnabled(false);
                return 1;
            } else {
                editText.setError(texto);
                editText.getEditText().requestFocus();
                return 0;
            }
        }


    }

    public static int veriricarConttadorTexto(TextInputLayout editText, int contador, String texto) {
        if (estaVacio(editText)) {
            editText.setError(texto);
            editText.getEditText().requestFocus();
            return 0;
        } else if (
                editText.getEditText().getText().length() == contador) {
            editText.setErrorEnabled(false);
            return 1;
        } else {
            editText.setError(texto);
            editText.getEditText().requestFocus();
            return 0;
        }

    }

    public static int veriricarTextoVacio(TextInputLayout editText, String nombre) {
        if (estaVacio(editText)) {
            editText.setError(nombre);
            editText.getEditText().requestFocus();
            return 0;
        } else {
            editText.setErrorEnabled(false);
            return 1;
        }
    }

    public static int veriricarCorreoElectronico(TextInputLayout editText, String nombre) {
        if (estaVacio(editText)) {
            editText.setError(nombre);
            editText.getEditText().requestFocus();
            return 0;
        } else {

            boolean encontrado = false;

            String cadena = editText.getEditText().getText().toString();
            for (int i = 0; i < cadena.length(); i++) {
                char c = cadena.charAt(i);
                System.out.println(c);
                String convertido = "" + c;
                if (convertido.equals("@")) {
                    encontrado = true;
                }

            }
            if (encontrado) {
                editText.setErrorEnabled(false);
                return 1;
            } else {
                editText.setError(nombre);
                editText.getEditText().requestFocus();
                return 0;
            }

        }
    }


    public static boolean verificacdorMayorA(TextInputLayout txt_crearUsuario5Contraseña, int i) {
        if (txt_crearUsuario5Contraseña.getEditText().getText().length() > i) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isOnlineNet() {
        try {
            Process p = Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static void clearCuedntasGamil() {
        listaCorreoVerificador.clear();
    }

    public static String obtenerAnio() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public static String sumarDiasAFecha(Date fecha, int dias) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (dias == 0) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        String fechaObtenida = "";
        fechaObtenida = dateFormat.format(calendar.getTime());
        return fechaObtenida;
    }

    public static String getMacAddress() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
        return "";
    }


    public static int calcularDiasRestante(String fechaFin) {
        int dias = 0;
        long diass = 0;
        final long MILLSECS = 24 * 60 * 60 * 1000;
        try {

            String[] arrayFechaFin = fechaFin.split("-");
            int diaf = Integer.parseInt(arrayFechaFin[0]);
            int mesf = Integer.parseInt(arrayFechaFin[1]);
            int aniof = Integer.parseInt(arrayFechaFin[2]);
            Calendar calendar = new GregorianCalendar(aniof, mesf - 1, diaf);
            Date dateFechaFin = new Date(calendar.getTimeInMillis());

            String[] arrayFechaInicio = Verificador.fechaActualSistema.split("-");
            int diai = Integer.parseInt(arrayFechaInicio[0]);
            int mesi = Integer.parseInt(arrayFechaInicio[1]);
            int anioi = Integer.parseInt(arrayFechaInicio[2]);
            Calendar calendar1 = new GregorianCalendar(anioi, mesi - 1, diai);
            Date dateFechaInicio = new Date(calendar1.getTimeInMillis());
            diass = ((dateFechaFin.getTime() - dateFechaInicio.getTime()) / MILLSECS);


        } catch (Exception e) {
            Log.e("levi", e.getMessage());
        }

        String fecha = "" + diass;


        return Integer.parseInt(fecha);
    }

    public static String acumularDineroToPrestamo(int totalDiasFalta, Double monto, String mora) {
        Double totalAcumulado = 0.0;
        final List<Integer> listaContadr = new ArrayList<>();
        int sumador = 3;
        for (int i = 1; i <= totalDiasFalta; i++) {
            if (i == sumador) {
                listaContadr.add(i);
                sumador += 3;
            }
        }
        for (int i = 0; i < listaContadr.size(); i++) {
            monto = monto + Double.parseDouble(mora);
        }
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println();
        String numer = df.format(monto);
        return numer;

    }


    public static int ponerEnFormato12(int hora) {
        switch (hora) {
            case 13:
                hora = 1;
                break;
            case 14:
                hora = 2;
                break;
            case 15:
                hora = 3;
                break;
            case 16:
                hora = 4;
                break;
            case 17:
                hora = 5;
                break;
            case 18:
                hora = 6;
                break;
            case 19:
                hora = 7;
                break;
            case 20:
                hora = 8;
                break;
            case 21:
                hora = 9;
                break;
            case 22:
                hora = 10;
                break;
            case 23:
                hora = 11;
                break;

        }
        return hora;
    }

    public static int ponerEnFormato24(int hora) {
        switch (hora) {
            case 1:
                hora = 13;
                break;
            case 2:
                hora = 14;
                break;
            case 3:
                hora = 15;
                break;
            case 4:
                hora = 16;
                break;
            case 5:
                hora = 17;
                break;
            case 6:
                hora = 18;
                break;
            case 7:
                hora = 19;
                break;
            case 8:
                hora = 20;
                break;
            case 9:
                hora = 21;
                break;
            case 10:
                hora = 22;
                break;
            case 11:
                hora = 23;
                break;

        }
        return hora;
    }


}
