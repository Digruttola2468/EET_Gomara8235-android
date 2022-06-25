package com.gomara.Prosecer;

public class Materias {

    private String materia;
    private String fechaPrueba;

    public Materias(String materia,String fechaPrueba){
        this.materia = materia;
        this.fechaPrueba = fechaPrueba;
    }

    public String getMateria() {
        return materia;
    }

    public String getFechaPrueba() {
        return fechaPrueba;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
