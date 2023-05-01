package TheBigDev.modelo.dao;

import TheBigDev.modelo.Cliente;
import TheBigDev.modelo.ClienteEstandar;
import TheBigDev.modelo.ClientePremium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientesDao implements DaoInterface<Cliente, String> {

    private static String TABLE_NAME = "clientes";

    private static Connection getConnection() {
        return TheBigDevConnection.getConnection();
    }

    public void insert(Cliente cliente) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // Deshabilitar el modo de confirmación automática
                conn.setAutoCommit(false);

                // template sql insert
                String query = "INSERT INTO $tableName (email, nif, nombre, domicilio, tipoCliente, calcAnual, descuentoEnv) VALUES (?,?,?,?,?,?,?);"
                        .replace("$tableName", TABLE_NAME);
                // insert tablename from variable
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, cliente.getEmail());
                stmt.setString(2, cliente.getNif());
                stmt.setString(3, cliente.getNombre());
                stmt.setString(4, cliente.getDomicilio());
                stmt.setString(5, cliente.tipoCliente());
                stmt.setFloat(6, cliente.calcAnual());
                stmt.setFloat(7, cliente.descuentoEnv());
                // And then do an executeUpdate
                stmt.executeUpdate();

                // Realizar el commit
                conn.commit();
            } catch (Exception e) {
                try {
                    // Realizar el rollback en caso de errores
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
                throw new RuntimeException(e);
            } finally {
                try {
                    // Reestablecer el modo de confirmación automática antes de cerrar la conexión
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void update(Cliente cliente) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // Deshabilitar el modo de confirmación automática
                conn.setAutoCommit(false);

                // template sql update
                String query = "UPDATE $tableName SET nif = ?, nombre = ?, domicilio = ?, tipoCliente = ?, calcAnual = ?, descuentoEnv = ? WHERE email = ?;"
                        .replace("$tableName", TABLE_NAME);
                // set variables
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, cliente.getNif());
                stmt.setString(2, cliente.getNombre());
                stmt.setString(3, cliente.getDomicilio());
                stmt.setString(4, cliente.tipoCliente());
                stmt.setFloat(5, cliente.calcAnual());
                stmt.setFloat(6, cliente.descuentoEnv());
                stmt.setString(7, cliente.getEmail());
                // And then do an executeUpdate
                stmt.executeUpdate();

                // Realizar el commit
                conn.commit();
            } catch (Exception e) {
                try {
                    // Realizar el rollback en caso de errores
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
                throw new RuntimeException(e);
            } finally {
                try {
                    // Reestablecer el modo de confirmación automática antes de cerrar la conexión
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void delete(Cliente cliente) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // template sql update
                String query = "DELETE FROM $tableName WHERE email = ?;".replace("$tableName", TABLE_NAME);
                // set variables
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, cliente.getEmail());
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

    public Cliente read(String email) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // template sql update
                String query = "SELECT * FROM $tableName WHERE email = ?;".replace("$tableName", TABLE_NAME);
                // set variables
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, email);
                // And then do an execute
                ResultSet rs = stmt.executeQuery();
                return readRow(rs);
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

    public ArrayList<Cliente> list() {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                // template sql update
                String query = "SELECT * FROM $tableName;".replace("$tableName", TABLE_NAME);
                // set variables
                PreparedStatement stmt = conn.prepareStatement(query);
                // And then do an execute
                ResultSet rs = stmt.executeQuery();
                return readRows(rs);
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

    private ArrayList<Cliente> readRows(ResultSet rs) throws SQLException {
        int columnCount = rs.getMetaData().getColumnCount();
        ArrayList<Cliente> rows = new ArrayList<Cliente>();
        while (rs.next()) {
            String tipoCliente = rs.getString(5);
            if (tipoCliente.equals("premium")) {
                Cliente row = new ClientePremium(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                rows.add(row);
            } else {
                Cliente row = new ClienteEstandar(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                rows.add(row);
            }
        }
        return rows;
    }

    private Cliente readRow(ResultSet rs) throws SQLException {
        int columnCount = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            if (rs.getString(5) == "premium") {
                Cliente row = new ClientePremium(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                return row;
            } else {
                Cliente row = new ClienteEstandar(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
                return row;
            }
        }
        return null;
    }

}
