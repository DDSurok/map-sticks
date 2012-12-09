/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.utils;

/**
 *
 * @author ddsurok
 */
import java.sql.SQLException;
import java.util.Collection;
import ru.ddsurok.datamodel.User;

public interface IUserUtil {

    public void addUser(User user) throws SQLException;

    public void updateUser(int id, User user) throws SQLException;

    public User getUserById(int id) throws SQLException;

    public Collection getAllUsers() throws SQLException;

    public void deleteUser(User user) throws SQLException;
}
