package com.gomara.Prosecer;

public class Comunicado {

    String title;
    String contendio;

    public Comunicado(String title, String contenido) {
        this.title = title;
        this.contendio = contenido;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContendio() {
        return contendio;
    }

    public void setContendio(String contendio) {
        this.contendio = contendio;
    }
}
