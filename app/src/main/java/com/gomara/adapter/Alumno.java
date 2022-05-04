package com.gomara.adapter;

public class Alumno {

    private String alumno;
    private float inasistencias;

    public Alumno(String alumno,float inasistencias) {
        this.alumno = alumno;
        this.inasistencias = inasistencias;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public float getInasistencias() {
        return inasistencias;
    }

    public void setInasistencias(int inasistencias) {
        this.inasistencias = inasistencias;
    }

}
