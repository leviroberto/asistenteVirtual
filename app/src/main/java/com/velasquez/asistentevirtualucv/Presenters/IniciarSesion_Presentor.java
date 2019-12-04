package com.velasquez.asistentevirtualucv.Presenters;

import com.velasquez.asistentevirtualucv.Interactors.IniciarSesion_Interactor;
import com.velasquez.asistentevirtualucv.Models.Clases.Docente;
import com.velasquez.asistentevirtualucv.Models.Interfaces.IInicioSesion;

public class IniciarSesion_Presentor implements IInicioSesion.IIniciarSesion_Presentor {
    private IInicioSesion.IIniciarSesion_View iIniciarSesion_view;

    private IInicioSesion.IIniciarSesion_Interactor iIniciarSesion_interactor;

    public IniciarSesion_Presentor(IInicioSesion.IIniciarSesion_View iIniciarSesion_view) {
        this.iIniciarSesion_view = iIniciarSesion_view;
        iIniciarSesion_interactor = new IniciarSesion_Interactor(this);
    }

    @Override
    public void iniciarSesion(Docente docente) {
        iIniciarSesion_view.inabilitarBoton();
        iIniciarSesion_interactor.iniciarSesion(docente);
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        iIniciarSesion_view.habiltarBoton();
        iIniciarSesion_view.operacionCorrecta(mensaje);
    }

    @Override
    public void operacionIncorrecta(String mensaje) {
        iIniciarSesion_view.habiltarBoton();
        iIniciarSesion_view.operacionIncorrecta(mensaje);
    }

    @Override
    public void mostrarErrorRed() {
        iIniciarSesion_view.habiltarBoton();
        iIniciarSesion_view.mostrarErrorRed();
    }
}
