package com.velasquez.asistentevirtualucv.Models.Clases;




public class Chat {
    private String id;
    private String descripcion;
    private String tipo_user;//si es del both o user
    private String tipe; //de qye tipo es si es curso , profesor o algo
    private boolean clickeable;


    public static final String TYPE_USER_BOTH ="BOTH";
    public static final String TYPE_USER_USER ="USER";

    public static final String TYPE_CURSO="Curso";
    public static final String TYPE_DEFECTO="DEFECTO";
    public static final String TYPE_ERROR="EROOR";

    public Chat(String descripcion, String tipo_user, boolean clickeable,String tipe) {
        this.descripcion = descripcion;
        this.tipo_user = tipo_user;
        this.clickeable=clickeable;
        this.tipe=tipe;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public boolean isClickeable() {
        return clickeable;
    }

    public void setClickeable(boolean clickeable) {
        this.clickeable = clickeable;
    }

    public Chat() {
    }

    public String getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(String tipo_user) {
        this.tipo_user = tipo_user;
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
