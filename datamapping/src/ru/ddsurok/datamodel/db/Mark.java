package ru.ddsurok.datamodel.db;

import ru.ddsurok.datamodel.ws.XYPoint;

public class Mark extends XYPoint implements java.io.Serializable {

    private Integer Id = null;
    private User Author = null;
    private String Caption;
    private String Color;

    public Mark() {
        this.X = 0;
        this.Y = 0;
    }
    
    public Mark(ru.ddsurok.datamodel.ws.Mark mark) {
        super(mark);
        this.Author = User.create(mark.Author);
        this.Caption = mark.Caption;
        this.Color = mark.Color;
    }

    public Mark(int id, User MUser, float x, float y) {
        this.Id = id;
        this.Author = MUser;
        this.X = x;
        this.Y = y;
        this.X = 0;
        this.Y = 0;
    }

    public Mark(int id, User MUser, Album MAlbum, float x, float y, String caption, String color) {
        this.Id = id;
        this.Author = MUser;
        this.X = x;
        this.Y = y;
        this.Caption = caption;
        this.Color = color;
        this.X = 0;
        this.Y = 0;
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

    public void setAuthor(User MAuthor) {
        this.Author = MAuthor;
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
    
    public ru.ddsurok.datamodel.ws.Mark toWsMark() {
        ru.ddsurok.datamodel.ws.Mark mark = new ru.ddsurok.datamodel.ws.Mark();
        mark.Author = this.Author.getNick();
        mark.Caption = this.Caption;
        mark.Color = this.Color;
        return mark;
    }
}
