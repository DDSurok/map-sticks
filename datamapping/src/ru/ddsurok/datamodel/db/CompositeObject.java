package ru.ddsurok.datamodel.db;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import ru.ddsurok.datamodel.fault.ObjectCanNotConvertedException;
import ru.ddsurok.datamodel.ws.CompositeObjectType;
import ru.ddsurok.datamodel.ws.Line;
import ru.ddsurok.datamodel.ws.Point;
import ru.ddsurok.datamodel.ws.XYPoint;

public class CompositeObject implements Serializable {

    private int Id;
    private User User;
    private String Caption;
    private String Color;
    private CompositeObjectType Type;
    private Set GeometricPoints;

    public CompositeObject() {
        this.GeometricPoints = new HashSet(0);
    }

    public CompositeObject(int id, User MUser, CompositeObjectType type) {
        this.GeometricPoints = new HashSet(0);
        this.Id = id;
        this.User = MUser;
        this.Type = type;
    }
    
    public CompositeObject(int id, User MUser, String caption, String color, Set MGeometricPoints, CompositeObjectType type) {
        this.Id = id;
        this.User = MUser;
        this.Caption = caption;
        this.Color = color;
        this.GeometricPoints = MGeometricPoints;
        this.Type = type;
    }

    public CompositeObject(Point point) {
        Caption = point.caption;
        Color = point.color;
        User = new User(point.user);
        Type = CompositeObjectType.POINT;
        GeometricPoints = new HashSet<GeometricPoint>();
        GeometricPoints.add(new GeometricPoint(point));
    }
    
    public CompositeObject(Line line) {
        Caption = line.caption;
        Color = line.color;
        Type = CompositeObjectType.LINE;
        User = new User(line.user);
        GeometricPoints = new HashSet<GeometricPoint>();
        for (XYPoint point : line.points) {
            GeometricPoints.add(new GeometricPoint(point));
        }
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public User getUser() {
        return this.User;
    }

    public void setUser(User MUser) {
        this.User = MUser;
    }

    public String getCaption() {
        return this.Caption;
    }

    public void setCaption(String caption) {
        this.Caption = caption;
    }

    public String getColor() {
        return this.Color;
    }

    public void setColor(String color) {
        this.Color = color;
    }

    public Set getGeometricPoints() {
        return this.GeometricPoints;
    }

    public void setGeometricPoints(Set MGeometricPoints) {
        this.GeometricPoints = MGeometricPoints;
    }
    
    public CompositeObjectType getType() {
        return Type;
    }
    
    public void setType(CompositeObjectType type) {
        Type = type;
    }
    
    public Point toPoint() throws ObjectCanNotConvertedException {
        if (!this.getType().equals(CompositeObjectType.POINT)) {
            throw new ObjectCanNotConvertedException(this, CompositeObjectType.POINT);
        }
        Point point = new Point((XYPoint)GeometricPoints.toArray()[0]);
        point.caption = Caption;
        point.color = Color;
        point.user = User.getNick();
        return point;
    }
    
    public Line toLine()  throws ObjectCanNotConvertedException {
        if (!this.getType().equals(CompositeObjectType.LINE)) {
            throw new ObjectCanNotConvertedException(this, CompositeObjectType.LINE);
        }
        Line line = new Line();
        line.caption = Caption;
        line.color = Color;
        line.user = User.getNick();
        line.points = (XYPoint[]) this.getGeometricPoints().toArray();
        return line;
    }
}
