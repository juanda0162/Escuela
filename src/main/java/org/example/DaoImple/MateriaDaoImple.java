package org.example.DaoImple;

import org.example.Dao.MateriaDao;
import org.example.Dto.Materia;
import org.example.GUI.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class MateriaDaoImple extends MateriaDao {
    @Override
    public int insert(Materia obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "INSERT INTO materias (nombre_materia, descripcion) VALUES (?,?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getNombre());
            stmt.setString(2, obj.getDescripcion());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            if (id == 0) {
                throw new Exception("El registro no pudo ser insertado");
            }

            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar la materia en la base de datos");
        }

        return id;
    }

    @Override
    public Materia update(Materia obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "UPDATE materias SET nombre_materia=? WHERE id_materia=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(2, obj.getIdMateria());
            stmt.setString(1, obj.getNombre());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la materia en la base de datos");
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "DELETE FROM materias WHERE id_materia=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la materia en la base de datos");
        }
    }

    @Override
    public Materia get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM materias WHERE id_materia=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre_materia"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return materia;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró la materia en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la materia de la base de datos");
        }
    }

    @Override
    public ArrayList<Materia> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM materias";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Materia> materias = new ArrayList<>();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre_materia"));
                materias.add(materia);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return materias;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de materias de la base de datos");
        }
    }
}
