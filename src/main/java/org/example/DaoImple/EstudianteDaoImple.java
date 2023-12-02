package org.example.DaoImple;

import org.example.Dao.EstudianteDao;
import org.example.Dto.Estudiante;
import org.example.GUI.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EstudianteDaoImple extends EstudianteDao {
    @Override
    public int insert(Estudiante obj) throws Exception {
        int id = 0;
        String matricula = null;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "INSERT INTO public.estudiantes (nombres, apellidos, fecha_nacimiento, carnet_identidad) VALUES (?, ?, ?, ?) RETURNING id_estudiante, matricula";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, obj.getNombres());
                stmt.setString(2, obj.getApellidos());
                stmt.setDate(3, new java.sql.Date(obj.getFechaNacimiento().getTime()));
                stmt.setString(4, obj.getCarnetIdentidad());

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("id_estudiante");
                        matricula = rs.getString("matricula");
                    } else {
                        throw new Exception("Error al obtener el id_estudiante y la matrícula generados");
                    }
                }
            }

            obj.setMatricula(matricula); // Asignar la matrícula al objeto Estudiante
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
            String query = "UPDATE estudiantes SET nombres=?, apellidos=? WHERE id_estudiante=?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(3, obj.getIdEstudiante());
                stmt.setString(1, obj.getNombres());
                stmt.setString(2, obj.getApellidos());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("El estudiante no existe en la base de datos");
                }
            }
            return get(obj.getIdEstudiante()); // Devolver el objeto actualizado
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el estudiante en la base de datos");
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "DELETE FROM estudiantes WHERE id_estudiante=?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
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
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Estudiante estudiante = new Estudiante();
                        estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                        estudiante.setNombres(rs.getString("nombres"));
                        estudiante.setApellidos(rs.getString("apellidos"));
                        return estudiante;
                    } else {
                        throw new Exception("No se encontró el estudiante en la base de datos");
                    }
                }
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
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    ArrayList<Estudiante> estudiantes = new ArrayList<>();
                    while (rs.next()) {
                        Estudiante estudiante = new Estudiante();
                        estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                        estudiante.setNombres(rs.getString("nombres"));
                        estudiante.setApellidos(rs.getString("apellidos"));
                        estudiantes.add(estudiante);
                    }
                    return estudiantes;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de estudiantes de la base de datos");
        }
    }
}
