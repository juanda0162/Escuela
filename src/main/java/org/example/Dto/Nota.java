package org.example.Dto;

import java.math.BigDecimal;

public class Nota {
    private int idNota;
    private int idEstudiante;
    private int idCurso;
    private int periodo;
    private BigDecimal nota;

    public Nota(int idEstudiante, int idCurso, int periodo, BigDecimal nota) {
        this.idEstudiante = idEstudiante;
        this.idCurso = idCurso;
        this.periodo = periodo;
        this.nota = nota;
    }

    public Nota() {
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idNota\":" + idNota +
                ", \"idEstudiante\":" + idEstudiante +
                ", \"idCurso\":" + idCurso +
                ", \"periodo\":" + periodo +
                ", \"nota\":" + nota +
                '}';
    }
}
