/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.utils.user;

/**
 *
 * @author ddsurok
 */
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.ddsurok.datamodel.User;
import ru.ddsurok.utils.HibernateUtil;

public class UserUtil implements IUserUtil, Serializable {

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
    public void finalize() throws Throwable {
        if (session != null && session.isOpen()) {
            session.close();
        }
        super.finalize();
    }

    @Override
    public void addUser(User user) throws SQLException {
        try {
            session.save(user);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw new SQLException(e.getMessage());
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

    public static UserUtil getUserUtil(HttpSession session) {
        UserUtil util = null;
        Object temp = session.getAttribute("utils");
        try {
            if (temp==null)
                throw new Throwable();
            util = (UserUtil)temp;
        } catch(Throwable th) {
            util = new UserUtil();
            session.setAttribute("utils", util);
        } finally {
            return util;
        }
    }
}
