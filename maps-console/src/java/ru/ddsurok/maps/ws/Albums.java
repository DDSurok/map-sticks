/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.soap.SOAPException;
import javax.xml.ws.ServiceMode;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import ru.ddsurok.datamodel.ws.Album;
import ru.ddsurok.utils.AlbumUtil;
import ru.ddsurok.maps.ws.fault.MAPS000000;
import ru.ddsurok.maps.ws.fault.MAPS000001;
import ru.ddsurok.maps.ws.fault.MAPS020001;
import ru.ddsurok.maps.ws.fault.MAPS020101;

@WebService(serviceName = "Albums", name = "change", targetNamespace = "http://maps.ddsurok.ru/ws/albums/change")
public class Albums {
    
    Logger log = Logger.getLogger(Albums.class);

    @WebMethod
    public void createAlbum(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Album") Album album) throws SOAPException {
        if (log.isTraceEnabled()) {
            log.trace("Create album run.");
        }
        new Authentication().validateAuthToken(authToken);
        ru.ddsurok.datamodel.db.Album db_album = new ru.ddsurok.datamodel.db.Album(album);
        if (!db_album.validate()) {
            MAPS020101 ex = new MAPS020101();
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
                log.trace("Create album run.");
            }
        } catch (HibernateException ex) {
            log.info(ex);
            throw new MAPS020001();
        }
    }
    
    @WebMethod
    public void changeAlbum(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Album") Album album) throws SOAPException {
        throw new MAPS000000();
    }
    
    @WebMethod
    public void removeAlbum(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Album") Album album) throws SOAPException {
        throw new MAPS000000();
    }
    
    @WebMethod
    public Album[] getAlbumsByUser(@WebParam(name = "AuthToken") Long authToken) throws SOAPException {
        throw new MAPS000000();
    }
}
