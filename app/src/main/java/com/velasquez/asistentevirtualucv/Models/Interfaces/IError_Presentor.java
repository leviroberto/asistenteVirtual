package com.velasquez.asistentevirtualucv.Models.Interfaces;

public interface IError_Presentor {
    void operacionCorrecta(String mensaje);
    void operacionIncorrecta(String mensaje);
    void mostrarErrorRed();
}
