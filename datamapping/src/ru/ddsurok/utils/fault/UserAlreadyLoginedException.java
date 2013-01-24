package ru.ddsurok.utils.fault;

/**
 *
 * @author d.duritskij
 */
public class UserAlreadyLoginedException extends Exception {

    public UserAlreadyLoginedException() {
        super("User already logined in system.");
    }
}
