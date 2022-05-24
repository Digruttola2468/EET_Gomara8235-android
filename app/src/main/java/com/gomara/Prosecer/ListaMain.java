package com.gomara.Prosecer;

public class ListaMain {

    private int imageView;
    private String texto;

    public ListaMain(int imageView, String texto) {
        this.imageView = imageView;
        this.texto = texto;
    }

    //GETTERS
    public String getTexto() {
        return texto;
    }
    public int getImageView() {
        return imageView;
    }

}
