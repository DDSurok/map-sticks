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
public class MAPS010101 extends SOAPFaultException {

    public MAPS010101() throws SOAPException {
        super((new FaultDetail("MAPS-010101", "Указанный пользователь не найден в системе.", "")).getSOAPFault());
    }

}
