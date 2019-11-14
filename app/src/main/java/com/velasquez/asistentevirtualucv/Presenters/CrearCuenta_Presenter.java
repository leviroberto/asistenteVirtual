package com.velasquez.asistentevirtualucv.Presenters;


import com.google.firebase.auth.FirebaseAuth;
import com.velasquez.asistentevirtualucv.Interactors.CrearCuenta_Interactor;
import com.velasquez.asistentevirtualucv.Interactors.Docente_Interactor;
import com.velasquez.asistentevirtualucv.Interfaces.ICrearCuenta_Interactor;
import com.velasquez.asistentevirtualucv.Interfaces.ICrearCuenta_Presentor;
import com.velasquez.asistentevirtualucv.Interfaces.ICrearCuenta_View;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_Interactor;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_Presenter;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_View;
import com.velasquez.asistentevirtualucv.Models.Docente;
import com.velasquez.asistentevirtualucv.Utils.Mensajes;


public class CrearCuenta_Presenter implements ICrearCuenta_Presentor {
    private ICrearCuenta_View iCrearCuenta_view;
    private ICrearCuenta_Interactor iDocente_interactor;

    public CrearCuenta_Presenter(ICrearCuenta_View iCrearCuenta_view) {
        this.iCrearCuenta_view = iCrearCuenta_view;
        this.iDocente_interactor = new CrearCuenta_Interactor(this);

    }


    @Override
    public void crearCuenta(String email, String password) {
        iCrearCuenta_view.inabilitarBoton();
        iDocente_interactor.crearCuenta(email,password);
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        iCrearCuenta_view.habiltarBoton();
        iCrearCuenta_view.operacionCorrecta(Mensajes.creacion_exitosa);
    }

    @Override
    public void operacionIncorrecta(String mensaje) {
        iCrearCuenta_view.habiltarBoton();
        iCrearCuenta_view.operacionIncorrecta(mensaje);
    }

    @Override
    public void mostrarErrorRed() {
        iCrearCuenta_view.habiltarBoton();
        iCrearCuenta_view.mostrarErrorRed();
    }
}
