package com.velasquez.asistentevirtualucv.Models.Interfaces;


import com.velasquez.asistentevirtualucv.Models.Clases.Docente;

public class ICrearCuenta {

    public interface ICrearCuenta_Interactor {
        void VerificarEmail(String correo);
        void crearCuenta(Docente docente);
    }

    public interface ICrearCuenta_Presentor extends IError_Presentor {
        void VerificarEmail(String email);
        void crearCuenta(Docente docente);
    }

    public interface ICrearCuenta_View extends IError_View {
    }

}
