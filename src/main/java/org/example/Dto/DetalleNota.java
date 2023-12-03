package org.example.Dto;
import java.math.BigDecimal;
import java.sql.Date;

public class DetalleNota {
    private int idDetalleNota;
    private int idNota;
    private BigDecimal valor;
    private int bimestre;
    private Date fechaRegistro;

    public DetalleNota(int idNota, BigDecimal valor, int bimestre, Date fechaRegistro) {
        this.idNota = idNota;
        this.valor = valor;
        this.bimestre = bimestre;
        this.fechaRegistro = fechaRegistro;
    }

    public DetalleNota() {
    }

    public int getIdDetalleNota() {
        return idDetalleNota;
    }

    public void setIdDetalleNota(int idDetalleNota) {
        this.idDetalleNota = idDetalleNota;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public int getBimestre() {
        return bimestre;
    }

    public void setBimestre(int bimestre) {
        this.bimestre = bimestre;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idDetalleNota\":" + idDetalleNota +
                ", \"idNota\":" + idNota +
                ", \"valor\":" + valor +
                ", \"bimestre\":" + bimestre +
                ", \"fechaRegistro\":" + fechaRegistro +
                '}';
    }
}
