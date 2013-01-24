/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.utils.user;

import ru.ddsurok.utils.UserUtil;
import java.util.Collection;
import org.junit.*;
import static org.junit.Assert.*;
import ru.ddsurok.datamodel.db.User;

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

    @Test
    public void fullTestUserUtil() throws Exception {
        System.out.println("\t---- test getAllUsers, addUser method running ----");
        UserUtil userUtil = new UserUtil();
        User temp = userUtil.getUserByNick("test");
        if (temp != null) {
            userUtil.deleteUser(temp);
        }
        System.out.println("-- Start print current state table USERS --");
        PrintUsers(userUtil.getAllUsers());
        int oldSize = userUtil.getAllUsers().size();
        System.out.println("-- End print current state table USERS --");
        System.out.println("-- Start create new user --");
        User user = new User("test", "1111", "email@email.com", true, true);
        System.out.print(user);
        System.out.println("-- End create new user --");
        System.out.println("-- Start add user --");
        userUtil.addUser(user);
        System.out.println("-- End add user --");
        System.out.println("-- Start print state table USERS after adding --");
        PrintUsers(userUtil.getAllUsers());
        int newSize = userUtil.getAllUsers().size();
        System.out.println("-- End print state table USERS after adding --");
        assertEquals(oldSize + 1, newSize);
    }

    private void PrintUsers(Collection<User> users) {
        for (User user : users) {
            System.out.print(user);
        }
    }
}
