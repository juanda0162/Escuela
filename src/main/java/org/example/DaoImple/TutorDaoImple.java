package org.example.DaoImple;

import org.example.Dao.TutorDao;
import org.example.Dto.Tutor;
import org.example.GUI.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class TutorDaoImple extends TutorDao {
    @Override
    public int insert(Tutor obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "INSERT INTO tutores (nombres, apellidos, telefono, carnet_identidad) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getNombres());
            stmt.setString(2, obj.getApellidos());
            stmt.setString(3, obj.getTelefono());
            stmt.setString(4, obj.getCarnetIdentidad());

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
            throw new Exception("Error al insertar el tutor en la base de datos");
        }

        return id;
    }

    @Override
    public Tutor update(Tutor obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "UPDATE tutores SET nombres=?, apellidos=?, telefono=?, carnet_identidad=? WHERE id_tutor=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, obj.getNombres());
            stmt.setString(2, obj.getApellidos());
            stmt.setString(3, obj.getTelefono());
            stmt.setString(4, obj.getCarnetIdentidad());
            stmt.setInt(5, obj.getIdTutor());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el tutor en la base de datos");
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "DELETE FROM tutores WHERE id_tutor=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el tutor en la base de datos");
        }
    }

    @Override
    public Tutor get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM tutores WHERE id_tutor=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Tutor tutor = new Tutor();
                tutor.setIdTutor(rs.getInt("id_tutor"));
                tutor.setNombres(rs.getString("nombres"));
                tutor.setApellidos(rs.getString("apellidos"));
                tutor.setTelefono(rs.getString("telefono"));
                tutor.setCarnetIdentidad(rs.getString("carnet_identidad"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return tutor;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró el tutor en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el tutor de la base de datos");
        }
    }

    @Override
    public ArrayList<Tutor> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM tutores";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Tutor> tutores = new ArrayList<>();
            while (rs.next()) {
                Tutor tutor = new Tutor();
                tutor.setIdTutor(rs.getInt("id_tutor"));
                tutor.setNombres(rs.getString("nombres"));
                tutor.setApellidos(rs.getString("apellidos"));
                tutor.setTelefono(rs.getString("telefono"));
                tutor.setCarnetIdentidad(rs.getString("carnet_identidad"));
                tutores.add(tutor);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return tutores;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de tutores de la base de datos");
        }
    }
}
