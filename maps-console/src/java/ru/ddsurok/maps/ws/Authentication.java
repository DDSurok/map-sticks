/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.maps.ws;

import java.util.Date;
import java.util.Random;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.soap.SOAPException;
import org.hibernate.HibernateException;
import ru.ddsurok.datamodel.db.User;
import ru.ddsurok.datamodel.db.Session;
import ru.ddsurok.maps.ws.fault.MAPS000001;
import ru.ddsurok.maps.ws.fault.MAPS010001;
import ru.ddsurok.maps.ws.fault.MAPS010101;
import ru.ddsurok.maps.ws.fault.MAPS010102;
import ru.ddsurok.maps.ws.fault.MAPS010201;
import ru.ddsurok.maps.ws.fault.MAPS010202;
import ru.ddsurok.utils.fault.SessionClosedException;
import ru.ddsurok.utils.SessionUtil;
import ru.ddsurok.utils.fault.UserAlreadyLoginedException;
import ru.ddsurok.utils.UserUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author d.duritskij
 */
@WebService(name = "authentication", targetNamespace = "http://maps.ddsurok.ru/ws/authentication")
public class Authentication {

    @WebMethod
    public @WebResult(name = "AuthToken")
    long getAuthToken(@WebParam(name = "Nick") String nick, @WebParam(name = "HashPassword") String hashPswd) throws SOAPException {
        try {
            UserUtil userUtil = new UserUtil();
            User user = userUtil.getUserByNick(nick);
            validateUserInfo(user, hashPswd);
            SessionUtil sessionUtil = new SessionUtil();
            Session session = sessionUtil.getSessionByUser(user);
            if (session != null) {
                try {
                    sessionUtil.validateSession(session);
                    return session.getAuthToken();
                } catch (SessionClosedException ex) {
                    System.out.println(ex);
                }
            }
            Random random = new Random(new Date().getTime());
            session = new Session(random.nextLong(), user, (short)60);
            sessionUtil.createSession(session);
            return session.getAuthToken();
        } catch (HibernateException ex) {
            System.err.println(ex);
            throw new MAPS000001();
        } catch (UserAlreadyLoginedException ex) {
            System.err.println(ex);
            throw new MAPS010001();
        }
    }
    
    @WebMethod
    public @WebResult(name = "IsValidate")
    boolean validateAuthToken(@WebParam(name = "AuthToken") Long authToken) throws SOAPException {
        SessionUtil sessionUtil;
        Logger log = LogManager.getLogger(Authentication.class);
        log.info("SUCCESS");
        try {
            sessionUtil = new SessionUtil();
            Session session = sessionUtil.getSessionByAuthToken(authToken);
            if (session != null) {
                try {
                    sessionUtil.validateSession(session);
                    return true;
                } catch (SessionClosedException ex) {
                    System.out.println(ex);
                    return false;
                }
            } else {
                return false;
            }
        } catch (HibernateException ex) {
            System.err.println(ex);
            throw new MAPS000001();
        } catch (Throwable ex) {
            System.err.println(ex);
            throw new MAPS000001();
        }
        
    }
    
    @WebMethod
    public void destroyAuthToken(@WebParam(name = "Nick") String nick, @WebParam(name = "AuthToken") Long authToken) throws SOAPException {
        try {
            SessionUtil sessionUtil = new SessionUtil();
            Session session = sessionUtil.getSessionByAuthToken(authToken);
            if (session == null) {
                throw new MAPS010201();
            }
            if (!session.getUser().getNick().equals(nick)) {
                throw new MAPS010202();
            }
            sessionUtil.removeSession(session);
        } catch (HibernateException ex) {
            System.err.println(ex);
            throw new MAPS000001();
        }
    }

    private void validateUserInfo(User user, String hashPswd) throws SOAPException {
        if (user == null) {
            // User not found
            throw new MAPS010101();
        }
        if (!user.getHashpswd().equals(hashPswd))
        {
            // Password don't correct
            throw new MAPS010102();
        }
    }
}
