package TheBigDev.modelo.dao;

public class FabricaDao {
    public static ArticuloDao creArticuloDao() {
        return new ArticuloDao();
    }

    public static ClientesDao creClientesDao() {
        return new ClientesDao();
    }

    public static PedidoDao crePedidoDao() {
        return new PedidoDao();
    }

    // public static DaoInterface crearDao(String nombre) {
    // switch (nombre) {
    // case "articulo":
    // return new ArticuloDao();
    // case "cliente":
    // return new ClientesDao();
    // case "pedido":
    // return new PedidoDao();
    // default:
    // throw new IllegalArgumentException("Tipo de operación no válido");
    // }
    // }

}
