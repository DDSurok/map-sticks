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
import java.util.Collection;
import ru.ddsurok.datamodel.Session;

public interface ISessionUtil {

    public void addSession(Session user) throws SQLException;

    public void updateSession(int id, Session session) throws SQLException;

    public Session getSessionById(int id) throws SQLException;

    public Collection getAllSessions() throws SQLException;

    public void deleteSession(Session session) throws SQLException;
}
