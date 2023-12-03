package org.example.Dao;


import org.example.Dto.Nota;

import java.util.ArrayList;

public abstract class NotaDao {
    public abstract int insert(Nota obj) throws Exception;
    public abstract Nota update(Nota obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Nota get(int id) throws Exception;
    public abstract ArrayList<Nota> getList() throws Exception;
}
