/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws.authentication;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.soap.SOAPException;
import javax.xml.ws.soap.SOAPFaultException;
import org.hibernate.HibernateException;
import ru.ddsurok.datamodel.User;
import ru.ddsurok.fault.FaultDetail;
import ru.ddsurok.utils.user.UserUtil;

/**
 *
 * @author d.duritskij
 */
@WebService(serviceName = "authentication", targetNamespace = "http://maps.ddsurok.ru/ws/authentication")
public class authentication {

    @WebMethod
    public @WebResult(name = "authToken")
    String getAuthToken(@WebParam(name = "nick") String nick, @WebParam(name = "hashpswd") String hashPswd) throws SOAPFaultException, SOAPException {
        try {
            UserUtil userUtil = new UserUtil();
            User user = userUtil.getUserByNick(nick);
            if (user == null) {
                // User not found
                throw new SOAPFaultException((new FaultDetail("MAPS-010001", "Указанный пользователь не найден в системе.", "")).getSOAPFault());
            } else {
                if (!user.getHashPswd().equals(hashPswd))
                {
                    // Password don't correct
                    throw new SOAPFaultException((new FaultDetail("MAPS-010002", "Пароль указан не верно.", "")).getSOAPFault());
                } else {
                    Random random = new Random(new Date().getTime());
                    Long l = random.nextLong();
                    return l.toString();
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            throw new SOAPFaultException((new FaultDetail("MAPS-000001", "Произошла ошибка при обращении к базе данных. Обратитесь к администратору.", "")).getSOAPFault());
        } catch (HibernateException ex) {
            System.err.println(ex);
            throw new SOAPFaultException((new FaultDetail("MAPS-000001", "Произошла ошибка при обращении к базе данных. Обратитесь к администратору.", "")).getSOAPFault());
        }

    }
}
