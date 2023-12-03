package org.example.Dao;

import org.example.Dto.Tutor;

import java.util.ArrayList;

public abstract class TutorDao {
    public abstract int insert(Tutor obj) throws Exception;
    public abstract Tutor update(Tutor obj) throws Exception;
    public abstract void delete(int id) throws Exception;
    public abstract Tutor get(int id) throws Exception;
    public abstract ArrayList<Tutor> getList() throws Exception;
}
