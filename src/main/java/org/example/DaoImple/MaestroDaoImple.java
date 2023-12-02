package org.example.DaoImple;

import org.example.Dao.MaestroDao;
import org.example.Dto.Maestro;
import org.example.GUI.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class MaestroDaoImple extends MaestroDao {
    @Override
    public int insert(Maestro obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "INSERT INTO maestros (nombres, apellidos, fecha_contratacion) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, obj.getNombres());
                stmt.setString(2, obj.getApellidos());
                stmt.setString(3, obj.getFechaContratacion());

                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }

                if (id == 0) {
                    throw new Exception("El maestro no pudo ser insertado");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar el maestro en la base de datos");
        }

        return id;
    }

    @Override
    public Maestro update(Maestro obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "UPDATE maestros SET nombres=?, apellidos=?, fecha_contratacion=? WHERE id_maestro=?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(4, obj.getIdMaestro());
                stmt.setString(1, obj.getNombres());
                stmt.setString(2, obj.getApellidos());
                stmt.setString(3, obj.getFechaContratacion());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("El maestro no existe en la base de datos");
                }
            }
            return get(obj.getIdMaestro()); // Devolver el objeto actualizado
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el maestro en la base de datos");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "DELETE FROM maestros WHERE id_maestro=?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el maestro en la base de datos");
        }
    }

    @Override
    public Maestro get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM maestros WHERE id_maestro=?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Maestro maestro = new Maestro();
                        maestro.setIdMaestro(rs.getInt("id_maestro"));
                        maestro.setNombres(rs.getString("nombres"));
                        maestro.setApellidos(rs.getString("apellidos"));
                        maestro.setFechaContratacion(rs.getString("fecha_contratacion"));
                        return maestro;
                    } else {
                        throw new Exception("No se encontró el maestro en la base de datos");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el maestro de la base de datos");
        }
    }

    @Override
    public ArrayList<Maestro> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM maestros";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    ArrayList<Maestro> maestros = new ArrayList<>();
                    while (rs.next()) {
                        Maestro maestro = new Maestro();
                        maestro.setIdMaestro(rs.getInt("id_maestro"));
                        maestro.setNombres(rs.getString("nombres"));
                        maestro.setApellidos(rs.getString("apellidos"));
                        maestro.setFechaContratacion(rs.getString("fecha_contratacion"));
                        maestros.add(maestro);
                    }
                    return maestros;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de maestros de la base de datos");
        }
    }
}
