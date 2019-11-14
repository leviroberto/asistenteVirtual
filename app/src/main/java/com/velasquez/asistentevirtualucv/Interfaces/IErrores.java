package com.velasquez.asistentevirtualucv.Interfaces;


public interface IErrores {
    void mostrarErrorRed();

    void habiltarBoton();

    void inabilitarBoton();

    void obtenerDatosxml();

    void llenarDatos();

    void guardarDatos();
    Object obtenerDatos();
    boolean verificarDatos();
    void operacionCorrecta(String mensaje);
    void operacionIncorrecta(String mensaje);

}
