/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.utils;

/**
 *
 * @author ddsurok
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ru.ddsurok.datamodel.db.User;

public class UserUtil implements Serializable {

    private Session session = null;

    public UserUtil() throws HibernateException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (ExceptionInInitializerError e) {
            throw new HibernateException("Initial SessionFactory creation failed. ", e);
        }
    }

    @Override
    protected void finalize() throws Exception {
        try {
            if (session != null && session.isOpen()) {
                session.close();
            }
            super.finalize();
        } catch (Throwable th) {
            throw new Exception(th);
        }
    }

    public void addUser(User user) throws HibernateException {
        try {
            session.save(user);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
    }

    public void updateUser(int id, User user) throws HibernateException {
        try {
            session.update(user);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
    }

    public User getUserById(int id) throws HibernateException {
        User user = null;
        try {
            user = (User) session.load(User.class, id);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
        return user;
    }

    public User getUserByNick(String nick) throws HibernateException {
        User user = null;
        try {
            List users =  session.createCriteria(User.class).add(Restrictions.eq("nick", nick)).list();
            if (users.size() > 0) {
                user = (User) users.get(0);
            }
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
        return user;
    }

    public Collection getAllUsers() throws HibernateException {
        List users = new ArrayList<User>();
        try {
            users = session.createCriteria(User.class).list();
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
        return users;
    }

    public void deleteUser(User user) throws HibernateException {
        try {
            session.delete(user);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
    }
}
