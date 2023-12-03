package org.example.Dto;

import java.math.BigDecimal;
import java.sql.Date;

public class Pago {
    private int idPago;
    private int idEstudiante;
    private String concepto;
    private BigDecimal monto;
    private Date fechaPago;

    public Pago(int idEstudiante, String concepto, BigDecimal monto, Date fechaPago) {
        this.idEstudiante = idEstudiante;
        this.concepto = concepto;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    public Pago() {
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "{" +
                "\"idPago\":" + idPago +
                ", \"idEstudiante\":" + idEstudiante +
                ", \"concepto\":\"" + concepto + "\"" +
                ", \"monto\":" + monto +
                ", \"fechaPago\":\"" + fechaPago + "\"" +
                '}';
    }
}
