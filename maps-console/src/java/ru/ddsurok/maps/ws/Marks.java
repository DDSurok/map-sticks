/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.soap.SOAPException;

import ru.ddsurok.datamodel.db.Mark;
import ru.ddsurok.datamodel.db.Album;
import ru.ddsurok.maps.ws.fault.MAPS000000;

/**
 *
 * @author d.duritskij
 */
@WebService(serviceName = "", name = "marks", targetNamespace = "http://maps.ddsurok.ru/ws/marks")
public class Marks {

    @WebMethod
    public void createPoint(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Mark") Mark point) throws SOAPException {
        throw new MAPS000000();
    }
    
    @WebMethod
    public void changePoint(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Mark") Mark point) throws SOAPException {
        throw new MAPS000000();
    }
    
    @WebMethod
    public void removePoint(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Mark") Mark point) throws SOAPException {
        throw new MAPS000000();
    }
    
    @WebMethod
    public Mark[] getPointByAlbum(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Album") Album album) throws SOAPException {
        throw new MAPS000000();
    }
}
