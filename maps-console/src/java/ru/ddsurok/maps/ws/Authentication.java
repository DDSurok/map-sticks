/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws;

import java.util.Date;
import java.util.Random;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.soap.SOAPFaultException;
import ru.ddsurok.datamodel.User;
import ru.ddsurok.datamodel.Session;
import ru.ddsurok.fault.FaultDetail;
import ru.ddsurok.utils.session.SessionUtil;
import ru.ddsurok.utils.user.UserUtil;

/**
 *
 * @author d.duritskij
 */
@Singleton
@LocalBean
@WebService(serviceName = "authentication", targetNamespace = "http://maps.ddsurok.ru/ws/authentication")
public class Authentication {

    @WebMethod
    public @WebResult(name = "authToken")
    String getAuthToken(@WebParam(name = "nick") String nick, @WebParam(name = "hashpswd") String hashPswd) throws Exception {
        try {
            UserUtil userUtil = new UserUtil();
            User user = userUtil.getUserByNick(nick);
            if (user == null) {
                // User not found
                throw new Exception(new SOAPFaultException((new FaultDetail("MAPS-010001", "Указанный пользователь не найден в системе.", "")).getSOAPFault()));
            } else {
                if (!user.getHashpswd().equals(hashPswd))
                {
                    // Password don't correct
                    throw new Exception(new SOAPFaultException((new FaultDetail("MAPS-010002", "Пароль указан не верно.", "")).getSOAPFault()));
                } else {
                    Random random = new Random(new Date().getTime());
                    Long l = random.nextLong();
                    return l.toString();
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
            throw new Exception(new SOAPFaultException((new FaultDetail("MAPS-000001", "Произошла ошибка при обращении к базе данных. Обратитесь к администратору.", "")).getSOAPFault()));
        }
    }
    
    @WebMethod
    public @WebResult(name = "isValidate")
    boolean validateAuthToken(@WebParam(name = "AuthToken") int authToken) throws Exception {
        SessionUtil sessionUtil;
        try {
            sessionUtil = new SessionUtil();
        } catch (Exception ex) {
            System.err.println(ex);
            throw new Exception(new SOAPFaultException((new FaultDetail("MAPS-000001", "Произошла ошибка при обращении к базе данных. Обратитесь к администратору.", "")).getSOAPFault()));
        }
        try {
            Session session = sessionUtil.getSessionByAuthToken(authToken);
            if (session != null)
                return true;
            else
                return false;
        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }
    }
}

