package TheBigDev.modelo;

public class Datos {
    private ListaArticulos listaArticulos;
    private ListaClientes listaClientes;
    private ListaPedidos listaPedidos;

    public Datos (){
        listaArticulos = new ListaArticulos();
        listaClientes = new ListaClientes();
        listaPedidos = new ListaPedidos();
    }
    // TO-BE-DONE
    public ListaArticulos getListaArticulos() {
        return listaArticulos;
    }
    public ListaClientes getListaClientes() {
        return listaClientes;
    }
    public ListaPedidos getListaPedidos() {
        return listaPedidos;
    }
}