/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.utils.session;

import org.junit.*;
import static org.junit.Assert.*;
import ru.ddsurok.datamodel.Session;
import ru.ddsurok.datamodel.User;

/**
 *
 * @author d.duritskij
 */
@Ignore
public class SessionUtilTest {
    
    public SessionUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of finalize method, of class SessionUtil.
     */
    @Test
    public void testFinalize() throws Exception {
        System.out.println("finalize");
        SessionUtil instance = new SessionUtil();
        instance.finalize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSession method, of class SessionUtil.
     */
    @Test
    public void testCreateSession() throws Exception {
        System.out.println("createSession");
        Session ses = null;
        SessionUtil instance = new SessionUtil();
        instance.createSession(ses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSession method, of class SessionUtil.
     */
    @Test
    public void testUpdateSession() throws Exception {
        System.out.println("updateSession");
        int id = 0;
        Session ses = null;
        SessionUtil instance = new SessionUtil();
        instance.updateSession(id, ses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSessionById method, of class SessionUtil.
     */
    @Test
    public void testGetSessionById() throws Exception {
        System.out.println("getSessionById");
        int id = 0;
        SessionUtil instance = new SessionUtil();
        Session expResult = null;
        Session result = instance.getSessionById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSessionByAuthToken method, of class SessionUtil.
     */
    @Test
    public void testGetSessionByAuthToken() throws Exception {
        System.out.println("getSessionByAuthToken");
        int authToken = 0;
        SessionUtil instance = new SessionUtil();
        Session expResult = null;
        Session result = instance.getSessionByAuthToken(authToken);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSessionByUser method, of class SessionUtil.
     */
    @Test
    public void testGetSessionByUser() throws Exception {
        System.out.println("getSessionByUser");
        User user = null;
        SessionUtil instance = new SessionUtil();
        Session expResult = null;
        Session result = instance.getSessionByUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeSession method, of class SessionUtil.
     */
    @Test
    public void testRemoveSession() throws Exception {
        System.out.println("removeSession");
        Session ses = null;
        SessionUtil instance = new SessionUtil();
        instance.removeSession(ses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeSessionByAuthToken method, of class SessionUtil.
     */
    @Test
    public void testRemoveSessionByAuthToken() throws Exception {
        System.out.println("removeSessionByAuthToken");
        int authToken = 0;
        SessionUtil instance = new SessionUtil();
        instance.removeSessionByAuthToken(authToken);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
