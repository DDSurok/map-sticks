package ru.ddsurok.utils.fault;

/**
 *
 * @author d.duritskij
 */
import ru.ddsurok.datamodel.db.Session;

public class SessionClosedException extends Exception {

    private SessionClosedException() {}
    
    public SessionClosedException(Session ses) {
        super("Session with token[" + Long.toString(ses.getAuthToken()) + "] has been closed.");
    }
}
