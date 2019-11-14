package com.velasquez.asistentevirtualucv.Interfaces;


import com.velasquez.asistentevirtualucv.Models.Curso;

public interface ICurso_Presenter {
    void agregar(Curso curso);

    void modificar(Curso curso);

    void eliminar(Curso curso);

    void operacionCorrecta();

    void operacionIncorrecta();

    void mostrarErrorRed();
}
