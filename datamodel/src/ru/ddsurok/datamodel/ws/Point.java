/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.datamodel.ws;

/**
 *
 * @author d.duritskij
 */
public class Point extends XYPoint {
    public String user;
    public String caption;
    public String color;
    
    public Point () {
    }
    
    public Point (Point old) {
        super((XYPoint)old);
        this.user = old.user;
        this.caption = old.caption;
        this.color = old.color;
    }
    
    public Point (XYPoint old) {
        super(old);
    }
}
