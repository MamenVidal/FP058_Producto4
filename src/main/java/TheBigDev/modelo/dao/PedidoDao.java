package TheBigDev.modelo.dao;

import TheBigDev.modelo.HibernateUtil;
import TheBigDev.modelo.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PedidoDao implements DaoInterface<Pedido, Integer> {

    private SessionFactory sessionFactory;

    public PedidoDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void insert(Pedido pedido) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(pedido);
            transaction.commit();
        }
    }
    public void update(Pedido pedido) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(pedido);
            transaction.commit();
        }
    }


    public void delete(Pedido pedido) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(pedido);
            transaction.commit();
        }
    }

    public Pedido read(Integer numero) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Pedido.class, numero);
        }
    }

    public List<Pedido> list() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Pedido", Pedido.class).list();
        }
    }

}
