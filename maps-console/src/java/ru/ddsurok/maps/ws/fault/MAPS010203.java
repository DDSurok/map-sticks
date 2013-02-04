package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS010203 extends SOAPFaultException {

    public MAPS010203() throws SOAPException {
        super((new FaultDetail("MAPS-010203", "Указанный токен аутентификации просрочен.", "")).getSOAPFault());
    }

}
