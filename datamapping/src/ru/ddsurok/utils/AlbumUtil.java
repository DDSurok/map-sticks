package ru.ddsurok.utils;

import java.io.Serializable;
import org.hibernate.HibernateException;
import ru.ddsurok.datamodel.db.Album;

public class AlbumUtil implements Serializable {
    
    private org.hibernate.Session session = null;
    
    public AlbumUtil() throws HibernateException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
//            log = LogManager.getLogger(AlbumUtil.class);
        } catch (Exception e) {
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
    
    public void createAlbum(Album album) {
        try {
            session.save(album);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
//            log.info(e);
            throw e;
        }
    }
    
    public void updateAlbum(Album album) {
        try {
            session.update(album);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
//            log.info(e);
            throw e;
        }
    }
}
