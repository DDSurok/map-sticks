/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.utils;

/**
 *
 * @author ddsurok
 */
import ru.ddsurok.utils.IUserUtil;
import ru.ddsurok.datamodel.User;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.EntityMode;
import org.hibernate.LockMode;
import ru.ddsurok.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.transform.ResultTransformer;

public class UserUtil implements IUserUtil, Serializable {

    private Session session = null;

    public UserUtil() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize() throws Throwable {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void addUser(User user) throws SQLException {
        try {
            session.save(user);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
    }

    @Override
    public void updateUser(int id, User user) throws SQLException {
        Session session = null;
        try {
            session.update(user);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = null;
        try {
            user = (User) session.load(User.class, id);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
        return user;
    }

    @Override
    public Collection getAllUsers() throws SQLException {
        List users = new ArrayList<User>();
        try {
            users = session.createCriteria(User.class).list();
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
        return users;
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        try {
            session.delete(user);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (Exception e) {
            session.getTransaction().rollback();
            session.beginTransaction();
        }
    }
}
