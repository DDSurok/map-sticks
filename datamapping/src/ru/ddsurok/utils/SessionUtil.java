package ru.ddsurok.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ddsurok.datamodel.db.Session;
import ru.ddsurok.datamodel.db.User;
import ru.ddsurok.utils.fault.SessionClosedException;
import ru.ddsurok.utils.fault.UserAlreadyLoginedException;

public class SessionUtil implements Serializable {

    private org.hibernate.Session session = null;
    
    private Logger log = LoggerFactory.getLogger(SessionUtil.class);

    public SessionUtil() throws HibernateException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Throwable e) {
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

    public void createSession(Session ses) throws HibernateException, UserAlreadyLoginedException {
        try {
            ses.setCreateTime(new Date());
            ses.setLastUpdateTime(new Date());
            try {
                Session temp = getSessionByUser(ses.getUser());
                if (temp != null) {
                    validateSession(temp);
                    throw new UserAlreadyLoginedException();
                }
            } catch (SessionClosedException e) {
                System.out.println("Удалена устаревшая запись о входе в систему.");
            }
            session.save(ses);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
    }

    public Session getSessionById(UUID id) throws HibernateException {
        Session ses = null;
        try {
            ses = (Session)session.load(Session.class, id);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw ex;
        }
        return ses;
    }

    public Session getSessionByAuthToken(long authToken) throws HibernateException {
        Session ses = null;
        try {
            List list = session.createCriteria(Session.class).add(Restrictions.eq("AuthToken", Long.valueOf(authToken))).list();
            if (list.size() > 0) {
                ses = (Session) list.get(0);
            }
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException ex) {
            session.getTransaction().rollback();
            session.beginTransaction();
            log.info(ex.getMessage(), ex.fillInStackTrace());
        } finally {
            return ses;
        }
    }

    public Session getSessionByUser(User user) throws HibernateException {
        Session ses = null;
        try {
            List list = session.createCriteria(Session.class).add(Restrictions.eq("User", user)).list();
            if (list.size() > 0) {
                ses = (Session)list.get(0);
            }
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
        return ses;
    }

    public void removeSession(Session ses) throws HibernateException {
        try {
            session.delete(ses);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
    }

    public void validateSession(Session ses) throws SessionClosedException {
        Date deathTime = new Date(ses.getLastUpdateTime().getTime() + ses.getLiveTime()*60*1000);
        if (deathTime.before(new Date())) {
            removeSession(ses);
            throw new SessionClosedException(ses);
        }
        ses.setLastUpdateTime(new Date());
        session.update(ses);
    }
}
