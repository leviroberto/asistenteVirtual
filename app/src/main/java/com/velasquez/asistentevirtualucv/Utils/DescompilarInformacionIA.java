package com.velasquez.asistentevirtualucv.Utils;

import ai.api.model.Result;

public class DescompilarInformacionIA {

    public DescompilarInformacionIA() {

    }

    public void verificarAccion(Result result) {
        if (result.getAction().equals("docenteAgregarTarea")) {
            String titulo = "", fecha = "", hora = "";

            if (result.getParameters().get("titulo") != null) {
                titulo = result.getParameters().get("titulo").toString();
            }

            if (result.getParameters().get("fecha") != null) {
                fecha = result.getParameters().get("fecha").toString();
            }
            if (result.getParameters().get("fecha") != null) {
                hora = result.getParameters().get("hora").toString();
            }

            if (!titulo.isEmpty() && !fecha.isEmpty() && !hora.isEmpty()) {

            }
        }

    }
}
