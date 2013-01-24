/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.soap.SOAPException;

import ru.ddsurok.datamodel.db.Album;
import ru.ddsurok.maps.ws.fault.MAPS000000;

/**
 *
 * @author d.duritskij
 */
@WebService(serviceName = "Albums", name = "access", targetNamespace = "http://maps.ddsurok.ru/ws/albums/access")
public class AlbumAccess {

    @WebMethod
    public Album[] getAvailableAlbums(@WebParam(name = "AuthToken") Long authToken) throws SOAPException {
        throw new MAPS000000();
    }
    
    @WebMethod
    public String getAccessLevelToAlbum(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "AlbumName") String albumName) throws SOAPException {
        throw new MAPS000000();
    }
    
    @WebMethod
    public void setGrantToAlbum(@WebParam(name = "AuthTokenOwner") Long owner, @WebParam(name = "UserRecipient") String user, @WebParam(name = "AlbumName") String albumName, @WebParam(name = "Level") String level) throws SOAPException {
        throw new MAPS000000();
    }
}
