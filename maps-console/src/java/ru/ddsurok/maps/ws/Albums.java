/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws;

import java.util.logging.Level;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.soap.SOAPException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import ru.ddsurok.datamodel.db.User;
import ru.ddsurok.datamodel.fault.ObjectCanNotConvertedException;

import ru.ddsurok.datamodel.ws.Album;
import ru.ddsurok.utils.AlbumUtil;
import ru.ddsurok.maps.ws.fault.*;
import ru.ddsurok.utils.SessionUtil;

@WebService(serviceName = "Albums", name = "change", targetNamespace = "http://maps.ddsurok.ru/ws/albums/change")
public class Albums {
    
    Logger log = Logger.getLogger(Albums.class);

    @WebMethod
    public void createAlbum(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Album") Album album) throws SOAPException {
        if (log.isTraceEnabled()) {
            log.trace("Create album run.");
        }
        new Authentication().validateAuthTokenWithInfo(authToken);
        ru.ddsurok.datamodel.db.Album db_album = new ru.ddsurok.datamodel.db.Album(album);
        if (!db_album.validate()) {
            MAPS020001 ex = new MAPS020001();
            log.info(ex);
            throw ex;
        }
        AlbumUtil util;
        try {
            util = new AlbumUtil();
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS000001();
        }
        try {
            util.createAlbum(db_album);
            if (log.isTraceEnabled()) {
                log.trace("Create album finish.");
            }
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS020102();
        }
    }
    
    @WebMethod
    public void changeAlbum(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Album") Album album) throws SOAPException {
        if (log.isTraceEnabled()) {
            log.trace("Change album run.");
        }
        new Authentication().validateAuthTokenWithInfo(authToken);
        ru.ddsurok.datamodel.db.Album db_album = new ru.ddsurok.datamodel.db.Album(album);
        if (!db_album.validate()) {
            MAPS020001 ex = new MAPS020001();
            log.info(ex);
            throw ex;
        }
        AlbumUtil util;
        try {
            util = new AlbumUtil();
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS000001();
        }
        try {
            util.updateAlbum(db_album);
            if (log.isTraceEnabled()) {
                log.trace("Change album finish.");
            }
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS020104();
        }
    }
    
    @WebMethod
    public void removeAlbum(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Album") Album album) throws SOAPException {
        if (log.isTraceEnabled()) {
            log.trace("Delete album run.");
        }
        new Authentication().validateAuthTokenWithInfo(authToken);
        ru.ddsurok.datamodel.db.Album db_album = new ru.ddsurok.datamodel.db.Album(album);
        if (!db_album.validate()) {
            MAPS020001 ex = new MAPS020001();
            log.info(ex);
            throw ex;
        }
        AlbumUtil util;
        try {
            util = new AlbumUtil();
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS000001();
        }
        try {
            util.deleteAlbum(db_album);
            if (log.isTraceEnabled()) {
                log.trace("Delete album finish.");
            }
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS020103();
        }
    }
    
    @WebMethod
    @WebResult(name = "Album")
    public Album[] getAlbumsByUser(@WebParam(name = "AuthToken") Long authToken) throws SOAPException {
        if (log.isTraceEnabled()) {
            log.trace("Get albums by user run.");
        }
        new Authentication().validateAuthTokenWithInfo(authToken);
        AlbumUtil util;
        SessionUtil ses_util;
        try {
            util = new AlbumUtil();
            ses_util = new SessionUtil();
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS000001();
        }
        User user;
        try {
            user = ses_util.getSessionByAuthToken(authToken).getUser();
        } catch (HibernateException ex) {
            log.error(ex);
            throw new MAPS000002();
        }
        try {
            ru.ddsurok.datamodel.db.Album[] db_albums = util.getAlbumByUser(user);
            Album[] albums = new Album[db_albums.length];
            for (int i = 0; i < db_albums.length; i++) {
                try {
                    albums[i] = db_albums[i].toWsAlbum();
                } catch (ObjectCanNotConvertedException ex) {
                    log.error(ex.getMessage());
                }
            }
            if (log.isTraceEnabled()) {
                log.trace("Get albums by user finish.");
            }
            return albums;
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS020101();
        }
    }
}
