package com.velasquez.asistentevirtualucv.Models.Clases;

public class Curso {
    private String id;
    private String nombre;
    private String creditos;
    private String horaInicio;
    private String horaFin;
    private String periodo;
    private String seccion;
    private String ciclo;
    private String estado;
    private String docente_Id;


    public static final String ESTADO_ACTIVO = "Activo";
    public static final String ESTADO_TERMINADO = "Terminado";

    public Curso() {
    }

    public String getDocente_Id() {
        return docente_Id;
    }

    public void setDocente_Id(String docente_Id) {
        this.docente_Id = docente_Id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean estaActivo() {
        return estado.equals(ESTADO_ACTIVO);
    }
}


