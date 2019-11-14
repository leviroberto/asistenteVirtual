package com.velasquez.asistentevirtualucv.Presenters;


import com.velasquez.asistentevirtualucv.Interactors.Docente_Interactor;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_Interactor;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_Presenter;
import com.velasquez.asistentevirtualucv.Interfaces.IDocente_View;
import com.velasquez.asistentevirtualucv.Models.Docente;


public class Docente_Presenter implements IDocente_Presenter {
    private IDocente_View iDocente_view;
    private IDocente_Interactor iDocente_interactor;

    public Docente_Presenter(IDocente_View iDocente_view) {
        this.iDocente_view = iDocente_view;
        this.iDocente_interactor = new Docente_Interactor(this);
    }

    @Override
    public void agregar(Docente docente) {
        iDocente_view.inabilitarBoton();
        iDocente_interactor.agregar(docente);
    }

    @Override
    public void modificar(Docente docente) {
        iDocente_view.inabilitarBoton();
        iDocente_interactor.moficiar(docente);
    }

    @Override
    public void eliminar(Docente docente) {
        iDocente_view.inabilitarBoton();
        iDocente_interactor.eliminar(docente);
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        iDocente_view.habiltarBoton();
        iDocente_view.operacionCorrecta("");
    }

    @Override
    public void operacionIncorrecta(String mensaje) {
        iDocente_view.habiltarBoton();
        iDocente_view.operacionIncorrecta("");
    }

    @Override
    public void mostrarErrorRed() {
        iDocente_view.habiltarBoton();
        iDocente_view.mostrarErrorRed();
    }
}
