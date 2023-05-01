package TheBigDev.modelo;

import TheBigDev.modelo.dao.FabricaDao;

import java.util.List;

public class ListaArticulos {

    public List<Articulo> getListaArticulos() {
        return FabricaDao.creArticuloDao().list();
    }

    public int getSize() {
        return getListaArticulos().size();
    }

    public void add(Articulo t) {
        FabricaDao.creArticuloDao().insert(t);
    }

    public void borrar(Articulo t) {
        FabricaDao.creArticuloDao().delete(t);
    }

    public Articulo getAt(int position) {
        return getListaArticulos().get(position);
    }

    public boolean isEmpty() {
        return getListaArticulos().isEmpty();
    }

    public List<Articulo> getArrayList() {
        return getListaArticulos();
    }

    public Articulo existeArticulo(Articulo articulo) {
        for (int i = 0; i < getListaArticulos().size(); i++) {
            Articulo a = getListaArticulos().get(i);
            if (a.getCodigo().equals(articulo.getCodigo())) {
                return a;
            }
        }
        return null;
    }

    public static Articulo existeCodigoArticulo(String codigo) {
        return FabricaDao.creArticuloDao().read(codigo);
    }
}