package TheBigDev.modelo;

import TheBigDev.modelo.dao.FabricaDao;

import java.util.List;

public class ListaClientes {

    public List<Cliente> getListaClientes() {
        return FabricaDao.creClientesDao().list();
    }

    public int getSize() {
        return getListaClientes().size();
    }

    public void add(Cliente t) {
        FabricaDao.creClientesDao().insert(t);
    }

    public void borrar(Cliente t) {
        FabricaDao.creClientesDao().delete(t);
    }

    public Cliente getAt(int position) {
        return getListaClientes().get(position);
    }

    public boolean isEmpty() {
        return getListaClientes().isEmpty();
    }

    public List<Cliente> getArrayList() {
        return getListaClientes();
    }

    public Cliente existeCliente(Cliente cliente) {
        for (int i = 0; i < getListaClientes().size(); i++) {
            Cliente c = getListaClientes().get(i);
            if (c.getEmail().equals(cliente.getEmail())) {
                return c;
            }
        }
        return null;
    }

    public static Cliente existeEmailCliente(String email) {
        return FabricaDao.creClientesDao().read(email);
    }
}