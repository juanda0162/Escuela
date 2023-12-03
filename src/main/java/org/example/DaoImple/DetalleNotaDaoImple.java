package org.example.DaoImple;
import org.example.Dao.DetalleNotaDao;
import org.example.Dto.DetalleNota;
import org.example.GUI.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class DetalleNotaDaoImple extends DetalleNotaDao {
    @Override
    public int insert(DetalleNota obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "INSERT INTO detalle_notas (id_nota, valor, bimestre, fecha_registro) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, obj.getIdNota());
            stmt.setBigDecimal(2, obj.getValor());
            stmt.setInt(3, obj.getBimestre());
            stmt.setDate(4, obj.getFechaRegistro());

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
            throw new Exception("Error al insertar el detalle de nota en la base de datos");
        }

        return id;
    }

    @Override
    public DetalleNota update(DetalleNota obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "UPDATE detalle_notas SET id_nota=?, valor=?, bimestre=?, fecha_registro=? WHERE id_detalle_nota=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, obj.getIdNota());
            stmt.setBigDecimal(2, obj.getValor());
            stmt.setInt(3, obj.getBimestre());
            stmt.setDate(4, obj.getFechaRegistro());
            stmt.setInt(5, obj.getIdDetalleNota());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el detalle de nota en la base de datos");
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "DELETE FROM detalle_notas WHERE id_detalle_nota=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el detalle de nota en la base de datos");
        }
    }

    @Override
    public DetalleNota get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM detalle_notas WHERE id_detalle_nota=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                DetalleNota detalleNota = new DetalleNota();
                detalleNota.setIdDetalleNota(rs.getInt("id_detalle_nota"));
                detalleNota.setIdNota(rs.getInt("id_nota"));
                detalleNota.setValor(rs.getBigDecimal("valor"));
                detalleNota.setBimestre(rs.getInt("bimestre"));
                detalleNota.setFechaRegistro(rs.getDate("fecha_registro"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return detalleNota;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró el detalle de nota en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el detalle de nota de la base de datos");
        }
    }

    @Override
    public ArrayList<DetalleNota> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM detalle_notas";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<DetalleNota> detallesNotas = new ArrayList<>();
            while (rs.next()) {
                DetalleNota detalleNota = new DetalleNota();
                detalleNota.setIdDetalleNota(rs.getInt("id_detalle_nota"));
                detalleNota.setIdNota(rs.getInt("id_nota"));
                detalleNota.setValor(rs.getBigDecimal("valor"));
                detalleNota.setBimestre(rs.getInt("bimestre"));
                detalleNota.setFechaRegistro(rs.getDate("fecha_registro"));
                detallesNotas.add(detalleNota);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return detallesNotas;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de detalles de notas de la base de datos");
        }
    }
}
