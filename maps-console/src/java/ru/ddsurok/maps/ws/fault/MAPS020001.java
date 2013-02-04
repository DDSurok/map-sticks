package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS020001 extends SOAPFaultException {

    public MAPS020001() throws SOAPException {
        super((new FaultDetail("MAPS-020001", "Полученный альбом не прошел ФЛК.", "")).getSOAPFault());
    }

}
