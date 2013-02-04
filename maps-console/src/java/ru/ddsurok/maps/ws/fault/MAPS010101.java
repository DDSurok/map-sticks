package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS010101 extends SOAPFaultException {

    public MAPS010101() throws SOAPException {
        super((new FaultDetail("MAPS-010101", "Указанный пользователь не найден в системе.", "")).getSOAPFault());
    }

}
