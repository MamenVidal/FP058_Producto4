package TheBigDev.modelo.dao;

import TheBigDev.modelo.ListaArticulos;
import TheBigDev.modelo.ListaClientes;
import TheBigDev.modelo.Pedido;

import java.sql.*;
import java.util.ArrayList;

public class PedidoDao implements DaoInterface<Pedido, Integer> {

    private static String TABLE_NAME = "pedidos";

    private static Connection getConnection() {
        return TheBigDevConnection.getConnection();
    }

    // antigua funcion con sql directo a la tabla
    /*
    public void insert(Pedido pedido) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // template sql insert
                String query = "INSERT INTO $tableName (numero, cliente, articulo, cantidad, fechaHora, enviado) VALUES (?,?,?,?,?,?);"
                        .replace("$tableName", TABLE_NAME);
                // insert tablename from variable
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, pedido.getNumero());
                stmt.setString(2, pedido.getCliente().getEmail());
                stmt.setString(3, pedido.getArticulo().getCodigo());
                stmt.setInt(4, pedido.getCantidad());
                stmt.setTimestamp(5, Timestamp.valueOf(pedido.getFechaHora()));
                stmt.setBoolean(6, pedido.getEnviado());
                // And then do an executeUpdate
                stmt.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    */

    // nueva función usando el procedimiento almacenado
    public void insert(Pedido pedido) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // Cambiar a una llamada al procedimiento almacenado
                String query = "{CALL InsertarPedido(?,?,?,?,?,?)}";
                PreparedStatement stmt = conn.prepareCall(query);
                stmt.setInt(1, pedido.getNumero());
                stmt.setString(2, pedido.getCliente().getEmail());
                stmt.setString(3, pedido.getArticulo().getCodigo());
                stmt.setInt(4, pedido.getCantidad());
                stmt.setTimestamp(5, Timestamp.valueOf(pedido.getFechaHora()));
                stmt.setBoolean(6, pedido.getEnviado());
                // And then do an executeUpdate
                stmt.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void update(Pedido pedido) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // template sql update
                String query = "UPDATE $tableName SET cliente = ?, articulo = ?, cantidad = ?, fechaHora = ?, enviado = ? WHERE numero = ?;"
                        .replace("$tableName", TABLE_NAME);
                // set variables
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, pedido.getCliente().getEmail());
                stmt.setString(2, pedido.getArticulo().getCodigo());
                stmt.setInt(3, pedido.getCantidad());
                stmt.setTimestamp(4, Timestamp.valueOf(pedido.getFechaHora()));
                stmt.setBoolean(5, pedido.getEnviado());
                stmt.setInt(6, pedido.getNumero());
                // And then do an executeUpdate
                stmt.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // "update users set name = ?,email= ?, country =? where id = ?;";
    }

    public void delete(Pedido pedido) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // template sql update
                String query = "DELETE FROM $tableName WHERE numero = ?;".replace("$tableName", TABLE_NAME);
                // set variables
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, pedido.getNumero());
                // And then do an execute
                stmt.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Pedido read(Integer numero) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // template sql update
                String query = "SELECT * FROM $tableName WHERE numero = ?;".replace("$tableName", TABLE_NAME);
                // set variables
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, numero);
                // And then do an execute
                ResultSet rs = stmt.executeQuery();
                int columnCount = rs.getMetaData().getColumnCount();
                while (rs.next()) {
                    Pedido row = new Pedido(
                            rs.getInt(1),
                            ListaClientes.existeEmailCliente(rs.getString(2)),
                            ListaArticulos.existeCodigoArticulo(rs.getString(3)),
                            rs.getInt(4),
                            rs.getTimestamp(5).toLocalDateTime(),
                            rs.getBoolean(6));
                    return row;
                }
                return null;
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public ArrayList<Pedido> list() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // template sql update
                String query = "SELECT * FROM $tableName;".replace("$tableName", TABLE_NAME);
                // set variables
                PreparedStatement stmt = conn.prepareStatement(query);
                // And then do an execute
                ResultSet rs = stmt.executeQuery();
                int columnCount = rs.getMetaData().getColumnCount();
                ArrayList<Pedido> rows = new ArrayList<>();
                while (rs.next()) {
                    Pedido row = new Pedido(
                            rs.getInt(1),
                            ListaClientes.existeEmailCliente(rs.getString(2)),
                            ListaArticulos.existeCodigoArticulo(rs.getString(3)),
                            rs.getInt(4),
                            rs.getTimestamp(5).toLocalDateTime(),
                            rs.getBoolean(6));
                    rows.add(row);
                }
                return rows;
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
