package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS010201 extends SOAPFaultException {

    public MAPS010201() throws SOAPException {
        super((new FaultDetail("MAPS-010201", "Указанный токен аутентификации не найден.", "")).getSOAPFault());
    }

}
