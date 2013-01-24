package ru.ddsurok.utils.session;

import ru.ddsurok.utils.fault.SessionClosedException;
import ru.ddsurok.utils.fault.UserAlreadyLoginedException;
import ru.ddsurok.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.junit.Test;
import ru.ddsurok.datamodel.db.Session;
import ru.ddsurok.datamodel.db.User;
import ru.ddsurok.utils.UserUtil;

/**
 *
 * @author d.duritskij
 */
public class SessionUtilTest {
    
    public SessionUtilTest() {
    }

    @Test
    public void createOrAndGetSessionByToken() throws HibernateException, UserAlreadyLoginedException, SessionClosedException {
        SessionUtil sessionUtil = new SessionUtil();
        UserUtil userUtil = new UserUtil();
        User user = userUtil.getUserByNick("test");
        if (user == null) {
            user = new User("test", "1111", "email@email.com", true, true);
            userUtil.addUser(user);
            user = userUtil.getUserByNick("test");
        }
        Session ses = new Session(1L, user, (short)60);
        try {
            sessionUtil.createSession(ses);
        } catch (UserAlreadyLoginedException e) {
            System.out.println("Сессия создана ранее и еще не просрочена.");
        }
        ses = sessionUtil.getSessionByAuthToken(1L);
        System.out.print(ses);
    }
}
