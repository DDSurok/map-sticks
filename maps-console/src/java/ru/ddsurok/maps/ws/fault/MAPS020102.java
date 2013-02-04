package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS020102 extends SOAPFaultException {

    public MAPS020102() throws SOAPException {
        super((new FaultDetail("MAPS-020102", "Не удалось добавить альбом в систему.", "")).getSOAPFault());
    }

}
