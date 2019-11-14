package com.velasquez.asistentevirtualucv.Models;

public class Chat {
    private String id;
    private String descripcion;
    private String tipo;


    public static final String TYPE_BOTH="BOTH";
    public static final String TYPE_USER="USER";

    public Chat(String descripcion, String tipo) {
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public Chat() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
