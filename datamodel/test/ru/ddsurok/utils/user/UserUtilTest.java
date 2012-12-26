/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.utils.user;

import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.apache.jasper.tagplugins.jstl.ForEach;
import org.junit.*;
import static org.junit.Assert.*;
import ru.ddsurok.datamodel.User;

/**
 *
 * @author d.duritskij
 */
public class UserUtilTest {
    
    public UserUtilTest() {
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
     * Test of addUser method, of class UserUtil.
     */
    @Ignore
    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        User user = null;
        UserUtil instance = new UserUtil();
        instance.addUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUser method, of class UserUtil.
     */
    @Ignore
    @Test
    public void testUpdateUser() throws Exception {
        System.out.println("updateUser");
        int id = 0;
        User user = null;
        UserUtil instance = new UserUtil();
        instance.updateUser(id, user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserById method, of class UserUtil.
     */
    @Ignore
    @Test
    public void testGetUserById() throws Exception {
        System.out.println("getUserById");
        int id = 0;
        UserUtil instance = new UserUtil();
        User expResult = null;
        User result = instance.getUserById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByNick method, of class UserUtil.
     */
    @Ignore
    @Test
    public void testGetUserByNick() throws Exception {
        System.out.println("getUserByNick");
        String nick = "";
        UserUtil instance = new UserUtil();
        User expResult = null;
        User result = instance.getUserByNick(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class UserUtil.
     */
    @Test
    public void testGetAllUsers() throws Exception {
        System.out.println("getAllUsers");
        try {
            UserUtil instance = new UserUtil();
            Collection<User> users = instance.getAllUsers();
            for (User user : users) {
                System.out.print(user);
            }
            assertTrue(true);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of deleteUser method, of class UserUtil.
     */
    @Ignore
    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        User user = null;
        UserUtil instance = new UserUtil();
        instance.deleteUser(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
