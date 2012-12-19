/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.utils.session;

/**
 *
 * @author ddsurok
 */
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import ru.ddsurok.datamodel.Session;
import ru.ddsurok.datamodel.User;
import ru.ddsurok.utils.HibernateUtil;

public class SessionUtil implements ISessionUtil, Serializable {

    private org.hibernate.Session session = null;

    public SessionUtil() throws HibernateException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (ExceptionInInitializerError e) {
            throw new HibernateException("Initial SessionFactory creation failed. ", e);
        }
    }

    @Override
    public void finalize() throws Throwable {
        if (session != null && session.isOpen()) {
            session.close();
        }
        super.finalize();
    }

    @Override
    public void addSession(Session ses) throws SQLException {
        try {
            session.save(ses);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void updateSession(int id, Session ses) throws SQLException {
        try {
            session.update(ses);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
    }

    @Override
    public Session getSessionById(int id) throws SQLException {
        Session ses = null;
        try {
            ses = (Session) session.load(Session.class, id);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
        return ses;
    }

    @Override
    public Collection getAllSessions() throws SQLException {
        List sessions = new ArrayList<User>();
        try {
            sessions = session.createCriteria(User.class).list();
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
        return sessions;
    }

    @Override
    public void deleteSession(Session ses) throws SQLException {
        try {
            session.delete(ses);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
    }
}
