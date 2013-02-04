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
import ru.ddsurok.maps.ws.fault.MAPS000002;
import ru.ddsurok.maps.ws.fault.MAPS010101;
import ru.ddsurok.maps.ws.fault.MAPS010102;
import ru.ddsurok.maps.ws.fault.MAPS010201;
import ru.ddsurok.maps.ws.fault.MAPS010202;
import ru.ddsurok.utils.fault.SessionClosedException;
import ru.ddsurok.utils.SessionUtil;
import ru.ddsurok.utils.fault.UserAlreadyLoginedException;
import ru.ddsurok.utils.UserUtil;

import org.apache.log4j.Logger;
import ru.ddsurok.maps.ws.fault.MAPS010203;

@WebService(name = "authentication", targetNamespace = "http://maps.ddsurok.ru/ws/authentication")
public class Authentication {
    
    Logger log = Logger.getLogger(Authentication.class);

    @WebMethod
    public @WebResult(name = "AuthToken")
    long getAuthToken(@WebParam(name = "Nick") String nick, @WebParam(name = "HashPassword") String hashPswd) throws SOAPException {
        try {
            if (log.isTraceEnabled()) {
               log.trace("Get auth token run.");
            }
            UserUtil userUtil = new UserUtil();
            User user = userUtil.getUserByNick(nick);
            validateUserInfo(user, hashPswd);
            SessionUtil sessionUtil = new SessionUtil();
            Session session = sessionUtil.getSessionByUser(user);
            if (session != null) {
                try {
                    sessionUtil.validateSession(session);
                    if (log.isTraceEnabled()) {
                       log.trace("Get auth token finish.");
                    }
                    return session.getAuthToken();
                } catch (SessionClosedException ex) {
                    log.info(ex);
                }
            }
            Random random = new Random(new Date().getTime());
            session = new Session(random.nextLong(), user, (short)60);
            sessionUtil.createSession(session);
            if (log.isTraceEnabled()) {
                log.trace("Get auth token finish.");
            }
            return session.getAuthToken();
        } catch (HibernateException ex) {
            log.error(ex);
            throw new MAPS000001();
        } catch (UserAlreadyLoginedException ex) {
            log.info(ex);
            throw new MAPS000002();
        }
    }
    
    @WebMethod(operationName = "validateAuthToken")
    public @WebResult(name = "IsValidate")
    boolean silentValidateAuthToken(@WebParam(name = "AuthToken") Long authToken) {
        try {
            validateAuthTokenWithInfo(authToken);
            return true;
        } catch (Throwable th) {
            log.info(th);
            return false;
        }
    }
    
    @WebMethod
    public void destroyAuthToken(@WebParam(name = "Nick") String nick, @WebParam(name = "AuthToken") Long authToken) throws SOAPException {
        try {
            if (log.isTraceEnabled()) {
                log.trace("Destroy auth token run.");
            }
            SessionUtil sessionUtil = new SessionUtil();
            Session session = sessionUtil.getSessionByAuthToken(authToken);
            if (session == null) {
                MAPS010201 ex = new MAPS010201();
                log.info(ex);
                throw ex;
            }
            if (!session.getUser().getNick().equals(nick)) {
                MAPS010202 ex = new MAPS010202();
                log.info(ex);
                throw ex;
            }
            sessionUtil.removeSession(session);
            if (log.isTraceEnabled()) {
                log.trace("Destroy auth token finish.");
            }
        } catch (HibernateException ex) {
            log.error(ex);
            throw new MAPS000001();
        }
    }

    private void validateUserInfo(User user, String hashPswd) throws SOAPException {
        if (user == null) {
            // User not found
            MAPS010101 ex = new MAPS010101();
            log.info(ex);
            throw ex;
        }
        if (!user.getHashpswd().equals(hashPswd))
        {
            // Password don't correct
            MAPS010102 ex = new MAPS010102();
            log.info(ex);
            throw ex;
        }
    }

    public void validateAuthTokenWithInfo(Long authToken) throws SOAPException {
        SessionUtil sessionUtil;
        if (log.isTraceEnabled()) {
            log.trace("Validate auth token run.");
        }
        try {
            sessionUtil = new SessionUtil();
        } catch (HibernateException ex) {
            log.error(ex);
            throw new MAPS000001();
        }
        Session session = sessionUtil.getSessionByAuthToken(authToken);
        if (session != null) {
            try {
                sessionUtil.validateSession(session);
            } catch (SessionClosedException ex) {
                log.info(ex);
                throw new MAPS010203();
            }
            if (log.isTraceEnabled()) {
               log.trace("Validate auth token finish.");
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Validate auth token finish.");
            }
            throw new MAPS010201();
        }
    }
}
