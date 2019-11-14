package com.velasquez.asistentevirtualucv.Interfaces;


import com.velasquez.asistentevirtualucv.Models.Docente;

public interface IDocente_Presenter {
    void agregar(Docente docente);

    void modificar(Docente docente);

    void eliminar(Docente docente);

    void operacionCorrecta(String mensaje);

    void operacionIncorrecta(String mensaje);

    void mostrarErrorRed();
}
