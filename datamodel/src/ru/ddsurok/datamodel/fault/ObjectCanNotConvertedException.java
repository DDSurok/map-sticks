/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.datamodel.fault;

import ru.ddsurok.datamodel.ws.CompositeObjectType;

/**
 *
 * @author d.duritskij
 */
public class ObjectCanNotConvertedException extends Exception {

    public ObjectCanNotConvertedException(Object obj, CompositeObjectType type) {
        super(String.format("Object \"%s\" can not be converted to %s.", obj.getClass().getName(), type));
    }
}
