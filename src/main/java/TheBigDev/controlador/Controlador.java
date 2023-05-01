package TheBigDev.controlador;

import TheBigDev.modelo.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controlador {
    private Datos datos;

    public Controlador() {
        datos = new Datos();
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public void addArticulo(String codigo,String descripcion,float precioVenta,float gastoEnvio,int tiempo) {
        try {
            // creamos un Articulo
            Articulo articulo = new Articulo(codigo, descripcion, precioVenta, gastoEnvio, tiempo);
            // si el mismo código de articulo existe en ListaArticulos devolvermos un error
            if( datos.getListaArticulos().existeArticulo(articulo) != null ) {
                System.out.println(ANSI_RED_BACKGROUND+"ERROR: Ya existe un articulo añadido con codigo: "+articulo.getCodigo()+ANSI_RESET);
                return;
            }
            try {
                // añadimos el articulo a la lista
                datos.getListaArticulos().add(articulo);
            } catch (Exception e) {
                System.out.println(ANSI_RED_BACKGROUND+"ERROR: Se produjo un error al añadir el articulo a la lista de articulos: "+e+ANSI_RESET);
            }
            System.out.println(ANSI_GREEN_BACKGROUND+"EXITO: articulo añadido con codigo: "+articulo.getCodigo()+ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED_BACKGROUND+"ERROR: Se produjo el siguiente error al crear el articulo: "+e+ANSI_RESET);
        }
    }
    public void mostrarArticulos() {
        System.out.println(" - - - - - - - - ");
        List<Articulo> articulos = datos.getListaArticulos().getArrayList();
        for (int i = 0; i < articulos.size(); i++) {
            System.out.println(articulos.get(i).toString());
        }
        System.out.println(" - - - - - - - - ");
    }

    public Boolean esEmail(String email) {
        // patron ara considerar que es email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        // comprobamos si el email introducido cumple el patrón
        Matcher mather = pattern.matcher(email);
        if (mather.find() != true) {
            System.out.println(ANSI_RED_BACKGROUND+"El email ingresado no es válido: "+email+ANSI_RESET);
            return false;
        }
        return true;
    }
    public void addCliente(String email,String nif,String nombre,String domicilio, Boolean premium) {
        try {
            Cliente cliente;
            // creamos un Cliente según el tipo
            if( premium ) {
                cliente = new ClientePremium(email, nif, nombre, domicilio);
            } else {
                cliente = new ClienteEstandar(email, nif, nombre, domicilio);
            }
            // si existe un cliente con el mismo email devolvemos un error
            if( datos.getListaClientes().existeCliente(cliente) != null ) {
                System.out.println(ANSI_RED_BACKGROUND+"ERROR: Ya existe un cliente con email: "+cliente.getEmail()+ANSI_RESET);
                return;
            }
            try {
                // añadimos el cliente a la lista
                datos.getListaClientes().add(cliente);
            } catch (Exception e) {
                System.out.println(ANSI_RED_BACKGROUND+"ERROR: Se produjo un error al añadir el cliente a la lista de clientes: "+e+ANSI_RESET);
            }
            System.out.println(ANSI_GREEN_BACKGROUND+"EXITO: cliente añadido con email: "+cliente.getEmail()+ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED_BACKGROUND+"ERROR: Se produjo el siguiente error al crear el cliente: "+e+ANSI_RESET);
        }
    }
    public void mostrarClientes() {
        System.out.println(" - - - - - - - - ");
        List<Cliente> clientes = datos.getListaClientes().getArrayList();
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i).toString());
        }
        System.out.println(" - - - - - - - - ");
    }
    public void mostrarClientesEstandar() {
        System.out.println(" - - - - - - - - ");
        List<Cliente> clientes = datos.getListaClientes().getArrayList();
        for (int i = 0; i < clientes.size(); i++) {
            // sólo imprimimos el cliente si es estándar
            if( clientes.get(i).tipoCliente() == "estandar" ) {
                System.out.println(clientes.get(i).toString());
            }
        }
        System.out.println(" - - - - - - - - ");
    }
    public void mostrarClientesPremium() {
        System.out.println(" - - - - - - - - ");
        List<Cliente> clientes = datos.getListaClientes().getArrayList();
        for (int i = 0; i < clientes.size(); i++) {
            // sólo imprimimos el cliente si es premium
            if( clientes.get(i).tipoCliente() == "premium" ) {
                System.out.println(clientes.get(i).toString());
            }
        }
        System.out.println(" - - - - - - - - ");
    }
    public Boolean existeNumeroPedido(Integer numero) {
        if( datos.getListaPedidos().existeNumeroPedido(numero) == null ) {
            return false;
        }
        System.out.println(ANSI_RED_BACKGROUND+"Ya existe un pedido con numero: "+numero+ANSI_RESET);
        return true;
    }
    public Boolean existeEmailCliente(String email) {
        if( datos.getListaClientes().existeEmailCliente(email) == null ) {
            System.out.println(ANSI_RED_BACKGROUND+"No existe cliente con email: "+email+ANSI_RESET);
            return false;
        }
        return true;
    }

    public void addPedido(Integer numero,String email,String codigo,Integer cantidad,Boolean enviado) {
        try {
            // otenemos el cliente
            Cliente cliente = datos.getListaClientes().existeEmailCliente(email);
            if( cliente == null ) {
                System.out.println(ANSI_RED_BACKGROUND+"No existe cliente con email: "+email+ANSI_RESET);
                return;
            }
            // obtenemos el articulo
            Articulo articulo = datos.getListaArticulos().existeCodigoArticulo(codigo);
            if( articulo == null ) {
                System.out.println(ANSI_RED_BACKGROUND+"No existe articulo con codigo: "+codigo+ANSI_RESET);
                return;
            }
            // creamos el pedido
            LocalDateTime fechaHora = LocalDateTime.now();
            Pedido pedido = new Pedido(numero, cliente, articulo, cantidad, fechaHora, enviado);
            // si el mismo código de articulo existe en ListaArticulos devolvermos un error
            if( datos.getListaPedidos().existeNumeroPedido(pedido.getNumero()) != null ) {
                System.out.println(ANSI_RED_BACKGROUND+"ERROR: Ya existe un pedido añadido con numero: "+pedido.getNumero()+ANSI_RESET);
                return;
            }
            try {
                // añadimos el pedido a la lista de pedidos
                datos.getListaPedidos().add(pedido);
            } catch (Exception e) {
                System.out.println(ANSI_RED_BACKGROUND+"ERROR: Se produjo un error al añadir el pedido a la lista de pedidos: "+e+ANSI_RESET);
            }
            System.out.println(ANSI_GREEN_BACKGROUND+"EXITO: pedido añadido con numero: "+pedido.getNumero()+ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED_BACKGROUND+"ERROR: Se produjo el siguiente error al crear el pedido: "+e+ANSI_RESET);
        }
    }
    public void eliminarPedido(Integer numero) {
        try {
            Pedido pedido = datos.getListaPedidos().existeNumeroPedido(numero);
            if( pedido == null ) {
                System.out.println(ANSI_RED_BACKGROUND+"ERROR: no existe pedido con número: "+numero+ANSI_RESET);
                return;
            }
            if( pedido.pedidoEnviado() ) {
                System.out.println(ANSI_RED_BACKGROUND+"ERROR: pedido ya enviado: "+numero+ANSI_RESET);
                return;
            }
            LocalDateTime pedidoDate = pedido.getFechaHora();
            LocalDateTime now = LocalDateTime.now();
            // si la fecha actual es mayor que la fecha del pedido + 2 days
            if( now.isAfter(pedidoDate.plusDays(2)) ) {
                System.out.println(ANSI_RED_BACKGROUND+"ERROR: no se puede eliminar el pedido porque se realizado hace más de 2 días: "+ANSI_RESET);
                return;
            }
            datos.getListaPedidos().borrar(pedido);
            System.out.println(ANSI_GREEN_BACKGROUND+"EXITO: pedido borrado con numero: "+numero+ANSI_RESET);
        } catch (Exception e) {
            System.out.println(ANSI_RED_BACKGROUND+"ERROR: Se produjo el siguiente error al eliminar el pedido: "+e+ANSI_RESET);
        }
    }
    public void mostrarPedidosPendientesEnvio() {
        System.out.println(" - - - - - - - - ");
        List<Pedido> pedidos = datos.getListaPedidos().getArrayList();
        for (int i = 0; i < pedidos.size(); i++) {
            // sólo imprimimos el cliente si es estándar
            if( !pedidos.get(i).getEnviado() ) {
                System.out.println(pedidos.get(i).toString());
            }
        }
        System.out.println(" - - - - - - - - ");
    }
    public void mostrarPedidoEnviados() {
        System.out.println(" - - - - - - - - ");
        List<Pedido> pedidos = datos.getListaPedidos().getArrayList();
        for (int i = 0; i < pedidos.size(); i++) {
            // sólo imprimimos el cliente si es estándar
            if( pedidos.get(i).getEnviado() ) {
                System.out.println(pedidos.get(i).toString());
            }
        }
        System.out.println(" - - - - - - - - ");
    }
    public Boolean existeCodigoArticulo(String codigo) {
        if( datos.getListaArticulos().existeCodigoArticulo(codigo) == null ) {
            System.out.println(ANSI_RED_BACKGROUND+"No existe articulo con codigo: "+codigo+ANSI_RESET);
            return false;
        }
        return true;
    }
}
