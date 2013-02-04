package ru.ddsurok.maps.ws.fault;

import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;

public class MAPS020103 extends SOAPFaultException {

    public MAPS020103() throws SOAPException {
        super((new FaultDetail("MAPS-020103", "Не удалось удалить альбом из системы.", "")).getSOAPFault());
    }

}
