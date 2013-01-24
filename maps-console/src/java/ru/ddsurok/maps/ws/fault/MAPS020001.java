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
public class MAPS020001 extends SOAPFaultException {

    public MAPS020001() throws SOAPException {
        super((new FaultDetail("MAPS-020001", "Не удалось добавить альбом в систему.", "")).getSOAPFault());
    }

}
