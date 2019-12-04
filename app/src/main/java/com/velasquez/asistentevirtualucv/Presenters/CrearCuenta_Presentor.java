package com.velasquez.asistentevirtualucv.Presenters;


import com.velasquez.asistentevirtualucv.Interactors.CrearCuenta_Interactor;
import com.velasquez.asistentevirtualucv.Models.Clases.Docente;
import com.velasquez.asistentevirtualucv.Models.Interfaces.ICrearCuenta;


public class CrearCuenta_Presentor implements ICrearCuenta.ICrearCuenta_Presentor {
    private ICrearCuenta.ICrearCuenta_View iCrearCuenta_view;
    private ICrearCuenta.ICrearCuenta_Interactor iDocente_interactor;

    public CrearCuenta_Presentor(ICrearCuenta.ICrearCuenta_View iCrearCuenta_view) {
        this.iCrearCuenta_view = iCrearCuenta_view;
        this.iDocente_interactor = new CrearCuenta_Interactor(this);

    }


    @Override
    public void VerificarEmail(String email) {
        iCrearCuenta_view.inabilitarBoton();
        iDocente_interactor.VerificarEmail(email);
    }

    @Override
    public void crearCuenta(Docente docente) {
        iCrearCuenta_view.inabilitarBoton();
        iDocente_interactor.crearCuenta(docente);
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        iCrearCuenta_view.habiltarBoton();
        iCrearCuenta_view.operacionCorrecta(mensaje);
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
