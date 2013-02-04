package ru.ddsurok.utils;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import ru.ddsurok.datamodel.db.Album;
import ru.ddsurok.datamodel.db.User;

public class AlbumUtil implements Serializable {
    
    private org.hibernate.Session session = null;
    
    public AlbumUtil() throws HibernateException {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
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
    
    public void createAlbum(Album album) throws HibernateException {
        try {
            session.save(album);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch(HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
    }
    
    public void updateAlbum(Album album) throws HibernateException {
        try {
            session.update(album);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
    }

    public Album getAlbumById(int Id) throws HibernateException {
        try {
            Album album = (Album)session.load(Album.class, Id);
            session.getTransaction().commit();
            session.beginTransaction();
            return album;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
    }

    public void deleteAlbum(Album db_album) throws HibernateException {
        try {
            session.delete(db_album);
            session.getTransaction().commit();
            session.beginTransaction();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
    }

    public Album[] getAlbumByUser(User user) {
        try {
            List list = session.createCriteria(Album.class).add(Restrictions.eq("Author", user)).list();
            session.getTransaction().commit();
            session.beginTransaction();
            Album[] array = new Album[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = (Album) list.get(i);
            }
            return array;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.beginTransaction();
            throw e;
        }
    }

}
