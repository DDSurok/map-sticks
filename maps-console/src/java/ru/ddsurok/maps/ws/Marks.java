package ru.ddsurok.maps.ws;

import java.util.HashSet;
import java.util.Set;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.soap.SOAPException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import ru.ddsurok.datamodel.ws.Mark;
import ru.ddsurok.maps.ws.fault.*;
import ru.ddsurok.utils.AlbumUtil;

@WebService(serviceName = "", name = "marks", targetNamespace = "http://maps.ddsurok.ru/ws/marks")
public class Marks {
    
    Logger log = Logger.getLogger(Marks.class);

    @WebMethod
    public void createPoint(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "AlbumId") Integer albumId, @WebParam(name = "Mark") Mark point) throws SOAPException {
        if (log.isTraceEnabled()) {
            log.trace("Create point run.");
        }
        new Authentication().validateAuthTokenWithInfo(authToken);
        AlbumUtil util;
        ru.ddsurok.datamodel.db.Mark db_mark;
        try {
            db_mark = new ru.ddsurok.datamodel.db.Mark(point);
        } catch (HibernateException ex) {
            log.error(ex);
            throw new MAPS030101();
        }
        try {
            util = new AlbumUtil();
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS000001();
        }
        try {
            ru.ddsurok.datamodel.db.Album db_album = util.getAlbumById(albumId);
            db_mark.setAlbum(db_album);
            db_album.getMarks().add(db_mark);
            util.updateAlbum(db_album);
            if (log.isTraceEnabled()) {
                log.trace("Create point finish.");
            }
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS020101();
        }
    }
    
    @WebMethod
    public void changePoint(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "AlbumId") Integer albumId, @WebParam(name = "OldMark") Mark point, @WebParam(name = "NewMark") Mark new_point) throws SOAPException {
        if (log.isTraceEnabled()) {
            log.trace("Change point run.");
        }
        new Authentication().validateAuthTokenWithInfo(authToken);
        AlbumUtil util;
        try {
            util = new AlbumUtil();
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS000001();
        }
        try {
            ru.ddsurok.datamodel.db.Album db_album = util.getAlbumById(albumId);
            for (ru.ddsurok.datamodel.db.Mark item : db_album.getMarks()) {
                if (!item.getAuthor().equals(db_album.getAuthor())) {
                    continue;
                }
                if (!item.getCaption().equals(point.Caption)) {
                    continue;
                }
                if (item.getX() != point.getX()) {
                    continue;
                }
                if (item.getY() != point.getY()) {
                    continue;
                }
                item.setX(new_point.getX());
                item.setY(new_point.getY());
                item.setCaption(new_point.Caption);
                item.setColor(new_point.Color);
            }
            util.updateAlbum(db_album);
            if (log.isTraceEnabled()) {
                log.trace("Change point finish.");
            }
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS020101();
        }
    }
    
    @WebMethod
    public void removePoint(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "AlbumId") Integer albumId, @WebParam(name = "Mark") Mark point) throws SOAPException {
        if (log.isTraceEnabled()) {
            log.trace("Delete point run.");
        }
        new Authentication().validateAuthTokenWithInfo(authToken);
        AlbumUtil util;
        try {
            util = new AlbumUtil();
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS000001();
        }
        try {
            ru.ddsurok.datamodel.db.Album db_album = util.getAlbumById(albumId);
            Set removedSet = new HashSet();
            for (ru.ddsurok.datamodel.db.Mark item : db_album.getMarks()) {
                if (!item.getAuthor().equals(db_album.getAuthor())) {
                    continue;
                }
                if (!item.getCaption().equals(point.Caption)) {
                    continue;
                }
                if (item.getX() != point.getX()) {
                    continue;
                }
                if (item.getY() != point.getY()) {
                    continue;
                }
                removedSet.add(item);
            }
            for (Object item : removedSet) {
                if (db_album.getMarks().remove(item)) {
                    log.warn("Mark can not removed.");
                }
            }
            util.updateAlbum(db_album);
            if (log.isTraceEnabled()) {
                log.trace("Delete point finish.");
            }
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS020101();
        }
    }
}
