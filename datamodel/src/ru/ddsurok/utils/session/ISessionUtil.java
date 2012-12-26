/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.utils.session;

/**
 *
 * @author ddsurok
 */
import java.sql.SQLException;
import ru.ddsurok.datamodel.Session;
import ru.ddsurok.datamodel.User;

public interface ISessionUtil {

    public void createSession(Session user) throws SQLException;

    public void updateSession(int id, Session session) throws SQLException;

    public Session getSessionById(int id) throws SQLException;
    
    public Session getSessionByAuthToken(int authToken) throws SQLException;
    
    public Session getSessionByUser(User user) throws SQLException;

    public void removeSession(Session session) throws SQLException;
    
    public void removeSessionByAuthToken(int authToken) throws SQLException;
    
}
