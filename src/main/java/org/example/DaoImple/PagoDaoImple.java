package org.example.DaoImple;
import org.example.Dao.PagoDao;
import org.example.Dto.Pago;
import org.example.GUI.Conexion;

import java.sql.*;
import java.util.ArrayList;

public class PagoDaoImple extends PagoDao {
    @Override
    public int insert(Pago obj) throws Exception {
        int id = 0;
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "INSERT INTO pagos (id_estudiante, concepto, monto, fecha_pago) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, obj.getIdEstudiante());
            stmt.setString(2, obj.getConcepto());
            stmt.setBigDecimal(3, obj.getMonto());
            stmt.setDate(4, obj.getFechaPago());

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
            throw new Exception("Error al insertar el pago en la base de datos");
        }

        return id;
    }

    @Override
    public Pago update(Pago obj) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "UPDATE pagos SET id_estudiante=?, concepto=?, monto=?, fecha_pago=? WHERE id_pago=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, obj.getIdEstudiante());
            stmt.setString(2, obj.getConcepto());
            stmt.setBigDecimal(3, obj.getMonto());
            stmt.setDate(4, obj.getFechaPago());
            stmt.setInt(5, obj.getIdPago());

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el pago en la base de datos");
        }
        return null;
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "DELETE FROM pagos WHERE id_pago=?";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            objConexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el pago en la base de datos");
        }
    }

    @Override
    public Pago get(int id) throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM pagos WHERE id_pago=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdEstudiante(rs.getInt("id_estudiante"));
                pago.setConcepto(rs.getString("concepto"));
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setFechaPago(rs.getDate("fecha_pago"));
                rs.close();
                stmt.close();
                objConexion.desconectar();
                return pago;
            } else {
                rs.close();
                stmt.close();
                objConexion.desconectar();
                throw new Exception("No se encontró el pago en la base de datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener el pago de la base de datos");
        }
    }

    @Override
    public ArrayList<Pago> getList() throws Exception {
        try {
            Conexion objConexion = Conexion.getOrCreate();
            Connection conn = objConexion.conectarPostgreSQL();
            String query = "SELECT * FROM pagos";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Pago> pagos = new ArrayList<>();
            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdEstudiante(rs.getInt("id_estudiante"));
                pago.setConcepto(rs.getString("concepto"));
                pago.setMonto(rs.getBigDecimal("monto"));
                pago.setFechaPago(rs.getDate("fecha_pago"));
                pagos.add(pago);
            }

            rs.close();
            stmt.close();
            objConexion.desconectar();
            return pagos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la lista de pagos de la base de datos");
        }
    }
}
