package org.example.Dto;

public class Tutor {
    private int idTutor;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String carnetIdentidad;

    public Tutor(String nombres, String apellidos, String telefono, String carnetIdentidad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.carnetIdentidad = carnetIdentidad;
    }

    public Tutor() {
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCarnetIdentidad() {
        return carnetIdentidad;
    }

    public void setCarnetIdentidad(String carnetIdentidad) {
        this.carnetIdentidad = carnetIdentidad;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idTutor\":" + idTutor +
                ", \"nombres\":\"" + nombres + "\"" +
                ", \"apellidos\":\"" + apellidos + "\"" +
                ", \"telefono\":\"" + telefono + "\"" +
                ", \"Carnet de identidad\":\"" + carnetIdentidad + "\"" +
                '}';
    }
}