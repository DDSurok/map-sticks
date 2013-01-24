/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.soap.SOAPException;
import org.hibernate.HibernateException;

import ru.ddsurok.datamodel.db.Album;
import ru.ddsurok.utils.AlbumUtil;
import ru.ddsurok.maps.ws.fault.MAPS000000;
import ru.ddsurok.maps.ws.fault.MAPS000001;
import ru.ddsurok.maps.ws.fault.MAPS020001;
import ru.ddsurok.maps.ws.fault.MAPS020101;

/**
 *
 * @author d.duritskij
 */

@WebService(serviceName = "Albums", name = "change", targetNamespace = "http://maps.ddsurok.ru/ws/albums/change")
public class Albums {

    @WebMethod
    public void createAlbum(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Album") Album album) throws SOAPException {
        new Authentication().validateAuthToken(authToken);
        if (!album.validate()) {
            throw new MAPS020101();
        }
        AlbumUtil util;
        try {
            util = new AlbumUtil();
        } catch (HibernateException ex) {
            throw new MAPS000001();
        }
        try {
            util.createAlbum(album);
        } catch (HibernateException ex) {
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
