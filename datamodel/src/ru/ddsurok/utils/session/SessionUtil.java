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
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
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
    public void finalize() throws Exception {
        try {
            if (session != null && session.isOpen()) {
                session.close();
            }
            super.finalize();
        } catch (Throwable th) {
            throw new Exception(th);
        }
    }

    @Override
    public void createSession(Session ses) throws SQLException {
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
    public Session getSessionByAuthToken(int authToken) throws SQLException {
        Session ses = null;
        try {
            ses = (Session) session.createFilter(Session.class, "authtoken = " + authToken).list().get(0);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
        return ses;
    }

    @Override
    public Session getSessionByUser(User user) throws SQLException {
        Session ses = null;
        try {
            ses = (Session) session.createCriteria(Session.class).add(Restrictions.eq("user", user)).list().get(0);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
        return ses;
    }

    @Override
    public void removeSession(Session ses) throws SQLException {
        try {
            session.delete(ses);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
    }

    @Override
    public void removeSessionByAuthToken(int authToken) throws SQLException {
        try {
            Session ses = this.getSessionByAuthToken(authToken);
            if (ses != null) {
                session.delete(ses);
            } else {
                throw new Exception("Не найден экземпляр сессии который пытаемся удалить.");
            }
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
    }
}
