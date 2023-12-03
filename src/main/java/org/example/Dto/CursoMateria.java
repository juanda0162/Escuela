package org.example.Dto;
public class CursoMateria {
    private int idCurso;
    private int idMateria;

    public CursoMateria(int idCurso, int idMateria) {
        this.idCurso = idCurso;
        this.idMateria = idMateria;
    }

    public CursoMateria() {
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idCurso\":" + idCurso +
                ", \"idMateria\":" + idMateria +
                '}';
    }
}
