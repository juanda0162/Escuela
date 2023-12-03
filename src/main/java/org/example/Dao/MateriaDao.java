package org.example.Dao;

import org.example.Dto.Materia;

import java.util.ArrayList;

public abstract class MateriaDao {
    public abstract int insert(Materia obj) throws Exception;
    public abstract Materia update(Materia obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Materia get(int id) throws Exception;
    public abstract ArrayList<Materia> getList() throws Exception;
}
