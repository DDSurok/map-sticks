package ru.ddsurok.datamodel;

/**
 *
 * @author d.duritskij
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column
    private String NickName;
    @Column
    private String HashPswd;
    @Column
    private String Family;
    @Column
    private String Name;
    @Column
    private String email;

    public void setId(int value) {
        Id = value;
    }

    public int getId() {
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
        email = value;
    }

    public String getEmail() {
        return email;
    }
}
