package com.velasquez.asistentevirtualucv.Models.Clases;

import java.util.Date;

public class Tarea {


    private String id;
    private String fecha;
    private String hora;
    private String titulo;
    private Curso curso;
    private String docente_id;
    private String estado;

    public Tarea() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDocente_id() {
        return docente_id;
    }

    public void setDocente_id(String docente_id) {
        this.docente_id = docente_id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
