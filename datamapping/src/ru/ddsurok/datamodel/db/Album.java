package ru.ddsurok.datamodel.db;

import java.util.Set;
import java.io.Serializable;

public class Album implements Serializable {

    private int Id;
    private User Author;
    private String Name;
    private String DefaultColor;
    private Set CompositeObjects;
    private Set Marks;

    public Album() {
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
}
