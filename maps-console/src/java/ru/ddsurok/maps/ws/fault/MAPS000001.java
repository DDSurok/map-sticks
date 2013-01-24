/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

/**
 *
 * @author d.duritskij
 */
public class MAPS000001 extends SOAPFaultException {

    public MAPS000001() throws SOAPException {
        super((new FaultDetail("MAPS-000001", "Произошла ошибка при обращении к базе данных. Обратитесь к администратору.", "")).getSOAPFault());
    }

}
