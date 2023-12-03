package org.example.DaoImple;
import org.example.Dao.EstudianteDao;
import org.example.Dto.Estudiante;
import org.example.GUI.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class EstudianteDaoImple extends EstudianteDao {
    @Override
    public int insert(Estudiante obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "INSERT INTO estudiantes (nombres, apellidos, fecha_nacimiento, carnet_identidad, id_tutor, id_curso) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, obj.getNombres());
            stmt.setString(2, obj.getApellidos());
            stmt.setDate(3, obj.getFechaNacimiento());
            stmt.setString(4, obj.getCarnetIdentidad());
            stmt.setInt(5, obj.getIdTutor());
            stmt.setInt(6, obj.getIdCurso());

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
            throw new Exception("Error al insertar el estudiante en la base de datos");
        }

        return id;
    }

    @Override
    public Estudiante update(Estudiante obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "UPDATE estudiantes SET nombres=?, apellidos=?, fecha_nacimiento=?, carnet_identidad=?, id_tutor=?, id_curso=? WHERE id_estudiante=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, obj.getNombres());
            stmt.setString(2, obj.getApellidos());
            stmt.setDate(3, obj.getFechaNacimiento());
            stmt.setString(4, obj.getCarnetIdentidad());
            stmt.setInt(5, obj.getIdTutor());
            stmt.setInt(6, obj.getIdCurso());
            stmt.setInt(7, obj.getIdEstudiante());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el estudiante en la base de datos");
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "DELETE FROM estudiantes WHERE id_estudiante=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el estudiante en la base de datos");
        }
    }

    @Override
    public Estudiante get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM estudiantes WHERE id_estudiante=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                estudiante.setCarnetIdentidad(rs.getString("carnet_identidad"));
                estudiante.setIdTutor(rs.getInt("id_tutor"));
                estudiante.setIdCurso(rs.getInt("id_curso"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return estudiante;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró el estudiante en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el estudiante de la base de datos");
        }
    }

    @Override
    public ArrayList<Estudiante> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM estudiantes";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Estudiante> estudiantes = new ArrayList<>();
            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                estudiante.setCarnetIdentidad(rs.getString("carnet_identidad"));
                estudiante.setIdTutor(rs.getInt("id_tutor"));
                estudiante.setIdCurso(rs.getInt("id_curso"));
                estudiantes.add(estudiante);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return estudiantes;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de estudiantes de la base de datos");
        }
    }
}
