package ru.ddsurok.datamodel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.ddsurok.datamodel.User;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author d.duritskij
 */
public class UserUtils {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    Session session = null;

    public static List getActorsByID() {
        List<User> actorList = null;

        try {

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Query query = session.createQuery("select from M_USER");
            query.setCacheable(false);
            actorList = query.list();

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return actorList;
    }
}
