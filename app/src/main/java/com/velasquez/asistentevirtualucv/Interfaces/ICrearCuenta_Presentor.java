package com.velasquez.asistentevirtualucv.Interfaces;

public interface ICrearCuenta_Presentor {
    void crearCuenta(String email,String password);


    void operacionCorrecta(String mensaje);

    void operacionIncorrecta(String mensaje);

    void mostrarErrorRed();
}
