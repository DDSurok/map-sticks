/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.datamodel.ws;

import java.io.Serializable;

/**
 *
 * @author d.duritskij
 */
public class XYPoint implements Serializable {
    protected float X;
    protected float Y;
    
    public XYPoint() {
    }
    
    public XYPoint(float x, float y) {
        this.X = x;
        this.Y = y;
    }
    
    public XYPoint(XYPoint old) {
        this.X = old.X;
        this.Y = old.Y;
    }
    
    public float getX() {
        return this.X;
    }

    public void setX(float x) {
        this.X = x;
    }

    public float getY() {
        return this.Y;
    }

    public void setY(float y) {
        this.Y = y;
    }
}
