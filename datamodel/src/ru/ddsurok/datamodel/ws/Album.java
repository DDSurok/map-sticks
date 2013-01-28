package ru.ddsurok.datamodel.ws;

import java.util.HashSet;
import java.util.Set;

public class Album implements java.io.Serializable {
    public Integer Id;
    public String Author;
    public String Name;
    public String DefaultColor;
    public Set<Line> Lines;
    public Set<Point> Points;
    public Set<Mark> Marks;
    
    public Album() {
        this.Lines = new HashSet<Line>();
        this.Points = new HashSet<Point>();
        this.Marks = new HashSet<Mark>();
    }
}
