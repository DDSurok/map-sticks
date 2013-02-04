package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS020101 extends SOAPFaultException {

    public MAPS020101() throws SOAPException {
        super((new FaultDetail("MAPS-020101", "Ошибка получения информации об альбомах.", "")).getSOAPFault());
    }

}
