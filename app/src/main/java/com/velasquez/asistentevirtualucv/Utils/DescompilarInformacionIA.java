package com.velasquez.asistentevirtualucv.Utils;

import com.velasquez.asistentevirtualucv.Models.Clases.Curso;
import com.velasquez.asistentevirtualucv.Models.Clases.Tarea;
import com.velasquez.asistentevirtualucv.Models.Interfaces.IMain;

import ai.api.model.Result;

public class DescompilarInformacionIA {


    private IMain.IMain_View iMain_view;


    public DescompilarInformacionIA(IMain.IMain_View iMain_view) {
        this.iMain_view = iMain_view;

    }

    public void verificarAccion(Result result) {
        if (result.getAction().equals("docenteAgregarTarea")) {
            String titulo = "", fecha = "", hora = "";
            Curso curso = null;

            if (result.getParameters().get("titulo") != null) {
                titulo = result.getParameters().get("titulo").toString();
                titulo = titulo.replaceAll("\"", "");

            }

            if (result.getParameters().get("fecha") != null) {
                fecha = result.getParameters().get("fecha").toString();
                fecha = fecha.replaceAll("\"", "");

            }
            if (result.getParameters().get("hora") != null) {
                hora = result.getParameters().get("hora").toString();
                hora = hora.replaceAll("\"", "");
            }
            if (result.getParameters().get("curso") != null) {
                curso = new Curso();
                String resultado = result.getParameters().get("curso").toString();
                resultado = resultado.replaceAll("\"", "");
                curso.setNombre(resultado);

            }

            if (!titulo.isEmpty() && !fecha.isEmpty() && !hora.isEmpty() && curso == null) {
                iMain_view.mostrarCursosChat();
            } else if (!titulo.isEmpty() && !fecha.isEmpty() && !hora.isEmpty() && curso != null) {
                Tarea tarea = new Tarea();
                tarea.setCurso(curso);
                tarea.setFecha(fecha);
                tarea.setHora(hora);
                tarea.setTitulo(titulo);
                iMain_view.crearTarea(tarea);
            }
        } else if (result.getAction().equals("docenteBuscarTareas")) {
            String date = "";
            if (result.getParameters().get("date") != null) {
                date = result.getParameters().get("date").toString();
                date = date.replaceAll("\"", "");

            }

            if (!date.isEmpty()) {
                iMain_view.buscarTareaPorFecha(date);
            }


        }

    }
}
