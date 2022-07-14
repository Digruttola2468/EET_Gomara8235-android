package com.gomara.Prosecer;

public class Comunicado {

    private String title;
    private String contendio;
    private String fechaEnvio;

    public Comunicado(String title, String contenido, String fechaEnvio) {
        this.title = title;
        this.contendio = contenido;
        this.fechaEnvio = fechaEnvio;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
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
