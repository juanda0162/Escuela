package org.example.Dao;

import org.example.Dto.Maestro;

import java.util.ArrayList;

public abstract class MaestroDao {
    public abstract int insert(Maestro obj) throws Exception;
    public abstract Maestro update(Maestro obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Maestro get(int id) throws Exception;
    public abstract ArrayList<Maestro> getList() throws Exception;
}
