package org.example.Dao;

import org.example.Dto.DetalleNota;

import java.util.ArrayList;

public abstract class DetalleNotaDao {
    public abstract int insert(DetalleNota obj) throws Exception;
    public abstract DetalleNota update(DetalleNota obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract DetalleNota get(int id) throws Exception;
    public abstract ArrayList<DetalleNota> getList() throws Exception;
}
