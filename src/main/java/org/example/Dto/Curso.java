package org.example.Dto;

public class Curso {
    private int idCurso;
    private String nombre;

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public Curso() {
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idCurso\":" + idCurso +
                ", \"nombre\":\"" + nombre + "\"" +
                '}';
    }
}
