package org.example.Dto;

public class Materia {
    private int idMateria;
    private String nombre;
    private String descripcion;

    public Materia(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Materia() {
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idMateria\":" + idMateria +
                ", \"nombre\":\"" + nombre + "\"" +
                ", \"descripcion\":\"" + descripcion + "\"" +
                '}';
    }
}
