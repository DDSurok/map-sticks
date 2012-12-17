package ru.ddsurok.datamodel;

/**
 *
 * @author d.duritskij
 */

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer Id = null;
    private String NickName = null;
    private String HashPswd = null;
    private String Family = null;
    private String Name = null;
    private String Email = null;
    private Boolean IsLocked = null;
    private Boolean IsBaned = null;
    
    public User () {
    }
    
    public void setId(Integer value) {
        Id = value;
    }

    public Integer getId() {
        return Id;
    }

    public void setNickName(String value) {
        NickName = value;
    }

    public String getNickName() {
        return NickName;
    }

    public void setHashPswd(String value) {
        HashPswd = value;
    }

    public String getHashPswd() {
        return HashPswd;
    }

    public void setFamily(String value) {
        Family = value;
    }

    public String getFamily() {
        return Family;
    }

    public void setName(String value) {
        Name = value;
    }

    public String getName() {
        return Name;
    }

    public void setEmail(String value) {
        Email = value;
    }

    public String getEmail() {
        return Email;
    }

    public void setIsLocked(boolean value) {
        IsLocked = value;
    }

    public boolean getIsLocked() {
        return IsLocked;
    }

    public void setIsBaned(boolean value) {
        IsBaned = value;
    }

    public boolean getIsBaned() {
        return IsBaned;
    }
}
