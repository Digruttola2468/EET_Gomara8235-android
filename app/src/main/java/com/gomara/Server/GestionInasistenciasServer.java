package com.gomara.Server;

import com.gomara.Prosecer.Alumno;

import java.util.ArrayList;

public interface GestionInasistenciasServer {
    void getAnioCurso();
    void getListAlumno(String anio,String curso);
    void updateAlumnos(String anio,String curso, ArrayList<Alumno> alumnos);
}
