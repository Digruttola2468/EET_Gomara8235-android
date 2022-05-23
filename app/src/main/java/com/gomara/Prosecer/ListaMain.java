package com.gomara.Prosecer;

public class ListaMain {

    private int imageView;
    private String texto;

    public ListaMain(int imageView, String texto) {
        this.imageView = imageView;
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
