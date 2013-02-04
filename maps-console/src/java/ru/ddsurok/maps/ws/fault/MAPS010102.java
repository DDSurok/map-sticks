package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS010102 extends SOAPFaultException {

    public MAPS010102() throws SOAPException {
        super((new FaultDetail("MAPS-010102", "Пароль указан не верно.", "")).getSOAPFault());
    }

}
