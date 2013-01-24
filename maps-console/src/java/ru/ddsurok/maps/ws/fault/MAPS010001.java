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
public class MAPS010001 extends SOAPFaultException {

    public MAPS010001() throws SOAPException {
        super((new FaultDetail("MAPS-010001", "Ошибка потокового исполненения при обращении к базе данных.", "")).getSOAPFault());
    }

}
