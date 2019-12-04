package com.velasquez.asistentevirtualucv.Presenters;


import com.velasquez.asistentevirtualucv.Interactors.Curso_Interactor;
import com.velasquez.asistentevirtualucv.Models.Clases.Curso;
import com.velasquez.asistentevirtualucv.Models.Interfaces.ICurso;

public class Curso_Presentor implements ICurso.ICurso_Presentor {

    private ICurso.ICurso_View iCurso_view;
    private ICurso.ICurso_Interactor iCurso_interactor;

    public Curso_Presentor(ICurso.ICurso_View iCurso_view) {
        this.iCurso_view = iCurso_view;
        this.iCurso_interactor = new Curso_Interactor(this);
    }
    @Override
    public void verificarCurso(Curso curso) {
        iCurso_view.inabilitarBoton();
        iCurso_interactor.verificarCurso(curso);
    }

    @Override
    public void existeCursoCorrecto() {
        iCurso_view.operacionIncorrecta("El curso ingresado ya existe");

    }

    @Override
    public void existeCursoIncorrecto(Curso curso) {
        iCurso_view.inabilitarBoton();
        iCurso_interactor.guardar(curso);
    }

    @Override
    public void operacionCorrecta(String mensaje) {
        iCurso_view.habiltarBoton();
        iCurso_view.operacionCorrecta(mensaje);
    }

    @Override
    public void operacionIncorrecta(String menasje) {
        iCurso_view.habiltarBoton();
        iCurso_view.operacionIncorrecta(menasje);
    }

    @Override
    public void mostrarErrorRed() {
        iCurso_view.habiltarBoton();
        iCurso_view.mostrarErrorRed();
    }
}
