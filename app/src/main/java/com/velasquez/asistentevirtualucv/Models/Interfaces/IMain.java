package com.velasquez.asistentevirtualucv.Models.Interfaces;

import com.velasquez.asistentevirtualucv.Models.Clases.Curso;
import com.velasquez.asistentevirtualucv.Models.Clases.Tarea;

import java.util.List;

public class IMain {






    public interface IMain_View  extends IError_View{

        void inicializadores();
        void obtenerCursosCorrecto(List<Curso> listaCurso);


        void mostrarCursosChat();

        void crearTarea(Tarea tarea);

        void responder(String descripcion);

        void cursoSeleccionado(String descripcion);

        void buscarTareaPorFecha(String date);

        void buscarTareaPorFechaCorrecto(List<Tarea> listTarea);
    }

    public interface  IMain_Interactor{
        void obtenerCursos();

        void crearTarea(Tarea tarea);

        void buscarTareaPorFecha(String date);
    }
    public interface  IMain_Presentor extends IError_Presentor{
        void obtenerCursos();
        void obtenerCursosCorrecto(List<Curso> listaCurso);

        void crearTarea(Tarea tarea);

        void buscarTareaPorFecha(String date);

        void buscarTareaPorFechaCorrecto(List<Tarea> listTarea);
    }
}
