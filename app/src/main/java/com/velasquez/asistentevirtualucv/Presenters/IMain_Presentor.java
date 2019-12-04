package com.velasquez.asistentevirtualucv.Presenters;


import com.velasquez.asistentevirtualucv.Interactors.Main_Interactor;
import com.velasquez.asistentevirtualucv.Models.Clases.Curso;
import com.velasquez.asistentevirtualucv.Models.Clases.Tarea;
import com.velasquez.asistentevirtualucv.Models.Interfaces.IMain;

import java.util.List;


public class IMain_Presentor implements IMain.IMain_Presentor {

    private IMain.IMain_View iMain_view;
    private IMain.IMain_Interactor iMain_interactor;

    public IMain_Presentor(IMain.IMain_View iMain_view) {
        this.iMain_view = iMain_view;
        this.iMain_interactor = new Main_Interactor(this);
    }

    @Override
    public void obtenerCursos() {
        iMain_view.inabilitarBoton();
        iMain_interactor.obtenerCursos();
    }

    @Override
    public void obtenerCursosCorrecto(List<Curso> listaCurso) {
        iMain_view.habiltarBoton();
        iMain_view.obtenerCursosCorrecto(listaCurso);
    }

    @Override
    public void crearTarea(Tarea tarea) {
        iMain_interactor.crearTarea(tarea);
    }

    @Override
    public void buscarTareaPorFecha(String date) {
        iMain_interactor.buscarTareaPorFecha(date);
    }

    @Override
    public void buscarTareaPorFechaCorrecto(List<Tarea> listTarea) {
        iMain_view.buscarTareaPorFechaCorrecto(listTarea);
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        iMain_view.operacionCorrecta(mensaje);
    }

    @Override
    public void operacionIncorrecta(String mensaje) {
        iMain_view.operacionIncorrecta(mensaje);
    }

    @Override
    public void mostrarErrorRed() {

    }
}
