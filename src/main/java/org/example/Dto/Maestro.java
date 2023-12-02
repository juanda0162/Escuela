package org.example.Dto;

public class Maestro {
    private int idMaestro;
    private String nombres;
    private String apellidos;
    private String fechaContratacion;

    public Maestro() {
    }

    public Maestro(String nombres, String apellidos, String fechaContratacion) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaContratacion = fechaContratacion;
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

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return "Maestro{" +
                "idMaestro=" + idMaestro +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fechaContratacion='" + fechaContratacion + '\'' +
                '}';
    }
}
