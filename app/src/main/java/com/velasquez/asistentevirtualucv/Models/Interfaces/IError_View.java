package com.velasquez.asistentevirtualucv.Models.Interfaces;

public interface IError_View {
    void obtenerDatosxml();
    void mostrarErrorRed();
    void habiltarBoton();
    void inabilitarBoton();
    void llenarDatos();
    void guardarDatos();
    Object obtenerDatos();
    boolean verificarDatos();
    void operacionCorrecta(String mensaje);
    void operacionIncorrecta(String mensaje);
}
