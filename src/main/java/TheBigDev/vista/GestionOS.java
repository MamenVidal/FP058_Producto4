package TheBigDev.vista;

import TheBigDev.controlador.Controlador;

import java.util.Locale;
import java.util.Scanner;

public class GestionOS {

    private final Controlador controlador;
    // añadimos el locale US para asegurar el mismo funcionamiento en todos los entornos que se ejecute, por ejemplo al introducir decimales
    Scanner teclado = new Scanner(System.in).useLocale(Locale.US);

    public GestionOS() {
        controlador = new Controlador();
    }

    public void inicio() {

        boolean salir = false;
        char opcion;

        do {
            System.out.println("1. Gestión Articulos");
            System.out.println("2. Gestión Clientes");
            System.out.println("3. Gestión Pedidos");
            System.out.println("0. Salir");
            opcion = pedirOpcion("1,2,3 o 0");
            switch (opcion) {
                case '1':
                    boolean subsalirArticulos = false;
                    char subopcionArticulos;
                    do {
                        System.out.println("1. Añadir Articulo");
                        System.out.println("2. Mostrar Articulos");
                        System.out.println("0. Salir");
                        subopcionArticulos = pedirOpcion("1,2 o 0");
                        switch (subopcionArticulos) {
                            case '1':
                                vistaAnadirArticulo();
                                break;
                            case '2':
                                vistaMostrarArticulos();
                                break;
                            case '0':
                                subsalirArticulos = true;
                                break;
                        }
                    } while (!subsalirArticulos);
                    break;
                case '2':
                    boolean subsalirClientes = false;
                    char subopcionClientes;
                    do {
                        System.out.println("1. Añadir Cliente");
                        System.out.println("2. Mostrar Clientes");
                        System.out.println("3. Mostrar Clientes Estándar");
                        System.out.println("4. Mostrar Clientes Premium");
                        System.out.println("0. Salir");
                        subopcionClientes = pedirOpcion("1,2,3,4 o 0");
                        switch (subopcionClientes) {
                            case '1':
                                vistaAnadirCliente();
                                break;
                            case '2':
                                vistaMostrarClientes();
                                break;
                            case '3':
                                vistaMostrarClientesEstandar();
                                break;
                            case '4':
                                vistaMostrarClientesPremium();
                                break;
                            case '0':
                                subsalirClientes = true;
                                break;
                        }
                    } while (!subsalirClientes);
                    break;
                case '3':
                    boolean subsalirPedidos = false;
                    char subopcionPedidos;
                    do {
                        System.out.println("1. Añadir Pedido");
                        System.out.println("2. Eliminar Pedido");
                        System.out.println("3. Mostrar Pedidos Pendientes Envío");
                        System.out.println("4. Mostrar Pedidos Enviados");
                        System.out.println("0. Salir");
                        subopcionPedidos = pedirOpcion("1,2,3,4 o 0");
                        switch (subopcionPedidos) {
                            case '1':
                                vistaAnadirPedido();
                                break;
                            case '2':
                                vistaEliminarPedido();
                                break;
                            case '3':
                                vistaMostrarPedidosPendientesEnvio();
                                break;
                            case '4':
                                vistaMostrarPedidoEnviados();
                                break;
                            case '0':
                                subsalirPedidos = true;
                                break;
                        }
                    } while (!subsalirPedidos);
                    break;
                case '0':
                    salir = true;
            }
        } while (!salir);
    }

    public void vistaAnadirArticulo() {
        System.out.println("Inserte codigo producto a crear");
        String codigo = teclado.next();
        teclado.nextLine();
        System.out.println("Inserte descripcion");
        String descripcion = teclado.nextLine();
        System.out.println("Inserte precioVenta (ej: 1050.32)");
        float precioVenta = teclado.nextFloat();
        teclado.nextLine();
        System.out.println("Inserte gastoEnvio (ej: 5.50)");
        float gastoEnvio = teclado.nextFloat();
        teclado.nextLine();
        System.out.println("Inserte tiempo preparación para el envio (en minutos, ej: 2)");
        int tiempo = teclado.nextInt();
        teclado.nextLine();
        controlador.addArticulo(codigo,descripcion,precioVenta,gastoEnvio,tiempo);
    }
    public void vistaMostrarArticulos() {
        controlador.mostrarArticulos();
    }

