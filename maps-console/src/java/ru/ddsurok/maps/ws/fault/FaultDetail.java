/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws.fault;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;

/**
 *
 * @author d.duritskij
 */
@XmlRootElement(name = "FaultDetail", namespace="http://maps.ddsurok.ru/ws/fault")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FaultDetail", namespace = "http://maps.ddsurok.ru/ws/authentication", propOrder={"FaultCode", "FaultMessage", "Detail"})
public class FaultDetail implements Serializable {

    protected String FaultCode;
    protected String FaultMessage;
    protected String Detail = "";

    public FaultDetail() {
    }

    public FaultDetail(String faultCode, String faultMessage, String detail) {
        FaultCode = faultCode;
        FaultMessage = faultMessage;
        Detail = detail;
    }

    public void setErrorCode(String value) {
        FaultCode = value;
    }

    public String getErrorCode() {
        return FaultCode;
    }

    public void setDetail(String value) {
        Detail = value;
    }

    public String getDetail() {
        return Detail;
    }

    public void setFaultMessage(String value) {
        FaultMessage = value;
    }

    public String getFaultMessage() {
        return FaultMessage;
    }
    
    public SOAPFault getSOAPFault() throws SOAPException {
        SOAPFactory fac = SOAPFactory.newInstance();
        SOAPFault sf = fac.createFault();
        sf.setFaultCode(FaultCode);
        sf.setFaultString(FaultMessage);
        sf.addDetail().addTextNode(Detail);
        return sf;
    }
}
