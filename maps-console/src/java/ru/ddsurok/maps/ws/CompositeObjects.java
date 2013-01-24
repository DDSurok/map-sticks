/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.soap.SOAPException;

import ru.ddsurok.datamodel.ws.Line;
import ru.ddsurok.maps.ws.fault.MAPS000000;

/**
 *
 * @author d.duritskij
 */
@WebService(serviceName = "", name = "compositeObjects", targetNamespace = "http://maps.ddsurok.ru/ws/compositeObjects")
public class CompositeObjects {

    @WebMethod
    public void createLine(@WebParam(name = "AuthToken") Long authToken, @WebParam(name = "Line") Line line) throws SOAPException {
        throw new MAPS000000();
    }
}
