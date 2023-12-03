package org.example.DaoImple;
import org.example.Dao.NotaDao;
import org.example.Dto.Nota;
import org.example.GUI.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class NotaDaoImple extends NotaDao {
    @Override
    public int insert(Nota obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "INSERT INTO notas (id_estudiante, id_curso, periodo, nota) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, obj.getIdEstudiante());
            stmt.setInt(2, obj.getIdCurso());
            stmt.setInt(3, obj.getPeriodo());
            stmt.setBigDecimal(4, obj.getNota());

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
            throw new Exception("Error al insertar la nota en la base de datos");
        }

        return id;
    }

    @Override
    public Nota update(Nota obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "UPDATE notas SET id_estudiante=?, id_curso=?, periodo=?, nota=? WHERE id_nota=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, obj.getIdEstudiante());
            stmt.setInt(2, obj.getIdCurso());
            stmt.setInt(3, obj.getPeriodo());
            stmt.setBigDecimal(4, obj.getNota());
            stmt.setInt(5, obj.getIdNota());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar la nota en la base de datos");
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "DELETE FROM notas WHERE id_nota=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la nota en la base de datos");
        }
    }

    @Override
    public Nota get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM notas WHERE id_nota=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Nota nota = new Nota();
                nota.setIdNota(rs.getInt("id_nota"));
                nota.setIdEstudiante(rs.getInt("id_estudiante"));
                nota.setIdCurso(rs.getInt("id_curso"));
                nota.setPeriodo(rs.getInt("periodo"));
                nota.setNota(rs.getBigDecimal("nota"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return nota;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró la nota en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la nota de la base de datos");
        }
    }

    @Override
    public ArrayList<Nota> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM notas";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Nota> notas = new ArrayList<>();
            while (rs.next()) {
                Nota nota = new Nota();
                nota.setIdNota(rs.getInt("id_nota"));
                nota.setIdEstudiante(rs.getInt("id_estudiante"));
                nota.setIdCurso(rs.getInt("id_curso"));
                nota.setPeriodo(rs.getInt("periodo"));
                nota.setNota(rs.getBigDecimal("nota"));
                notas.add(nota);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return notas;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de notas de la base de datos");
        }
    }
}
