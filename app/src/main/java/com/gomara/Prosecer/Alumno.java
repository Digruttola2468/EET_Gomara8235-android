package com.gomara.Prosecer;

public class Alumno {

    private String id;
    private String alumno;
    private float inasistencias;

    public Alumno(String id,String alumno,float inasistencias) {
        this.id = id;
        this.alumno = alumno;
        this.inasistencias = inasistencias;
    }

    public Alumno(String id,String alumno,double inasistencias) {
        this.alumno = alumno;
        this.id = id;
        this.inasistencias = (float) inasistencias;
    }


    //GETTERS
    public String getAlumno() {
        return alumno;
    }
    public float getInasistencias() {
        return inasistencias;
    }
    public String getId() {
        return id;
    }

    //Setters
    public void setInasistencias(float inasistencias) {
        this.inasistencias = inasistencias;
    }
}
