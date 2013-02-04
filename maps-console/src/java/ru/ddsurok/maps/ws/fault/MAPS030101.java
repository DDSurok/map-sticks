package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS030101 extends SOAPFaultException {

    public MAPS030101() throws SOAPException {
        super((new FaultDetail("MAPS-030101", "Полученная метка не прошла ФЛК.", "")).getSOAPFault());
    }

}