    public void vistaAnadirCliente() {
        System.out.println("Inserte email");
        String email = teclado.next();
        if( controlador.esEmail(email) ) {
            teclado.nextLine();
            System.out.println("Inserte nif");
            String nif = teclado.next();
            teclado.nextLine();
            System.out.println("Inserte nombre");
            String nombre = teclado.nextLine();
            System.out.println("Inserte domicilio");
            String domicilio = teclado.nextLine();
            System.out.println("¿Es premium? (true = Premium, false = Estándar)");
            Boolean premium = teclado.nextBoolean();
            teclado.nextLine();
            System.out.println(premium);
            controlador.addCliente(email,nif,nombre,domicilio, premium);
        }
    }
    public void vistaMostrarClientes() {
        controlador.mostrarClientes();
    }
    public void vistaMostrarClientesEstandar() {
        controlador.mostrarClientesEstandar();
    }
    public void vistaMostrarClientesPremium() {
        controlador.mostrarClientesPremium();
    }

    public void vistaAnadirPedido() {
        System.out.println("Inserte número pedido");
        Integer numero = teclado.nextInt();
        teclado.nextLine();
        if( controlador.existeNumeroPedido(numero) ) {
            return;
        }
        System.out.println("Inserte email cliente para asignar a pedido");
        String email = teclado.next();
        teclado.nextLine();
        if( !controlador.esEmail(email) ) {
            return;
        }
        if( !controlador.existeEmailCliente(email) ) {
            vistaAnadirClientePedido(email);
        }
        System.out.println("Inserte codigo articulo para asignar a pedido");
        String codigo = teclado.next();
        teclado.nextLine();
        if( !controlador.existeCodigoArticulo(codigo) ) {
            vistaAnadirArticuloPedido(codigo);
        }
        System.out.println("Inserte cantidad");
        Integer cantidad = teclado.nextInt();
        teclado.nextLine();
        System.out.println("¿Esta enviado? (true = Sí, false = No)");
        Boolean enviado = teclado.nextBoolean();
        teclado.nextLine();
        controlador.addPedido(numero,email,codigo,cantidad,enviado);
    }
    public void vistaAnadirClientePedido(String email) {
        System.out.println("==== Creamos cliente");
        System.out.println("Inserte nif");
        String nif = teclado.next();
        teclado.nextLine();
        System.out.println("Inserte nombre");
        String nombre = teclado.nextLine();
        System.out.println("Inserte domicilio");
        String domicilio = teclado.nextLine();
        System.out.println("¿Es premium? (true = Premium, false = Estándar)");
        Boolean premium = teclado.nextBoolean();
        System.out.println(premium);
        teclado.nextLine();
        controlador.addCliente(email,nif,nombre,domicilio, premium);
    }
    public void vistaAnadirArticuloPedido(String codigo) {
        System.out.println("==== Creamos articulo");
        System.out.println("Inserte descripcion");
        String descripcion = teclado.nextLine();
        teclado.nextLine();
        System.out.println("Inserte precioVenta (ej: 1050.32)");
        float precioVenta = teclado.nextFloat();
        teclado.nextLine();
        System.out.println("Inserte gastoEnvio (ej: 5.50)");
        float gastoEnvio = teclado.nextFloat();
        teclado.nextLine();
        System.out.println("Inserte tiempo preparación para el envio (en minutos, ej: 2)");
        int tiempo = teclado.nextInt();
        teclado.nextLine();
        controlador.addArticulo(codigo,descripcion,precioVenta,gastoEnvio,tiempo);
    }
    public void vistaEliminarPedido() {
        System.out.println("Inserte número pedido a eliminar");
        Integer numero = teclado.nextInt();
        teclado.nextLine();
        controlador.eliminarPedido(numero);
    }
    public void vistaMostrarPedidosPendientesEnvio() {
        controlador.mostrarPedidosPendientesEnvio();
    }
    public void vistaMostrarPedidoEnviados() {
        controlador.mostrarPedidoEnviados();
    }

    // modificamos la función para pasarle en una variable el texto de las opciones y así poder reutilizar la misma función para los submenús
    char pedirOpcion(String textoOpciones) {
        String resp;
        System.out.println("Elige una opción ("+textoOpciones+"):");
        resp = teclado.next();
        teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);
    }
}
