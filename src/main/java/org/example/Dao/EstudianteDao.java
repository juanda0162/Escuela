package org.example.Dao;


import org.example.Dto.Estudiante;

import java.util.ArrayList;

public abstract class EstudianteDao {
    public abstract int insert(Estudiante obj) throws Exception;
    public abstract Estudiante update(Estudiante obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Estudiante get(int id) throws Exception;
    public abstract ArrayList<Estudiante> getList() throws Exception;
}
