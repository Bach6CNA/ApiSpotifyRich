package com.richpolissystems.android.apispotipy;

/**
 * Created by richpolis on 14/09/15.
 */
public class Saga {
    private String saga;
    private String descripcion;
    private String imagen;

    public Saga(String saga, String descripcion, String imagen) {
        this.saga = saga;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getSaga() {
        return saga;
    }

    public void setSaga(String saga) {
        this.saga = saga;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
