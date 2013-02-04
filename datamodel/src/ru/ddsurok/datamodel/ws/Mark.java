package ru.ddsurok.datamodel.ws;

public class Mark extends XYPoint implements java.io.Serializable {
    public String Author;
    public String Caption;
    public String Color;

    public Mark() {
        super();
    }
    
    public Mark (XYPoint p) {
        super(p);
    }
}
