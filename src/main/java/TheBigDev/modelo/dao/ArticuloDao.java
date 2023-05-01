package TheBigDev.modelo.dao;

import TheBigDev.modelo.Articulo;
import TheBigDev.modelo.HibernateUtil;
import org.hibernate.*;

import java.util.List;


public class ArticuloDao implements DaoInterface<Articulo, String> {

    private SessionFactory sessionFactory;

    public ArticuloDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void insert(Articulo articulo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(articulo);
            transaction.commit();
        }
    }

    public void update(Articulo articulo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(articulo);
            transaction.commit();
        }
    }

    public void delete(Articulo articulo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(articulo);
            transaction.commit();
        }
    }

    public Articulo read(String codigo) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Articulo.class, codigo);
        }
    }

    public List<Articulo> list() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Articulo", Articulo.class).list();
        }
    }

}
