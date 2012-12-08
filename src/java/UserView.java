/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author d.duritskij
 */
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ru.ddsurok.datamodel.User;

public class UserView {

    Session session = null;

    public List getActorsByID() {
        List<User> actorList = null;

        try {

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
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
