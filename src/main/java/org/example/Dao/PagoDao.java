package org.example.Dao;

import org.example.Dto.Pago;

import java.util.ArrayList;

public abstract class PagoDao {
    public abstract int insert(Pago obj) throws Exception;
    public abstract Pago update(Pago obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Pago get(int id) throws Exception;
    public abstract ArrayList<Pago> getList() throws Exception;
}
