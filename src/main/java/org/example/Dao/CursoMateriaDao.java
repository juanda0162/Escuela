package org.example.Dao;

import org.example.Dto.CursoMateria;

import java.util.ArrayList;

public abstract class CursoMateriaDao {
    public abstract int insert(CursoMateria obj) throws Exception;
    public abstract void delete(int idCurso, int idMateria) throws Exception;
    public abstract ArrayList<CursoMateria> getList() throws Exception;
}
