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
            String query = "INSERT INTO maestros (nombres, apellidos, fecha_contratacion, carnet_identidad, id_materia) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getNombres());
            stmt.setString(2, obj.getApellidos());
            stmt.setDate(3, obj.getFechaContratacion());
            stmt.setString(4, obj.getCarnetIdentidad());
            stmt.setInt(5, obj.getIdMateria());

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
            throw new Exception("Error al insertar el maestro en la base de datos");
        }

        return id;
    }

    @Override
    public Maestro update(Maestro obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "UPDATE maestros SET nombres=?, apellidos=?, fecha_contratacion=?, carnet_identidad=?, id_materia=? WHERE id_maestro=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, obj.getNombres());
            stmt.setString(2, obj.getApellidos());
            stmt.setDate(3, obj.getFechaContratacion());
            stmt.setString(4, obj.getCarnetIdentidad());
            stmt.setInt(5, obj.getIdMateria());
            stmt.setInt(6, obj.getIdMaestro());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el maestro en la base de datos");
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "DELETE FROM maestros WHERE id_maestro=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
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
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Maestro maestro = new Maestro();
                maestro.setIdMaestro(rs.getInt("id_maestro"));
                maestro.setNombres(rs.getString("nombres"));
                maestro.setApellidos(rs.getString("apellidos"));
                maestro.setFechaContratacion(rs.getDate("fecha_contratacion"));
                maestro.setCarnetIdentidad(rs.getString("carnet_identidad"));
                maestro.setIdMateria(rs.getInt("id_materia"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return maestro;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró el maestro en la base de datos");
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
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Maestro> maestros = new ArrayList<>();
            while (rs.next()) {
                Maestro maestro = new Maestro();
                maestro.setIdMaestro(rs.getInt("id_maestro"));
                maestro.setNombres(rs.getString("nombres"));
                maestro.setApellidos(rs.getString("apellidos"));
                maestro.setFechaContratacion(rs.getDate("fecha_contratacion"));
                maestro.setCarnetIdentidad(rs.getString("carnet_identidad"));
                maestro.setIdMateria(rs.getInt("id_materia"));
                maestros.add(maestro);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return maestros;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de maestros de la base de datos");
        }
    }
}
