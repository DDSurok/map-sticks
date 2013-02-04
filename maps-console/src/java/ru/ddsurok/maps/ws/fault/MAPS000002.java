package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS000002 extends SOAPFaultException {

    public MAPS000002() throws SOAPException {
        super((new FaultDetail("MAPS-000002", "Ошибка потокового исполненения при обращении к базе данных.", "")).getSOAPFault());
    }

}
