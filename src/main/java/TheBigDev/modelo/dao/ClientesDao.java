package TheBigDev.modelo.dao;

import TheBigDev.modelo.Cliente;
import TheBigDev.modelo.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ClientesDao implements DaoInterface<Cliente, String> {

    private SessionFactory sessionFactory;

    public ClientesDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void insert(Cliente cliente) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(cliente);
            transaction.commit();
        }
    }
    public void update(Cliente cliente) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(cliente);
            transaction.commit();
        }
    }


    public void delete(Cliente cliente) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(cliente);
            transaction.commit();
        }
    }

    public Cliente read(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Cliente.class, email);
        }
    }

    public List<Cliente> list() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Cliente", Cliente.class).list();
        }
    }

}
