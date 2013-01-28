package ru.ddsurok.datamodel.db;

import java.util.HashSet;
import java.util.Set;
import ru.ddsurok.datamodel.fault.ObjectCanNotConvertedException;
import ru.ddsurok.datamodel.ws.CompositeObjectType;

public class Album implements java.io.Serializable {

    private int Id;
    private User Author;
    private String Name;
    private String DefaultColor;
    private Set<CompositeObject> CompositeObjects;
    private Set<Mark> Marks;

    public Album() {
    }
    
    public Album(ru.ddsurok.datamodel.ws.Album album) {
        this.Id = album.Id;
        this.Author = User.create(album.Author);
        this.DefaultColor = album.DefaultColor;
        this.Name = album.Name;
        this.Marks = new HashSet<Mark>();
        for (ru.ddsurok.datamodel.ws.Mark item : album.Marks) {
            this.Marks.add(new Mark(item));
        }
        for (ru.ddsurok.datamodel.ws.Point item : album.Points) {
            this.CompositeObjects.add(new CompositeObject(item));
        }
        for (ru.ddsurok.datamodel.ws.Line item : album.Lines) {
            this.CompositeObjects.add(new CompositeObject(item));
        }
    }

    public Album(int id, User MUser, String name, String defaultcolor) {
        this.Id = id;
        this.Author = MUser;
        this.Name = name;
        this.DefaultColor = defaultcolor;
    }

    public Album(int id, User MUser, String name, String defaultcolor, Set MPolygons, Set MPoints) {
        this.Id = id;
        this.Author = MUser;
        this.Name = name;
        this.DefaultColor = defaultcolor;
    }

    public int getId() {
        return this.Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public User getAuthor() {
        return this.Author;
    }

    public void setAuthor(User MUser) {
        this.Author = MUser;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDefaultColor() {
        return this.DefaultColor;
    }

    public void setDefaultColor(String defaultColor) {
        this.DefaultColor = defaultColor;
    }
    
    public Set getCompositeObjects() {
        return this.CompositeObjects;
    }
    
    public void setCompositeObjects(Set obj) {
        this.CompositeObjects = obj;
    }
    
    public Set getMarks() {
        return this.Marks;
    }
    
    public void setMarks(Set obj) {
        this.Marks = obj;
    }
    
    public boolean validate() {
        if (this.Author == null) {
            return false;
        }
        return true;
    }
    
    public ru.ddsurok.datamodel.ws.Album toWsAlbum() throws ObjectCanNotConvertedException {
        ru.ddsurok.datamodel.ws.Album album = new ru.ddsurok.datamodel.ws.Album();
        album.Id = this.Id;
        album.Author = this.Author.getName();
        album.DefaultColor = this.DefaultColor;
        album.Name = this.Name;
        for (Mark mark : this.Marks) {
            album.Marks.add(mark.toWsMark());
        }
        for (CompositeObject item : this.CompositeObjects) {
            if (item.getType().equals(CompositeObjectType.LINE)) {
                album.Lines.add(item.toLine());
            } else if (item.getType().equals(CompositeObjectType.POINT)) {
                album.Points.add(item.toPoint());
            }
        }
        return album;
    }
}
