package com.velasquez.asistentevirtualucv.Models.Interfaces;

import com.velasquez.asistentevirtualucv.Models.Clases.Curso;

public class ICurso {

    public interface ICurso_View extends IError_View {
    }
    public interface ICurso_Presentor extends IError_Presentor{
        void verificarCurso(Curso curso);
        void existeCursoCorrecto();
        void existeCursoIncorrecto(Curso curso);
    }
    public interface ICurso_Interactor{
        void guardar(Curso curso);
        void verificarCurso(Curso curso);
    }
}
