package org.example.DaoImple;

import org.example.Dao.CursoMateriaDao;
import org.example.Dto.CursoMateria;
import org.example.GUI.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class CursoMateriaDaoImple extends CursoMateriaDao {
    @Override
    public int insert(CursoMateria obj) throws Exception {
        int idCurso = obj.getIdCurso();
        int idMateria = obj.getIdMateria();
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "INSERT INTO cursos_materias (id_curso, id_materia) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, idCurso);
            stmt.setInt(2, idMateria);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al insertar el curso-materia en la base de datos");
        }

        return idCurso; // Puedes retornar idCurso o idMateria, dependiendo de tus necesidades
    }

    @Override
    public void delete(int idCurso, int idMateria) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "DELETE FROM cursos_materias WHERE id_curso=? AND id_materia=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, idCurso);
            stmt.setInt(2, idMateria);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el curso-materia en la base de datos");
        }
    }

    @Override
    public ArrayList<CursoMateria> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM cursos_materias";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<CursoMateria> cursosMaterias = new ArrayList<>();
            while (rs.next()) {
                CursoMateria cursoMateria = new CursoMateria();
                cursoMateria.setIdCurso(rs.getInt("id_curso"));
                cursoMateria.setIdMateria(rs.getInt("id_materia"));
                cursosMaterias.add(cursoMateria);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return cursosMaterias;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de cursos-materias de la base de datos");
        }
    }
}
