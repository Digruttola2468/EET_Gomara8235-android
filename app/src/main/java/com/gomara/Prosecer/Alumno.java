package com.gomara.Prosecer;

public class Alumno {

    private String alumno;
    private float inasistencias;

    public Alumno(String alumno,float inasistencias) {
        this.alumno = alumno;
        this.inasistencias = inasistencias;
    }

    //GETTERS
    public String getAlumno() {
        return alumno;
    }
    public float getInasistencias() {
        return inasistencias;
    }

}
