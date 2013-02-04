package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS000000 extends SOAPFaultException {

    public MAPS000000() throws SOAPException {
        super((new FaultDetail("MAPS-000000", "Данная операция не реализована", "")).getSOAPFault());
    }

}
