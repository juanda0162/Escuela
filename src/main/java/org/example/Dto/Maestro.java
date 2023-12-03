package org.example.Dto;

import java.sql.Date;

public class Maestro {
    private int idMaestro;
    private String nombres;
    private String apellidos;
    private Date fechaContratacion;
    private String carnetIdentidad;
    private int idMateria;

    public Maestro(String nombres, String apellidos, Date fechaContratacion, String carnetIdentidad, int idMateria) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaContratacion = fechaContratacion;
        this.carnetIdentidad = carnetIdentidad;
        this.idMateria = idMateria;
    }

    public Maestro() {
    }

    public int getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(int idMaestro) {
        this.idMaestro = idMaestro;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public void setCarnetIdentidad(String carnetIdentidad) {
        this.carnetIdentidad = carnetIdentidad;
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
                "\"idMaestro\":" + idMaestro +
                ", \"nombres\":\"" + nombres + "\"" +
                ", \"apellidos\":\"" + apellidos + "\"" +
                ", \"fechaContratacion\":\"" + fechaContratacion + "\"" +
                ", \"carnetIdentidad\":\"" + carnetIdentidad + "\"" +
                ", \"idMateria\":" + idMateria +
                '}';
    }
}
