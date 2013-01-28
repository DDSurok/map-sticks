package ru.ddsurok.datamodel.db;

import ru.ddsurok.datamodel.ws.XYPoint;

public class GeometricPoint extends XYPoint implements java.io.Serializable {

    private int Id;
    
    public GeometricPoint() {
        this.X = 0;
        this.Y = 0;
    }
    
    public GeometricPoint(XYPoint p) {
        super(p);
    }

    public GeometricPoint(int id, float x, float y) {
        this.Id = id;
        this.X = x;
        this.Y = y;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public XYPoint toXYPoint() {
        return this;
    }
}
