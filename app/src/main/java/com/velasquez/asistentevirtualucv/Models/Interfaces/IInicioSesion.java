package com.velasquez.asistentevirtualucv.Models.Interfaces;

import com.velasquez.asistentevirtualucv.Models.Clases.Docente;

public class IInicioSesion {
    public interface IIniciarSesion_Interactor {
        void iniciarSesion(Docente docente);
    }
    public interface IIniciarSesion_Presentor extends IError_Presentor {
        void iniciarSesion(Docente docente);
    }

    public interface IIniciarSesion_View extends IError_View{
    }

}
