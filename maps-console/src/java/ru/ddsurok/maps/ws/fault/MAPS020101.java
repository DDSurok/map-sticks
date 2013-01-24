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
public class MAPS020101 extends SOAPFaultException {

    public MAPS020101() throws SOAPException {
        super((new FaultDetail("MAPS-020101", "Не удалось добавить альбом из нарушения ФЛК.", "")).getSOAPFault());
    }

}
