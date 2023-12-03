package org.example.Dao;


import org.example.Dto.Curso;

import java.util.ArrayList;

public abstract class CursoDao {
    public abstract int insert(Curso obj) throws Exception;
    public abstract Curso update(Curso obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Curso get(int id) throws Exception;
    public abstract ArrayList<Curso> getList() throws Exception;
}
