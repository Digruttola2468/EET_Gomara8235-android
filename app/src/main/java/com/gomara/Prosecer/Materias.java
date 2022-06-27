package com.gomara.Prosecer;

public class Materias {

    private String materia;
    private String fechaPrueba;
    private String temasEvaluacion;


    public Materias(String materia,String fechaPrueba){
        this.materia = materia;
        this.fechaPrueba = fechaPrueba;
    }

    public Materias(String materia,String fechaPrueba,String temasEvaluacion){
        this.materia = materia;
        this.fechaPrueba = fechaPrueba;
        this.temasEvaluacion = temasEvaluacion;
    }

    public void setFechaPrueba(String fechaPrueba) {
        this.fechaPrueba = fechaPrueba;
    }

    public String getTemasEvaluacion() {
        return temasEvaluacion;
    }

    public void setTemasEvaluacion(String temasEvaluacion) {
        this.temasEvaluacion = temasEvaluacion;
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
